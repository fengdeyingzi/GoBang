package com.xl.gobang;

import org.java_websocket.handshake.ServerHandshake;

public interface OnConnectListener {
	
	public void onOpen(ServerHandshake handshakedata);
	
	public void onMessage(String msg);
	
	public void onClose(int code, String reason, boolean remote );
	
	public void onError(Exception e);

}
