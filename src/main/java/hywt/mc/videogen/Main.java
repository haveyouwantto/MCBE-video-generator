package hywt.mc.videogen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;  

public class Main {

	
	  /*public static void main(String[] args) {
	   Window.showError(RefStrings.position_x);
	}
	*/
	
	public static void loadFolder(File dir) {
    	
    	File[] images=dir.listFiles();
    	boolean hasPng=false;
    	String png=".png"; //$NON-NLS-1$
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
    		Window.showError(Messages.getString("Main.noPngFound")); //$NON-NLS-1$
    	}else {
    		/*
    		 * Print info
    		 */
    		Window.dirInfo(pngcount, totalsize);
    		Window.showMessage(pngcount+" "+Messages.getString("Main.pngFound")); //$NON-NLS-1$ //$NON-NLS-2$
    	}
    }
    
    public static void genPack(Project p) throws IOException {
    	if(p.getPname().length()>11) {
    		throw new Error(Messages.getString("Main.idTooLong")); //$NON-NLS-1$
    	}
    	
    	File resdestdir=new File(p.getOutput()+"/"+p.getPname()+"-res"); //$NON-NLS-1$ //$NON-NLS-2$
    	resdestdir.mkdirs();
    	
    	File datdestdir=new File(p.getOutput()+"/"+p.getPname()+"-dat"); //$NON-NLS-1$ //$NON-NLS-2$
    	datdestdir.mkdirs();
    	
    	/*
    	 * Initialize Pack
    	 */
    	File resmanifest=new File(resdestdir.getAbsolutePath()+"/manifest.json"); //$NON-NLS-1$
    	if(!resmanifest.exists()) {
    		resmanifest.createNewFile();
    		FileOperation.writeFile(resmanifest, Template.resmanifest(p.getPname(),p.getPdescription()));
    	}
    	
    	File datmanifest=new File(datdestdir.getAbsolutePath()+"/manifest.json"); //$NON-NLS-1$
    	if(!datmanifest.exists()) {
    		datmanifest.createNewFile();
    		FileOperation.writeFile(datmanifest, Template.datmanifest(p.getPname(),p.getPdescription()));
    	}
    	
    	
    	/*
    	 * Create resources
    	 */
    	File particles=new File(resdestdir.getAbsolutePath()+"/particles/"+p.getPname()+"-frames"); //$NON-NLS-1$ //$NON-NLS-2$
    	particles.mkdirs();
    	File videoframe=new File(resdestdir.getAbsolutePath()+"/textures/"+p.getPname()+"-frames"); //$NON-NLS-1$ //$NON-NLS-2$
    	videoframe.mkdirs();
    	File audio=new File(resdestdir.getAbsolutePath()+"/sounds/audio/"+p.getPname()); //$NON-NLS-1$
    	audio.mkdirs();
    	
    	/*
    	 * Create data
    	 */
    	File fx=new File(datdestdir.getAbsolutePath()+"/functions/"+p.getPname()); //$NON-NLS-1$
    	fx.mkdirs();
    	
    	
    	System.out.println(resdestdir);
    	System.out.println(datdestdir);
    	
    	/*
    	 * Generate resources and data
    	 */
    	
    	/*
    	 * Initialize functions
    	 */
    	File fxf=new File(fx.getAbsolutePath()+"/tick.mcfunction"); //$NON-NLS-1$
    	File fxinit=new File(fx.getAbsolutePath()+"/play.mcfunction"); //$NON-NLS-1$
    	File fxstop=new File(fx.getAbsolutePath()+"/stop.mcfunction"); //$NON-NLS-1$
    	File fxhelp=new File(fx.getAbsolutePath()+"/help.mcfunction"); //$NON-NLS-1$
    	FileOperation.writeFile(fxinit, Template.initcmd(p.getPname()));
    	FileOperation.writeFile(fxstop, Template.stopcmd(p.getPname()));
    	FileOperation.writeFile(fxhelp, Template.helpcmd(p.getPname()));
    	
    	FileWriter fw = new FileWriter(fxf);
    	if(!fxf.exists()) {
    		fxf.createNewFile();
  	  }
    	/*
    	 * sound_definitions.json
    	 */
    	FileOperation.writeFile(new File(resdestdir.getAbsolutePath()+"/sounds/sound_definitions.json"), Template.soundjson(p.getPname())); //$NON-NLS-1$
      	
        BufferedWriter bw = new BufferedWriter(fw);
    	File[] images=p.getSource().listFiles();
    	/*
    	 * Pick thumbnail
    	 */
    	if(images.length>100) {
    		Files.copy(images[100].toPath(), new File(resdestdir.getAbsolutePath()+"/pack_icon.png").toPath(),REPLACE_EXISTING); //$NON-NLS-1$
    		Files.copy(images[100].toPath(), new File(datdestdir.getAbsolutePath()+"/pack_icon.png").toPath(),REPLACE_EXISTING); //$NON-NLS-1$
    	}
    	bw.write("execute @a[scores={"+p.getPname()+"-tick=1},tag="+p.getPname()+"] ~ ~ ~ playsound video."+p.getPname()+" @s\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    	

    	File tick = null;
    	FileWriter functionw;
    	BufferedWriter bfw = null;
    	for(int i=0;i<images.length;i++){
    		if(i%1000==0) {
    			/*
    			 * Create new function file
    			 */
    			bw.write(Template.indexcmd(p.getPname(),i));
    			tick=new File(fx.getAbsolutePath()+"/frames/frame"+(i/1000)+"k.mcfunction"); //$NON-NLS-1$ //$NON-NLS-2$
    	    	tick.getParentFile().mkdirs();
    	    	if(!tick.exists()) {
    	    		tick.createNewFile();
    	  	  }
    			functionw = new FileWriter(tick);
    	    	bfw = new BufferedWriter(functionw);
    		}
    		File f=images[i];
    		if(getFileExtension(f).endsWith(".png")) { //$NON-NLS-1$
    			/*
    			 * Generate particle JSON
    			 */
    			FileOperation.writeFile(
    					new File(resdestdir.getAbsolutePath()+"/particles/"+p.getPname()+"-frames/"+f.getName()+".json"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    					Template.framejson(
    							p.getPname(),
    							f.getName(), 
    							p.getSizex(),
    							p.getSizey(), 
    							p.isHover(),
    							ArrayOperation.toIntArray(p.getFacing())
    							)
    					);
    			
    			
    			//FileOperation.writeFile(new File(resdestdir.getAbsolutePath()+"/particles/"+pname+"-frames/"+f.getName()+".json"), Template.myCinemaFramejson(f.getName()));
    			Files.copy(f.toPath(), new File(videoframe.getAbsolutePath()+"/"+f.getName()).toPath(),REPLACE_EXISTING); //$NON-NLS-1$
    			
    			Window.setProgress(i,images.length);
    			
    			/*
    			 * Generate function
    			 */
    	        bfw.write(Template.cmd(
    	        		p.getPname(),
    	        		i,
    	        		f.getName(),
    	        		p.getTxtx(),
    	        		p.getTxty(),
    	        		p.getTxtz()
    	        		));
    	        
    	        //bfw.write(Template.myCinemaCmd(i, f.getName()));
    	        //System.out.print(i+" / "+tick.getAbsolutePath()+"\n");
    		}else if(getFileExtension(f).endsWith(".ogg")) { //$NON-NLS-1$
    			Files.copy(f.toPath(),new File(audio.getAbsolutePath()+"/audio.ogg").toPath(),REPLACE_EXISTING); //$NON-NLS-1$
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
    	bw.write("scoreboard players add @s[scores={"+p.getPname()+"-tick=0.."+images.length+"},tag="+p.getPname()+"] "+p.getPname()+"-tick 1\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    	bw.close();
    	Window.showMessage(Messages.getString("Main.doneGen")); //$NON-NLS-1$
    	Window.setEnable();
    }
    
    
    
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf("."); //$NON-NLS-1$
        if (lastIndexOf == -1) {
            return ""; // empty extension //$NON-NLS-1$
        }
        return name.substring(lastIndexOf);
    }
    
    public static String readableFileSize(long size) {
        if(size <= 0) return "0"; //$NON-NLS-1$
        final String[] units = new String[] { Messages.getString("Main.B"), Messages.getString("Main.KB"), Messages.getString("Main.MB"), Messages.getString("Main.GB"), Messages.getString("Main.TB") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups]; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public static String formatSeconds(int timeInSeconds)
    {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = ""; //$NON-NLS-1$
        if (hours < 10)
            formattedTime += "0"; //$NON-NLS-1$
        formattedTime += hours + ":"; //$NON-NLS-1$

        if (minutes < 10)
            formattedTime += "0"; //$NON-NLS-1$
        formattedTime += minutes + ":"; //$NON-NLS-1$

        if (seconds < 10)
            formattedTime += "0"; //$NON-NLS-1$
        formattedTime += seconds ;

        return formattedTime;
    }
}
