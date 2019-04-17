package com.xl.gobang.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.xl.gdx.XLScreen;
import com.xl.gobang.view.FlexGroup;

//显示房间
public class RoomsScreen extends XLScreen{

	public static final String TAG = "RoomsScreen";
	Skin skin;
	ArrayList<TextButton> list_button_rooms;
	FlexGroup group_buttons;
	Stage stage;
	
	
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
		ha
	}
	
	
	
	
	

}
