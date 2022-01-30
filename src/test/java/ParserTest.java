package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.TreeSet;

import org.junit.Test;

import main.java.utils.Match;
import main.java.utils.Parser;

public class ParserTest {

	@Test
	public void parseTest() throws IOException {
		String fileLocation = "logfiles/requests.log";
		Parser parser = new Parser();

		TreeSet<Match> set = parser.parse(fileLocation);

		assertTrue(set.isEmpty() == false);

		assertEquals("123.45.201.225", set.first().getIp());
		assertTrue(Integer.compare(set.first().getStatusCode(), Parser.HTTP_STATUS_CODE_OK) == 0);
		assertEquals(new Integer(10), set.first().getOKTotalRequests());
		assertEquals(new Integer(11), set.first().getTotalRequests());
		assertEquals(new Integer(42039), set.first().getTotalOKBytes());
		assertEquals(new Integer(46278), set.first().getTotalBytes());

	}

}
