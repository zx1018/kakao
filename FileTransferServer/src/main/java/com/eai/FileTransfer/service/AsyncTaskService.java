package com.eai.FileTransfer.service;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.eai.FileTransfer.sendReceive.Message;
import com.eai.FileTransfer.sendReceive.Sender;
import com.eai.FileTransfer.util.FileTransferReceiver;
import com.eai.FileTransfer.util.FileTransferSender;

import ch.qos.logback.classic.Logger;

@Component
@Service("asyncTaskService")
public class AsyncTaskService {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(AsyncTaskService.class);
	
	@Autowired(required=true)
    private Sender sender;
	
	
	@Async
	public void jobRunningInBackgroundServer(Message msg) {
		log.info("Thread Server Start");
		FileTransferReceiver rcv = new FileTransferReceiver();
		int ret = rcv.open(msg);
		if(ret == 0) {
			msg.setSndRst("Y");
		}
		else {
			msg.setSndRst("N");
		}
		sender.sndRstMsg(msg);
	}
	
	@Async
	public void jobRunningInBackgroundClient(Message msg) {
		log.info("Thread Client Start");
		FileTransferSender snd = new FileTransferSender();
		snd.start(msg);
	}
}
