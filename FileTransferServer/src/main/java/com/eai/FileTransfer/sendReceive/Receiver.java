package com.eai.FileTransfer.sendReceive;

import java.net.InetAddress;
import java.net.ServerSocket;

import org.apache.tomcat.jni.Socket;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.eai.FileTransfer.service.AsyncTaskService;
import com.eai.FileTransfer.web.controller.CommonController;

import ch.qos.logback.classic.Logger;



@Component
public class Receiver {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Receiver.class);
	
	@Autowired(required=true)
    private Sender sender;

	@Autowired(required=true)
    private AsyncTaskService service;
	
	// 파일 전송 요청 큐 
	@JmsListener(destination = "beanbroker-queue", containerFactory="jsaFactory")
	public void receiveMessage(Message msg){
		log.info("Received ");
		log.info(msg.toString());
			 
		try {
			int port = 10344;                                // 일반 소켓 포트 
			if("FTP".equals(msg.getSndType())) { 
				port = 21;                                   // ftp 포트 
			}
			else {
				(new ServerSocket(port)).close();
	            // 소켓 생성 오류가 없다면 응답을 주자
				service.jobRunningInBackgroundServer(msg);
			}
		    
            // 자신의 IP 조사 
            InetAddress local = InetAddress.getLocalHost();
            msg.setRcvPort(String.valueOf(port));
            msg.setRcvip(local.getHostAddress());
            sender.sendReplyMsg(msg);
		}
		catch(Exception e) {
			e.printStackTrace();
			// 소켓 생성 오류가 나면 
			log.info("Socket Create Error");
        	msg.setSndRst("9");
        	sender.sendReplyMsg(msg);
		}
	}
	
	// 파일 전송 시작 메세지 큐 
	@JmsListener(destination = "sendreply-queue", containerFactory="jsaFactory")
	public void receiveMessageReply(Message msg){
		log.info("Received Reply");
		log.info(msg.toString());
		
		if("9".equals(msg.getSndRst())) {
			// 파일 전송 실패 
		}
		else {
			service.jobRunningInBackgroundClient(msg);
		}
	}
	
	// 파일 전송 시작 메세지 큐 
	@JmsListener(destination = "finalResponse-queue", containerFactory="jsaFactory")
	public void sndRstMsg(Message msg){
		log.info("finalResponse Message");
		log.info(msg.toString());
		if("Y".equals(msg.getSndRst())) {
			log.info("File Transfer Complete");
		}
	}
}