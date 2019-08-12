package hywt.mc.videogen;

import java.io.File;
import java.io.IOException;

public class ProjectSaver extends Project{

	public static void save(File file) {
		
		
		try {
			FileOperation.writeFile(file, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String toString() {
		return "P:";
	}
}
