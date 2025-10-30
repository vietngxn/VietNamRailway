package fourcore.animation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GhiFile {
	
	public GhiFile() {
		
	}
	
	public static void appendData(String dataLine, File file) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file , true));
			bw.write(dataLine);
			bw.newLine();
			bw.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
