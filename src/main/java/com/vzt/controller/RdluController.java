package com.vzt.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RdluController {	

	//SimpleTextMessage

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@RequestMapping(value="/greeting",method=RequestMethod.POST)
	public void greetingMessage(String message){
		System.out.println(message);
	}
	
	@MessageMapping("/startDevice")
	public void startDevice(String request) throws Exception {	
		for(int i = 0; i<100; i++){
			Thread.sleep(2000);
			this.messagingTemplate.convertAndSend("/topic/greetings/", request);
		}		
	}
	
	@MessageMapping("/stopDevice")
	public void stopDevice(String request) throws Exception {		
		
	}

}
