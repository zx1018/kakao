package com.eai.FileTransfer.sendReceive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	    
    public void sndDstMsg(Message msg) {	
        jmsTemplate.convertAndSend("dst_rcv-queue", msg);
    }
    
    public void sndSrcMsg(Message msg) {	
        jmsTemplate.convertAndSend("src_rcv-queue", msg);
    }
}
