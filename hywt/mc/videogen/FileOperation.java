package hywt.mc.videogen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
  public static void writeFile(File file,String content) throws IOException {
	  if(!file.exists()) {
		  file.createNewFile();
	  }
	  FileWriter fw = new FileWriter(file);
  	
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(content);
      bw.close();
  }
}
