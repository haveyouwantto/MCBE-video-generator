package hywt.mc.videogen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
  
  public static String readFile(File file) throws IOException {
	  FileInputStream fis = new FileInputStream(file);
	  byte[] data = new byte[(int) file.length()];
	  fis.read(data);
	  fis.close();

	  String str = new String(data, "UTF-8");
	  return str;
  }
}
