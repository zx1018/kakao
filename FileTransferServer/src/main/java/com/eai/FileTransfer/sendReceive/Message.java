package com.eai.FileTransfer.sendReceive;

public class Message {

	private String fileName;       // 파일명 
	private String secretKey;      // 파일 암호화 키 
	private String transactionKey; // 전문 거래 번호 
	private String sndType;        // 전송방식
	private String fileSize;       // 파일 사이즈 
	private String srcPath;        // 파일 전송 경로 
	private String dstPath;        // 파일 수신 경로 
	private String rcvip;        // 수신측 ip 정보
	private String rcvPort;        // 수신측 Port 정보  
	private String sndRst;         // 파일 송신 결과 
	private String fileHash;       // 파일의 해시 값 
	
	
	public Message() {
		
	}
	
	

	// getter, setter 
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getRcvip() {
		return rcvip;
	}

	public void setRcvip(String rcvip) {
		this.rcvip = rcvip;
	}


	public String getSecretKey() {
		return secretKey;
	}


	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	public String getTransactionKey() {
		return transactionKey;
	}


	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}


	public String getSndType() {
		return sndType;
	}


	public void setSndType(String sndType) {
		this.sndType = sndType;
	}


	public String getFileSize() {
		return fileSize;
	}


	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}


	public String getSrcPath() {
		return srcPath;
	}


	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}


	public String getDstPath() {
		return dstPath;
	}


	public void setDstPath(String dstPath) {
		this.dstPath = dstPath;
	}


	public String getRcvPort() {
		return rcvPort;
	}


	public void setRcvPort(String rcvPort) {
		this.rcvPort = rcvPort;
	}


	public String getSndRst() {
		return sndRst;
	}


	public void setSndRst(String sndRst) {
		this.sndRst = sndRst;
	}


	public String getFileHash() {
		return fileHash;
	}


	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	// toString 생성 
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("dstPath:" +this.dstPath);
		sb.append(" fileHash:" +this.fileHash);
		sb.append(" fileName:" +this.fileName);
		sb.append(" fileSize:" +this.fileSize);
		sb.append(" secretKey:" +this.secretKey);
		sb.append(" sndPort:" +this.rcvPort);
		sb.append(" rcvip:" +this.rcvip);
		sb.append(" sndRst:" +this.sndRst);
		sb.append(" sndType:" +this.sndType);
		sb.append(" srcPath:" +this.srcPath);
		sb.append(" transactionKey:" +this.transactionKey);
		
		return sb.toString();
	}

}
