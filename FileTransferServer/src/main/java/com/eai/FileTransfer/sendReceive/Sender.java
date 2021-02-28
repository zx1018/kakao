package com.eai.FileTransfer.sendReceive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	    
    public void sendMsg(Message msg) {
        jmsTemplate.convertAndSend("beanbroker-queue", msg);
    }
    
    
    public void sendReplyMsg(Message msg) {	
        jmsTemplate.convertAndSend("sendreply-queue", msg);
    }
    
    public void sndRstMsg(Message msg) {	
        jmsTemplate.convertAndSend("finalResponse-queue", msg);
    }
}
