package com.niit.colloborativebackend.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.colloborativebackend.model.Message;
import com.niit.colloborativebackend.model.OutputMessage;

@Controller
public class ChatController {
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {
		System.out.println("Calling the method sendMessage().");
		
		
	//	message.setUserId(userId);
		System.out.println("Message : "+message.getMessage());
		
		System.out.println("Message ID : "+message.getId());
System.out.println("Message userId: "+message.getUserId());
				
		return new OutputMessage(message, new Date());

	}
	
}



