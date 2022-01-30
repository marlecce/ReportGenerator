package main.java.report;

import java.io.IOException;
import java.util.TreeSet;

import main.java.utils.Match;
import main.java.utils.Parser;
import main.java.utils.Writer;

public class Generator {

	public static void main(String[] args) throws IOException  {
		
		String FILE_TO_PROCESS = "logfiles/requests.log";
		
		// parse the log file
		Parser parser = new Parser();
		TreeSet<Match> resultSet = parser.parse(FILE_TO_PROCESS);
		
		// write report
		Writer writer = new Writer();
		writer.write(resultSet);

	}

}
