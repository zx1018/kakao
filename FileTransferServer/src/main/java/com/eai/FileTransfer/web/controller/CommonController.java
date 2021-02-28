package com.eai.FileTransfer.web.controller;

import java.io.File;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eai.FileTransfer.sendReceive.Message;
import com.eai.FileTransfer.sendReceive.Sender;

import ch.qos.logback.classic.Logger;
 
@RestController
public class CommonController {
	
	private static Logger log = (Logger) LoggerFactory.getLogger(CommonController.class);
	
	@Autowired(required=true)
    private Sender sender;
    
    @RequestMapping("/")
    public String root_test() throws Exception{
    	
    	// �޼��� ��ü�� ���� ���� 
    	Message msg = new Message();
    	
    	
    	
    	File file = new File("F:\\1.txt");
    	File dstfile = new File("2.txt");
    	if (!file.exists()) {
    		log.info("File Not Exist");
    		return "File Not Exist";
        }
    	else{
	    	msg.setFileName(file.getName());
	    	msg.setSrcPath(file.getPath());
	    	msg.setDstPath(dstfile.getPath());
	    	msg.setFileHash(String.valueOf(file.hashCode()));
	    	msg.setFileSize(String.valueOf(file.length()));
//	    	Random random = new Random(); //���� ��ü ����(����Ʈ �õ尪 : ����ð�)
//	        random.setSeed(System.currentTimeMillis()); //�õ尪 ������ ���� �Ҽ��� ����
	    	msg.setSecretKey("696d697373796f7568616e6765656e61");
//	    	msg.setSndType("socket");
	    	msg.setSndType("FTP");
	    	msg.setTransactionKey("1");
	    	
	    	log.info(msg.toString());	
	        sender.sendMsg(msg);
	        
	        return "Hello Root(/)";
	    	
    	}
    }
 
    @RequestMapping("/demo")
    public String demo_test() throws Exception{
    	
        return "Hello demo(/demo)";
    }
 
}

