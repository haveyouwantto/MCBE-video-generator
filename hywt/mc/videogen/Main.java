package hywt.mc.videogen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;

import hywt.mc.videogen.RefStrings.Str;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;  

public class Main {

	
	  /*public static void main(String[] args) {
	   Window.showError(RefStrings.position_x);
	}
	*/
	
	public static String pname=Str.PACKAGE_ID.getStr();
	public static String pdescription=Str.PACKAGE_DESCRIPTION.getStr();
	
	
    public static void loadFolder(File dir) {
    	
    	File[] images=dir.listFiles();
    	boolean hasPng=false;
    	String png=".png";
    	int pngcount=0;
    	long totalsize=0;
    	/*
    	 * Check PNG files
    	 */
    	for(File f: images){
    		if(getFileExtension(f).endsWith(png)) {
    			hasPng=true;
    			pngcount++;
    			totalsize=totalsize+f.length();
    		}
        }
    	if(hasPng==false) {
    		Window.showError(Str.NO_PNG_FOUND.getStr());
    	}else {
    		/*
    		 * Print info
    		 */
    		Window.dirInfo(pngcount, totalsize);
    		Window.showMessage(pngcount+" "+Str.PNG_FOUND.getStr());
    	}
    }
    
    public static void genPack(File sourcedir,File destdir) throws IOException {
    	if(pname.length()>11) {
    		throw new Error(Str.ID_TOO_LONG.getStr());
    	}
    	
    	File resdestdir=new File(destdir.getAbsolutePath()+"/"+pname+"-res");
    	resdestdir.mkdirs();
    	
    	File datdestdir=new File(destdir.getAbsolutePath()+"/"+pname+"-dat");
    	datdestdir.mkdirs();
    	
    	/*
    	 * Initialize Pack
    	 */
    	File resmanifest=new File(resdestdir.getAbsolutePath()+"/manifest.json");
    	if(!resmanifest.exists()) {
    		resmanifest.createNewFile();
    		FileOperation.writeFile(resmanifest, Template.resmanifest(pdescription));
    	}
    	
    	File datmanifest=new File(datdestdir.getAbsolutePath()+"/manifest.json");
    	if(!datmanifest.exists()) {
    		datmanifest.createNewFile();
    		FileOperation.writeFile(datmanifest, Template.datmanifest(pdescription));
    	}
    	
    	
    	/*
    	 * Create resources
    	 */
    	File particles=new File(resdestdir.getAbsolutePath()+"/particles/"+pname+"-frames");
    	particles.mkdirs();
    	File videoframe=new File(resdestdir.getAbsolutePath()+"/textures/"+pname+"-frames");
    	videoframe.mkdirs();
    	File audio=new File(resdestdir.getAbsolutePath()+"/sounds/audio/"+pname);
    	audio.mkdirs();
    	
    	/*
    	 * Create data
    	 */
    	File fx=new File(datdestdir.getAbsolutePath()+"/functions/"+pname);
    	fx.mkdirs();
    	
    	
    	System.out.println(resdestdir);
    	System.out.println(datdestdir);
    	
    	/*
    	 * Generate resources and data
    	 */
    	
    	/*
    	 * Initialize functions
    	 */
    	File fxf=new File(fx.getAbsolutePath()+"/tick.mcfunction");
    	File fxinit=new File(fx.getAbsolutePath()+"/init.mcfunction");
    	File fxstop=new File(fx.getAbsolutePath()+"/stop.mcfunction");
    	File fxhelp=new File(fx.getAbsolutePath()+"/stop.mcfunction");
    	FileOperation.writeFile(fxinit, Template.initcmd());
    	FileOperation.writeFile(fxstop, Template.stopcmd());
    	FileOperation.writeFile(fxhelp, Template.helpcmd());
    	
    	FileWriter fw = new FileWriter(fxf);
    	if(!fxf.exists()) {
    		fxf.createNewFile();
  	  }
    	/*
    	 * sound_definitions.json
    	 */
    	FileOperation.writeFile(new File(resdestdir.getAbsolutePath()+"/sounds/sound_definitions.json"), Template.soundjson());
      	
        BufferedWriter bw = new BufferedWriter(fw);
    	File[] images=Window.dir.listFiles();
    	/*
    	 * Pick thumbnail
    	 */
    	if(images.length>100) {
    		Files.copy(images[100].toPath(), new File(resdestdir.getAbsolutePath()+"/pack_icon.png").toPath(),REPLACE_EXISTING);
    		Files.copy(images[100].toPath(), new File(datdestdir.getAbsolutePath()+"/pack_icon.png").toPath(),REPLACE_EXISTING);
    	}
    	bw.write("execute @s[scores={"+pname+"-tick=1}] ~ ~ ~ playsound video."+pname+" @a\n");
    	
    	/*
    	 * Detect facing mode
    	 */
    	int[] facing;
    	if(Window.getRdbtnPositiveX().isSelected()) {
    		facing= RefStrings.positiveX;
    	}else if(Window.getRdbtnNegativeX().isSelected()) {
    		facing= RefStrings.negativeX;
    	}else if(Window.getRdbtnPositiveZ().isSelected()) {
    		facing= RefStrings.positiveZ;
    	}else{
    		facing= RefStrings.negativeZ;
    	}
    	
    	File tick = null;
    	FileWriter functionw;
    	BufferedWriter bfw = null;
    	for(int i=0;i<images.length;i++){
    		if(i%1000==0) {
    			/*
    			 * Create new function file
    			 */
    			bw.write(Template.indexcmd(i));
    			tick=new File(fx.getAbsolutePath()+"/frames/frame"+(i/1000)+"k.mcfunction");
    	    	tick.getParentFile().mkdirs();
    	    	if(!tick.exists()) {
    	    		tick.createNewFile();
    	  	  }
    			functionw = new FileWriter(tick);
    	    	bfw = new BufferedWriter(functionw);
    		}
    		File f=images[i];
    		if(getFileExtension(f).endsWith(".png")) {
    			/*
    			 * Generate particle JSON
    			 */
    			FileOperation.writeFile(
    					new File(resdestdir.getAbsolutePath()+"/particles/"+pname+"-frames/"+f.getName()+".json"), 
    					Template.framejson(
    							f.getName(), 
    							Double.parseDouble(Window.getSizeX().getText()),
    							Double.parseDouble(Window.getSizeY().getText()), 
    							Window.getRdbtnHoverAtTarget().isSelected(),
    							facing
    							)
    					);
    			
    			
    			//FileOperation.writeFile(new File(resdestdir.getAbsolutePath()+"/particles/"+pname+"-frames/"+f.getName()+".json"), Template.myCinemaFramejson(f.getName()));
    			Files.copy(f.toPath(), new File(videoframe.getAbsolutePath()+"/"+f.getName()).toPath(),REPLACE_EXISTING);
    			
    			Window.setProgress(i,images.length);
    			
    			/*
    			 * Generate function
    			 */
    	        bfw.write(Template.cmd(
    	        		i,
    	        		f.getName(),
    	        		Window.getTxtX().getText(),
    	        		Window.getTxtY().getText(),
    	        		Window.getTxtZ().getText()
    	        		));
    	        
    	        //bfw.write(Template.myCinemaCmd(i, f.getName()));
    	        //System.out.print(i+" / "+tick.getAbsolutePath()+"\n");
    		}else if(getFileExtension(f).endsWith(".ogg")) {
    			Files.copy(f.toPath(),new File(audio.getAbsolutePath()+"/audio.ogg").toPath(),REPLACE_EXISTING);
    		}
    		if(i%1000==999||i==images.length-1) {
    			//System.out.println(tick.getAbsolutePath());
    			
    			/*
    			 * Switch to next function file
    			 */
    			bfw.close();
    		}
        }
    	/*
    	 * Finalization
    	 */
    	Window.setProgress(images.length,images.length);
    	bw.write("scoreboard players add @s[scores={"+pname+"-tick=0.."+images.length+"}] "+pname+"-tick 1\n");
    	bw.close();
    	Window.showMessage(Str.DONE_GEN.getStr());
    	Window.setEnable();
    }
    
    
    
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    
    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
    
    public static String formatSeconds(int timeInSeconds)
    {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds ;

        return formattedTime;
    }
}
