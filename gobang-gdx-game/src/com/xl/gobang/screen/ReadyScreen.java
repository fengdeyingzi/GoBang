package com.xl.gobang.screen;

import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xl.gdx.XLGame;
import com.xl.gdx.XLScreen;
import com.xl.gobang.MainGame;
import com.xl.gobang.UserInfo;

public class ReadyScreen extends XLScreen{

	TextButton button_reday;
	Image img_ren1,img_ren2;
	Image img_hand1,img_hand2;
	MainGame game;
	Skin skin;
	
	
	
	public ReadyScreen(int width, int height) {
		super(width, height);
		skin = new Skin(Gdx.files.internal("ui/packer.json"));
		
		button_reday= new TextButton("准备", skin,"button");
		button_reday.setPosition(300, 50);
		
		img_ren1 = new Image(new Texture(Gdx.files.internal("ren.png")));
		img_ren2 = new Image(new Texture(Gdx.files.internal("ren.png")));
		img_hand1 = new Image(new Texture(Gdx.files.internal("handle.png")));
		img_hand2 = new Image(new Texture(Gdx.files.internal("handle.png")));
		img_ren1.setPosition(20, 200);
		img_ren2.setPosition(30, 20);
		img_hand1.setPosition(300, 300);
		img_hand2.setPosition(300, 30);
		img_hand1.setVisible(false);
		img_hand2.setVisible(false);
		
		getStage().addActor(button_reday);
		getStage().addActor(img_ren1);
		getStage().addActor(img_ren2);
		getStage().addActor(img_hand1);
		getStage().addActor(img_hand2);
		button_reday.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
			}
		});
		
		Gdx.input.setInputProcessor(getStage());
		// TODO Auto-generated constructor stub
	}
	
	//准备游戏
	protected void ready() {
		img_hand2.setVisible(true);
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("action", "prepare");
		jsonObject.put("userId", UserInfo.userId);
		this.game.send(jsonObject);
	}
	
	
	//开始游戏
	private void startGame() {
		startScreen(BoardScreen.class);
	}
	
	//对方已准备
	private void userReady() {
		img_hand1.setVisible(true);
	}
	
	
	
	public void setGame(MainGame game) {
		this.game= game;
	}
	
	
	

}
