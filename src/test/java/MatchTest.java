package test.java;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import main.java.utils.Match;

public class MatchTest {

	@Test
	public void percentageTest() throws IOException {
		
		Match match = new Match("29/Jan/2022:16:44:32 +0000", 125, 200, "127.0.0.1");
		
		// requests
		match.setOKTotalRequests(50);
		match.setTotalRequests(100);
		
		assertTrue(50.0 == match.getPercentageTotalRequests());
		
		match.setOKTotalRequests(1);
		match.setTotalRequests(100);
		
		assertTrue(1.0 == match.getPercentageTotalRequests());
	
		match.setOKTotalRequests(33);
		match.setTotalRequests(100);
		
		assertTrue(33.0 == match.getPercentageTotalRequests());
		
		// bytes
		match.setTotalOKBytes(120);
		match.setTotalBytes(240);
		
		assertTrue(50.0 == match.getPercentageTotalBytes());
	}

}
