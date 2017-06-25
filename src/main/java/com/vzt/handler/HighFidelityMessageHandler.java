package com.vzt.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HighFidelityMessageHandler extends TextWebSocketHandler{
	
	
	Map<String, WebSocketSession> sessionMap = new HashMap<>();
	
	Map<String, String> deviceMap = new HashMap<>();
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private int messageReceived = 0;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {	
		System.out.println("Connection Opened " + session.getId());
		sessionMap.put(session.getId(), session);
		//session.sendMessage(new TextMessage("Responce From Server : Connection Opened"));
		//integ.asyncRoute("enableDisableDeviceProducer", new DeviceDataMessage(session.getId(), "ACTIVE", session.getId()), null);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		messageReceived++;
		System.out.println("Received Message " + messageReceived + " Message : " + message);
		session.sendMessage(message);
		//HighFidelityRequest req = objectMapper.readValue(message.getPayload(),HighFidelityRequest.class);
		//headerMap.put(session.getId(), req);
		deviceMap.put(session.getId(), "1");
		//integ.asyncRoute("enableDisableDeviceProducer", new DeviceDataMessage(req.getDataArea().getDeviceIMEI(), "ACTIVE", session.getId()), null);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Connection Closed " + session.getId());
		System.out.println("Connection Code : " + status.getCode() + " Connection Reason " + status.getReason());		
		sessionMap.remove(session.getId());
		deviceMap.remove(session.getId());
	}

	public Map<String, WebSocketSession> getSessionMap() {
		return sessionMap;
	}

	
	

}
