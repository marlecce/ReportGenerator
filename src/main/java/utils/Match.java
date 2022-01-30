package main.java.utils;

public class Match  implements Comparable<Match>  {
	
	private String timestamp ;
	private Integer bytes;
	private Integer statusCode;
	private String ip;
	private Integer totalOKRequests = 0;
	private Integer totalRequests = 0;
	
	private Integer totalOKBytes = 0;
	private Integer totalBytes = 0;
	
	public Match(String timestamp, Integer bytes, Integer statusCode, String ip) {
		this.timestamp = timestamp;
		this.bytes = bytes;
		this.statusCode = statusCode;
		this.ip = ip;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getOKTotalRequests() {
		return totalOKRequests;
	}

	public void setOKTotalRequests(Integer totalRequests) {
		this.totalOKRequests = totalRequests;
	}

	public Integer getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(Integer totalRequests) {
		this.totalRequests = totalRequests;
	}

	public Integer getTotalOKBytes() {
		return totalOKBytes;
	}

	public void setTotalOKBytes(Integer totalOKBytes) {
		this.totalOKBytes = totalOKBytes;
	}

	public Integer getTotalBytes() {
		return totalBytes;
	}

	public void setTotalBytes(Integer totalBytes) {
		this.totalBytes = totalBytes;
	}
	
	public double getPercentageTotalRequests() {
		return (this.totalOKRequests * 100) / this.totalRequests; 
	}
	
	public double getPercentageTotalBytes() {
		return (this.totalOKBytes * 100) /  this.totalBytes; 
	}

	@Override
	public int compareTo(Match m) {
		if (this.totalOKRequests > m.totalOKRequests) return 1;
		if (this.totalOKRequests < m.totalOKRequests) return -1;
		return -1;
		
	}

}
