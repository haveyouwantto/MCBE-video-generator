package hywt.mc.videogen;

public class RefStrings {
     enum Str{
    	 TITLE("Minecraft Video Generator"),
    	 GENERATE_PACK("Generate Pack"),
    	 
    	 INFORMATION("Information"),
    	 OUTPUT_SETTINGS("Output Settings"),
    	 
    	 NO_OUTPUT("No output folder selected."),
    	 ID_TOO_LONG("ID too long! (max.11 characters)"),
    	 
    	 SELECT_FOLDER("Select Folder"),
    	 BROWSE("Browse"),
    	 SOURCE("Source:"),
    	 OUTPUT("Output:"),
    	 
    	 ERROR("Error"),
    	 WARNING("Warning"),
    	 INFO("Info"),
    	 
    	 FRAMES("Frames:"),
    	 SIZE("Size:"),
    	 LENGTH("Length:"),
    	 
    	 PACKAGE_ID("package_id"),
    	 PACKAGE_DESCRIPTION("Package Description"),
    	 PACKAGE_ID_LABEL("Package ID:"),
    	 PACKAGE_DESCRIPTION_LABEL("Package Description:"),
    	 
    	 NO_PNG_FOUND("No PNG Found!"),
    	 PNG_FOUND("PNG files found"),
    	 DONE_GEN("Done generating pack."),
    	 
    	 DISPLAY_MODE("Display Mode: "),
    	 DISPLAY_MODE_HOVER_AT_TARGET("Hover at target"),
    	 DISPLAY_MODE_STATIC("Static (Like a screen)"),
    	 
    	 COORDINATES("Coordinates:"),
    	 SCREEN_SIZE("Screen Size:"),
    	 
    	 STATIC_MODE_ONLY("Static Mode Only"),
    	 FACING_MODE("Facing mode:"),
    	 
    	 POSITIVE_X("Positive X"),
    	 NEGATIVE_X("Negative X"),
    	 POSITIVE_Z("Positive Z"),
    	 NEGATIVE_Z("Negative Z"),
    	 
    	 PROJECTS("Projects"),
    	 SAVE_SETTINGS("Save Settings"),
    	 LOAD_SETTINGS("Load Settings");
    	 
    	 private String str;

    	 Str(String str) {
 			this.str = str;
 		}
		
		public String getStr() {
			return str;
		}
     }
     public static int[] positiveX= {1,0};
     public static int[] negativeX= {-1,0};
     public static int[] positiveZ= {0,1};
     public static int[] negativeZ= {0,-1};
}
