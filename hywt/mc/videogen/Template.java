package hywt.mc.videogen;

import java.util.UUID;

public class Template {
	
	//Resource pack manifest.json
    public static String resmanifest(String description) {
        return "{\r\n" + 
			"	\"format_version\": 1,\r\n" + 
			"	\"header\": {\r\n" + 
			"		\"description\": \""+description+"\",\r\n" + 
			"		\"name\": \""+Main.pname+"-Resources\",\r\n" + 
			"		\"uuid\": \""+UUID.randomUUID()+"\",\r\n" + 
			"		\"version\": [1, 0, 0],\r\n" + 
			"		\"min_engine_version\": [1, 8, 0]\r\n" + 
			"	},\r\n" + 
			"	\"modules\": [{\r\n" + 
			"		\"description\": \""+Main.pname+"\",\r\n" + 
			"		\"type\": \"resources\",\r\n" + 
			"		\"uuid\": \""+UUID.randomUUID()+"\",\r\n" + 
			"		\"version\": [1, 0, 0]\r\n" + 
			"	}]\r\n" + 
			"}";  
	}
    
    //Behavior pack manifest.json
    public static String datmanifest(String description) {
    	return "{\r\n" + 
		"    \"format_version\": 1,\r\n" + 
		"    \"header\": {\r\n" + 
		"        \"description\": \""+description+"\",\r\n" + 
		"        \"name\": \""+Main.pname+"-Data\",\r\n" + 
		"        \"uuid\": \""+UUID.randomUUID()+"\",\r\n" + 
		"        \"version\": [1, 0, 0],\r\n" + 
		"        \"min_engine_version\": [1, 8, 0]\r\n" + 
		"    },\r\n" + 
		"    \"modules\": [\r\n" + 
		"        {\r\n" + 
		"            \"description\": \""+Main.pname+"\",\r\n" + 
		"            \"type\": \"data\",\r\n" + 
		"            \"uuid\": \""+UUID.randomUUID()+"\",\r\n" + 
		"            \"version\": [1, 0, 0]\r\n" + 
		"        }\r\n" + 
		"    ]\r\n" + 
		"}";
    }
    
    //Particle jsons
    public static String framejson(String framename,double width,double height,boolean mode,int[] facing) {
    	String facingMode;
    	if(mode) {
    		facingMode="rotate_xyz";
    		facing= new int[]{0,0};
    	}else {
    		facingMode="direction_z";
    	}
    	return "{\r\n" + 
    			"  \"format_version\": \"1.10.0\",\r\n" + 
    			"  \"particle_effect\": {\r\n" + 
    			"    \"description\": {\r\n" + 
    			"      \"identifier\": \"minecraft:"+Main.pname+"-"+framename+"\",\r\n" + 
    			"      \"basic_render_parameters\": {\r\n" + 
    			"        \"material\": \"particles_alpha\",\r\n" + 
    			"        \"texture\": \"textures/"+Main.pname+"-frames/"+framename+"\"\r\n" + 
    			"      }\r\n" + 
    			"    },\r\n" + 
    			"    \"components\": {\r\n" + 
    			"      \"minecraft:emitter_rate_instant\": {\r\n" + 
    			"        \"num_particles\": 1\r\n" + 
    			"      },\r\n" + 
    			"      \"minecraft:emitter_lifetime_once\": {\r\n" + 
    			"        \"active_time\": 0.05\r\n" + 
    			"      },\r\n" + 
    			"      \"minecraft:emitter_shape_point\": {\r\n" + 
    			"        \"offset\": [\r\n" + 
    			"          0,\r\n" + 
    			"          0,\r\n" + 
    			"          0\r\n" + 
    			"        ],\r\n" + 
    			"        \"direction\": [\r\n" + 
    			"          "+facing[0]+",\r\n" + 
    			"          0,\r\n" + 
    			"          "+facing[1]+"\r\n" + 
    			"        ]\r\n" + 
    			"      },\r\n" + 
    			"      \"minecraft:particle_lifetime_expression\": {\r\n" + 
    			"        \"max_lifetime\": 0.12\r\n" + 
    			"      },\r\n" + 
    			"      \"minecraft:particle_appearance_billboard\": {\r\n" + 
    			"        \"size\": [\r\n" + 
    			"          "+(width/2)+",\r\n" + 
    			"          "+(height/2)+"\r\n" + 
    			"        ],\r\n" + 
    			"        \"facing_camera_mode\": \""+facingMode+"\"\r\n" + 
    			"      }\r\n" + 
    			"    }\r\n" + 
    			"  }\r\n" + 
    			"}";
    }
    
    //Particle command
    public static String cmd(int framenum, String framename,String x,String y,String z) {
		return "execute @a[scores={"+Main.pname+"-tick="+framenum+"},tag="+Main.pname+"] ~ ~ ~ particle minecraft:"+Main.pname+"-"+framename+" "+x+" "+y+" "+z+"\n";
    	
    }
    
    //Indexes in tick.mcfunction
    public static String indexcmd(int framenum) {
		return "execute @a[scores={"+Main.pname+"-tick="+(framenum/1000)*1000+".."+(framenum/1000+1)*1000+"},tag="+Main.pname+"] ~ ~ ~ function "+Main.pname+"/frames/frame"+framenum/1000+"k\n";
    	
    }
    
    //init.mcfunction
    public static String initcmd() {
	return 	"scoreboard objectives add "+Main.pname+"-tick dummy\n"+
			"tag @s add "+Main.pname+"\n"+
			"scoreboard players set @s "+Main.pname+"-tick 0"
			;
}
    //stop.mcfunction
public static String stopcmd() {
	return "stopsound @s\n"+
	       "scoreboard players set @s "+Main.pname+"-tick 0\n"+
			"tag @s remove "+Main.pname;
}

	//help.mcfunction
public static String helpcmd() {
return "tellraw @s {\"rawtext\":[{\"text\":\"/function "+Main.pname+"/play - Play video\\n"
		+ "/function "+Main.pname+"/tick - Next frame (runs in repeating command block)\\n"
		+ "/function "+Main.pname+"/stop - Stop playback\"}]}"
			;
}

    
	//sound_definitions.json
    public static String soundjson() {
return "{\r\n" + 
		"  \"video."+Main.pname+"\": {\r\n" + 
		"    \"category\": \"music\",\r\n" + 
		"    \"max_distance\": 50.0,\r\n" + 
		"    \"sounds\": [\r\n" + 
		"      {\r\n" + 
		"        \"name\": \"sounds/audio/"+Main.pname+"/audio\",\r\n" + 
		"        \"stream\": true,\r\n" + 
		"        \"load_on_low_memory\": true\r\n" + 
		"      }\r\n" + 
		"    ]\r\n" + 
		"  }}";
    }
}
