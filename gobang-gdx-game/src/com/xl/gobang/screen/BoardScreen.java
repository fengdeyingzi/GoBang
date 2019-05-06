package com.xl.gobang.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xl.gdx.XLScreen;
import com.xl.gobang.view.ChessBoardView;
import com.xl.gobang.view.TextButtonView;

public class BoardScreen extends XLScreen{
	
	Stage stage;
	ChessBoardView boardView;
	Skin skin;
	TextButton button_text;
	
	public BoardScreen(int width,int height){
		super(width, height);
		skin = new Skin(Gdx.files.internal("ui/packer.json"));
		stage= getStage();
		boardView= new ChessBoardView();
		stage.addActor(boardView);
		boardView.setWidth(width-10);
		boardView.setHeight(height-10);
		stage.setDebugAll(true);
		
		button_text = new TextButtonView("房间：", skin,"default");
		button_text.setPosition(30, 30);
		stage.addActor(button_text);
		boardView.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				int ix= boardView.getBoardX((int)x);
				int iy= boardView.getBoardY((int)y);
				if(ix>=0&& ix<16 && iy>=0 && iy<16)
				boardView.drop(ix, iy, boardView.BOARD_BLACK);
			}
		});
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		
	}

}
