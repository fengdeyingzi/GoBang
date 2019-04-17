package com.xl.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.xl.gdx.XLScreen;

import com.badlogic.gdx.utils.viewport.Viewport;
/*
游戏摇杆测试

*/
public class TouchPadScreen extends XLScreen {
	Stage stage;
	Touchpad touchpad;
	Texture texture;
	Texture killer;
	SpriteBatch batch;
    Viewport viewport;
	
	TextureRegionDrawable background;
	TextureRegionDrawable knobRegion;
	TouchpadStyle touchpadStyle;

	int speed = 3;
	int x = 0;
	int y = 0;
	
	public TouchPadScreen(int w,int h){
		super(w,h);
		create();
	}

	public void create() {
		this.viewport = new StretchViewport(getWidth(),getHeight());
		
		stage = new Stage(viewport);

		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("data/touchpad.png"));

		killer = new Texture(Gdx.files.internal("data/2.png"));

		background = new TextureRegionDrawable(new TextureRegion(texture, 0, 0,
																 128, 128));
		knobRegion = new TextureRegionDrawable(new TextureRegion(texture, 128+30,
																 0+30, 128-60, 128-60));

		touchpadStyle = new TouchpadStyle(background, knobRegion);

		touchpad = new Touchpad(60, touchpadStyle);
//设置显示范围
		touchpad.setBounds(0, 0, 128, 128);
touchpad.setPosition(120,120);
		stage.addActor(touchpad);
        stage.setViewport(viewport);
		
		TestActor testActor = new TestActor();
		testActor.setRegion(new TextureRegion(killer,0,0,8,8));
		stage.addActor(testActor);
		testActor.setSize(300,300);
		touchpad.setZIndex(100);
		testActor.setRotation(30);
		testActor.setOrigin(150,150);
	}

	public void update() {
		if (touchpad.isTouched()) {
			x += touchpad.getKnobPercentX() * speed;
			y += touchpad.getKnobPercentY() * speed;
		}
		if(x<0)x=0;
		if(x>getWidth())x=getWidth();
		if(y<0)y=0;
		if(y>getHeight())y=getHeight();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render(float p1) {
		super.render(p1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		batch.draw(killer, x-25,y-25, 50, 50);
		batch.end();

		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
		this.viewport.update(width,height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
