package com.xl.gobang;

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
	
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
	}
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		roomScreen= new RoomsScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
		startScreen(RoomsScreen.class);
		roomScreen= (XLScreen) getScreen();
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
