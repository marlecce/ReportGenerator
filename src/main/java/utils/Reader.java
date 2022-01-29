package main.java.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

	private String fileLocation = null;
	
	/**
	 * 
	 * @param fileLocation
	 */
	public Reader (String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public ArrayList<String> read() throws IOException {
		
		ArrayList<String> rawData= new ArrayList<String>();
		
        FileReader fileReader = new FileReader(this.fileLocation);
 
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
    
        while ((line = bufferedReader.readLine()) != null) {
            rawData.add(line);
        }
        
        bufferedReader.close();	
        
        return rawData;
	}
	
}
