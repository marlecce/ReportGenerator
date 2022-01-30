package main.java.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private static final Pattern REGEX = Pattern.compile(
			"(?<timestamp>[\\d]{1,2}\\/[\\w]+\\/[\\d]{4}:[\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}\\s\\+[\\d]{4}):(?<byte>[\\d]+):(?<statusCode>[\\d]+):(?<ip>\\d+.\\d+.\\d+.\\d+.)",
			Pattern.DOTALL);

	public static final Integer HTTP_STATUS_CODE_OK = 200;

	public Parser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param fileToRead
	 * @return
	 * @throws FileNotFoundException
	 */
	private BufferedReader readFile(String fileToRead) throws FileNotFoundException {
		FileReader fileReader = new FileReader(fileToRead);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		return bufferedReader;

	}

	/**
	 * 
	 * @param fileToParse @return @throws IOException @throws
	 */
	public TreeSet<Match> parse(String fileToParse) throws IOException {

		TreeSet<Match> set = new TreeSet<Match>();

		HashMap<String, Match> ipMatches = new HashMap<String, Match>();
		HashMap<String, Match> ipOKMatches = new HashMap<String, Match>();

		BufferedReader bufferedReader = this.readFile(fileToParse);
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			final Matcher matcher = REGEX.matcher(line);
			if (matcher.find()) {

				Integer statusCode = Integer.parseInt(matcher.group(3));
				Integer bytes = Integer.parseInt(matcher.group(2));
				String timestamp = matcher.group(1);
				String ip = matcher.group(4);

				Match match = null;

				if (ipMatches.get(ip) == null) {

					match = new Match(timestamp, bytes, statusCode, ip);

				} else {

					match = ipMatches.get(ip);

					// update data for the last match
					match.setStatusCode(statusCode);
					match.setTimestamp(timestamp);

				}

				// increment the total requests
				int tmpTotalRequests = match.getTotalRequests();
				match.setTotalRequests(tmpTotalRequests + 1);

				// increment the total bytes sent
				int tmpTotalBytes = match.getTotalBytes();
				match.setTotalBytes(tmpTotalBytes + bytes);

				if (Integer.compare(statusCode, HTTP_STATUS_CODE_OK) == 0) {
					// increment ok requests
					int tmpTotalOKRequests = match.getOKTotalRequests();
					match.setOKTotalRequests(tmpTotalOKRequests + 1);

					// increment ok bytes
					int tmpTotalOKBytes = match.getTotalOKBytes();
					match.setTotalOKBytes(tmpTotalOKBytes + bytes);
					ipOKMatches.put(ip, match);
				}

				ipMatches.put(ip, match);

			}
		}

		bufferedReader.close();

		set.addAll(ipOKMatches.values());

		return (TreeSet<Match>) set.descendingSet();

	}

}
