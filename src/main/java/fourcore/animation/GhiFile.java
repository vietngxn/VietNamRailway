package fourcore.animation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public static boolean xoaTrangDong(File file, int dongXoa) {
	    List<String> lines = new ArrayList<>();
	    boolean daXoa = false;
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        int index = 0;
	        while ((line = reader.readLine()) != null) {
	            if (index == dongXoa) {
	                lines.add(""); 
	                daXoa = true;
	            } else {
	                lines.add(line);
	            }
	            index++;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }

	    if (daXoa) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	            for (String l : lines) {
	                writer.write(l);
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    return daXoa;
	}

	
}
