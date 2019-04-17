package com.xl.gsnkxq.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xl.gdx.XLScreen;

public class DemoScreen extends XLScreen
 {

	Demo03 demo = null;
	Viewport stretchViewport;

	public DemoScreen(int width,int height){
		super(width,height);
		 stretchViewport = new StretchViewport(width, height);
		 //create();
	}
	
	/*
	@Override
	public void create() {
		
		demo = new Demo03(stretchViewport);
		Gdx.input.setInputProcessor(demo);
	}
	*/

	@Override
	public void render(float p1) {
		demo.draw();
	}

	@Override
	public void dispose() {
		demo.dispose();
		super.dispose();
	}
}
