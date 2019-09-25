package hywt.mc.videogen;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

public class ProjectSaver extends Project{

	public static void save(Project p,File file) {
		
		try {
			FileOperation.writeFile(file, p.toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Project load(File file) throws IOException {
		
		Gson g=new Gson();
		String str = null;
		
		str=FileOperation.readFile(file);
		Project p=g.fromJson(str,Project.class );

		return p;
	}
}
