package com.eai.FileTransfer.sendReceive;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ch.qos.logback.classic.Logger;

@Component
public class Receiver {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(Receiver.class);
	
	@Autowired(required=true)
    private Sender sender;
	
	// client1 snd측 큐를 모니터링 한다.  
	@JmsListener(destination = "src_snd-queue", containerFactory="jsaFactory")
	public void receiveMessage(Message msg){
		log.info("Received Src");
		log.info(msg.toString());
		sender.sndDstMsg(msg);
	}
	
	// client2 snd측 큐를 모니터링 한다.
	@JmsListener(destination = "dst_snd-queue", containerFactory="jsaFactory")
	public void receiveMessageReply(Message msg){
		log.info("Received Dst");
		log.info(msg.toString());
		sender.sndSrcMsg(msg);
	}
}