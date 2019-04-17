package com.xl.gobang;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.xl.gdx.XLGame;
import com.xl.gdx.XLScreen;
import com.xl.gobang.screen.RoomsScreen;

/**
 * 五子棋主程序
 * @author Administrator
 *
 */
public class MainGame extends XLGame{
	
	public static final int SCREEN_WIDTH=720;
	public static final int SCREEN_HEIGHT=480;
	
	XLScreen roomScreen;
	WebSocketClient client=null;
	String url= "ws://119.29.215.145:8001";
	public static final String TAG= "MainGame";
	
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		client= new WebSocketClient(URI.create(url)) {
			
			
			
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				// TODO Auto-generated method stub
				Gdx.app.log(TAG, "onOpen");
				Screen screen= getScreen();
				if(screen!=null && screen instanceof OnConnectListener){
					((OnConnectListener)screen).onOpen(handshakedata);
				}
			}
			
			@Override
			public void onMessage(String message) {
				// TODO Auto-generated method stub
				Gdx.app.log(TAG, "onMessage"+message);
				Screen screen= getScreen();
				if(screen!=null && screen instanceof OnConnectListener){
					((OnConnectListener)screen).onMessage(message);
				}
			}
			
			@Override
			public void onError(Exception ex) {
				// TODO Auto-generated method stub
				Gdx.app.log(TAG, "onError");
				Screen screen= getScreen();
				if(screen!=null && screen instanceof OnConnectListener){
					((OnConnectListener)screen).onError(ex);
				}
			}
			
			@Override
			public void onClose(int code, String reason, boolean remote) {
				// TODO Auto-generated method stub
				Gdx.app.log(TAG, "onClose");
				Screen screen= getScreen();	
				if(screen!=null && screen instanceof OnConnectListener){
					((OnConnectListener)screen).onClose(code, reason, remote);
				}
			}
		};
		
	}
	
	
	public void send(String text){
		client.send(text);
	}
	
	public void send(JSONObject jsonObject) {
		client.send(jsonObject.toString());
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		roomScreen= new RoomsScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
		startScreen(RoomsScreen.class);
		((RoomsScreen)getScreen()).setGame(this);
		roomScreen= (XLScreen) getScreen();
		client.connect();
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		getScreen().dispose();
	}

}
