package com.xl.gobang.screen;

import java.util.ArrayList;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.xl.gdx.XLScreen;
import com.xl.gobang.MainGame;
import com.xl.gobang.OnConnectListener;
import com.xl.gobang.view.FlexGroup;

//显示房间
public class RoomsScreen extends XLScreen implements OnConnectListener{

	public static final String TAG = "RoomsScreen";
	Skin skin;
	ArrayList<TextButton> list_button_rooms;
	FlexGroup group_buttons;
	Stage stage;
	MainGame game;
	
	
	public RoomsScreen(int width, int height) {
		super(width, height);
		getStage().setDebugAll(true);
		skin = new Skin(Gdx.files.internal("ui/packer.json"));
		group_buttons= new FlexGroup(width-100, height-100);
		group_buttons.setPosition(50, 50);
		stage = getStage();
		list_button_rooms= new ArrayList<TextButton>();
		for(int i=0;i<10;i++){
			TextButton button= new TextButton("房间："+i, skin,"default");
			list_button_rooms.add(button);
			button.setWidth(200);
			button.setHeight(50);
		}
		group_buttons.setPaddingView(10);
		setRooms();
		//list_button_rooms.get(0).setPosition(0, 100);
		stage.addActor(group_buttons);
		// TODO Auto-generated constructor stub
	}
	
	public void setGame(MainGame game){
		this.game= game;
	}
	
	//布局房间
	private void setRooms(){
		for(int i=0;i<list_button_rooms.size();i++){
			TextButton button= list_button_rooms.get(i);
			button.setText(""+i+"房间："+i);
			group_buttons.addActor(button);
		}
		group_buttons.invalidate();
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		skin.dispose();
		
	}
	
	//向libgdx线程发送数据
	public void sendText(String text){
		Gdx.app.postRunnable(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		//发送登陆数据
		enterGoBang();
	}

	@Override
	public void onMessage(String msg) {
		// TODO Auto-generated method stub
		JSONObject object= new JSONObject(msg);
		String action= object.getString("action");
		if(action.equals("login")){
			boolean isLogin= object.getBoolean("isLogin");
			if(isLogin)
			getRooms();
		}
		if(action.equals("roomList")){
			
			int page=object.getInt("page"); //0
			JSONArray list=object.getJSONArray("list"); //[
			group_buttons.clear();
			for(int i=0;i<list.length();i++){
				JSONObject obj_room= list.getJSONObject(i);
				String room_name= obj_room.getString("roomName");
				int roomId = obj_room.getInt("roomId");
				TextButton button= new TextButton(room_name, skin,"default");
				button.setWidth(200);
				button.setHeight(50);
				group_buttons.addActor(button);
			}
			group_buttons.invalidate();
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Exception e) {
		// TODO Auto-generated method stub
		
	}
	
	//
	public void getRooms() {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("action", "getRoomList");
		jsonObject.put("page", 0);
		game.send(jsonObject);
	}
	
	//登陆
	public void enterGoBang(){
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("action", "login2");
		jsonObject.put("userName", "影子");
		jsonObject.put("type", "json");
		jsonObject.put("uuid", "0000000000000000");
		game.send(jsonObject);
	}
	
	

}
