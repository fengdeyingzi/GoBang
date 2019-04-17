package com.xl.gobang.screen;

import java.util.ArrayList;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xl.gdx.GdxToast;
import com.xl.gdx.XLScreen;
import com.xl.gobang.MainGame;
import com.xl.gobang.OnConnectListener;
import com.xl.gobang.view.FlexGroup;
import com.xl.gobang.view.TextButtonView;

//显示房间
public class RoomsScreen extends XLScreen implements OnConnectListener{

	public static final String TAG = "RoomsScreen";
	Skin skin;
	ArrayList<TextButtonView> list_button_rooms;
	FlexGroup group_buttons;
	Stage stage;
	MainGame game;
	GdxToast toast;
	
	
	public RoomsScreen(int width, int height) {
		super(width, height);
		getStage().setDebugAll(true);
		skin = new Skin(Gdx.files.internal("ui/packer.json"));
		group_buttons= new FlexGroup(width-100, height-100);
		group_buttons.setPosition(50, 50);
		toast= new GdxToast("", skin,"toast");
		stage = getStage();
		stage.addActor(toast);
		toast.setPosition(720/2, 150);
		list_button_rooms= new ArrayList<TextButtonView>();
		for(int i=0;i<10;i++){
			TextButtonView button= new TextButtonView("房间："+i, skin,"default");
			list_button_rooms.add(button);
			
			button.setWidth(200);
			button.setHeight(50);
		}
		group_buttons.setPaddingView(10);
		setRooms();
		//list_button_rooms.get(0).setPosition(0, 100);
		stage.addActor(group_buttons);
		Gdx.input.setInputProcessor(stage);
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
				TextButtonView button= new TextButtonView(room_name, skin,"default");
				button.setWidth(200);
				button.setHeight(50);
				button.setId(roomId);
				button.setName(room_name);
				button.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						// TODO Auto-generated method stub
						
						Actor actor= event.getListenerActor();
						if(actor instanceof TextButtonView){
							TextButtonView view= (TextButtonView) event.getListenerActor();
						joinRoom(view.getId(), view.getName());
						System.out.println("加入房间"+view.getName());
						}
						else {
							System.out.println("点击失败"+actor.getName());
						}
						
						
						
					}
				});
				button.setName(""+roomId);
				group_buttons.addActor(button);
			}
			group_buttons.invalidate();
		}
		else if(action.equals("joinRoom")){
			int userid= object.getInt("userid");
			String user= object.getString("user");
			int roomid= object.getInt("roomid");
			String roomname= object.getString("roomName");
			toast.setText(user+"加入了房间");
			toast.show();
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
	
	//加入房间
	public void joinRoom(int roomid, String roomName) {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("action", "joinRoom");
		jsonObject.put("roomId", roomid);
		jsonObject.put("roomName", roomName);
		game.send(jsonObject);
	}
	
	//获取棋盘数据
	public void getChessBoardData() {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("action", "getChessboardData");
		jsonObject.put("type", "chars");
		game.send(jsonObject);
	}
	
	

}
