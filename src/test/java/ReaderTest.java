package test.java;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import main.java.utils.Reader;

public class ReaderTest {

	@Test
	public void readFileTest() throws IOException {
		String fileLocation = "logfiles/requests.log";
		Reader reader = new Reader(fileLocation);
		
		ArrayList<String> rawData = reader.read();
		System.out.println(Arrays.toString(rawData.toArray()));	
		assertTrue(rawData.size() > 0);
				
	}

}
