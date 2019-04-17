package com.xl.gsnkxq;

import com.xl.gdx.XLScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Input;
import java.util.ArrayList;

/*
 游戏主界面Screen
 spritex
 */

public class GameScreen extends XLScreen
{
	Viewport viewport;
	SpriteXActor sprite;
	SpriteXActor spr_hero;
	int desition; //主角方向
	int _UP=2,_DOWN=3,_LEFT=4,_RIGHT=5;
	SpriteXActor spr_ciwei;
	ArrayList<SpriteXActor> list_entry;
	Graphics gr;
	Label text_log;
	
	Skin skin;
	Touchpad touchpad;
	Button touchpad_button;
    SpriteBatch batch;
	Image img_background;
	Stage stage ;
	int fps = 30;
	
	public GameScreen(int width,int height)
	{
		super(width,height);
		this.viewport = new StretchViewport(width,height);
		list_entry = new ArrayList<SpriteXActor>();
		
		stage = new Stage(viewport);
		
        batch = new SpriteBatch();
		Gdx.app.log("","开始加载skin");
		
		skin = new Skin(Gdx.files.internal("ui/packer.json"));
		Gdx.app.log("","加载skin成功");
		
        text_log = new Label("屏幕大小"+width+"*"+height,skin,"default");
		
		sprite = new SpriteXActor("sprite/55.sprite","sprite/55.png");
        spr_hero = new SpriteXActor("sprite/hero.sprite", "sprite/hero.png");
		spr_ciwei = new SpriteXActor("sprite/ciwei.sprite", "sprite/ciwei.png");
		spr_ciwei.setAction(2);
		
		spr_hero.setAction(1);
		spr_hero.setPosition(150,50);
		spr_ciwei.setPosition(width,50);
		list_entry.add(spr_ciwei);
		batch.setProjectionMatrix(getCamera().combined);
		gr = new Graphics(batch);
		touchpad = new Touchpad(20,skin);
		touchpad.setPosition(40,30);
		touchpad_button = new Button(skin,"pad");
		touchpad_button.setPosition(getWidth()-280,60);
		touchpad_button.addListener(new ClickListener(){
			public void clicked(InputEvent e,float x,float y){
				kan();
			}
		});
		
		
		Texture texture_background = new Texture(Gdx.files.internal("img/background.png"));
		img_background =new Image(texture_background);
		img_background.setPosition((width-img_background.getWidth())/2, (height-img_background.getHeight())/2);
		
		stage.addActor(img_background);
		
		stage.addActor(spr_hero);
		stage.addActor(spr_ciwei);
		stage.addActor(touchpad);
		stage.addActor(touchpad_button);
		stage.addActor(text_log);
		Gdx.input.setInputProcessor(stage);
	}
	
	//主角攻击
	public void kan(){
		if(desition == _LEFT){
			spr_hero.setAction(4);
		}
		else if(desition == _RIGHT){
			spr_hero.setAction(3);
		}
		
	}
	
	public void logoc(){
		
		spr_ciwei.moveBy(-2,0);
		
		//砍动画结束之后，恢复到初始状态
		if(spr_hero.getAction()>=3 && spr_hero.getFrame()==1){
			spr_hero.setAction(0);
		}
		
		if(spr_hero.collidesWith(spr_ciwei)){
			text_log.setText("碰撞");
			spr_ciwei.setVisible(false);
		}
		else{
			text_log.setText("主角位置："+spr_hero.getX()+","+spr_hero.getY());
		}
		
		
		
		
		if (touchpad.isTouched()) {
		float px = touchpad.getKnobPercentX();
		float py = touchpad.getKnobPercentY();
		if(spr_hero.getAction()<3){
		//上
		if((px>=0 && py>0 && py>px) || (px<0 && py>0 && py>(-px))){
			spr_hero.moveBy(0,2);
		}
		//下
		if((py<0 && px>0 && (-py)>px) || (py<0 && px<0 && (-py)>(-px))){
			spr_hero.moveBy(0,-2);
		}
		//左
		if((px<0 && py>0 && (-px)>py) || (px<0 && py<0 && (-px)>(-py))){
			if(spr_hero.getAction()!=2)
			spr_hero.setAction(2);
			spr_hero.moveBy(-3,0);
			desition = _LEFT;
		}
		//右
		if((px>0 && py>0 && px>py) || (px>0 && py<0&& px>(-py))){
			if(spr_hero.getAction()!=1)
			spr_hero.setAction(1);
			spr_hero.moveBy(3,0);
			desition = _RIGHT;
		}
		}
		
		}
	}

	float ptime=0;
    @Override
    public void render(float p1)
    {
        // TODO: Implement this method
		float ff = 1f/fps;
		ptime+=p1;
		if(ptime > ff){
			ptime-=ff;
			logoc();
		}
		stage.act(p1);
		//spr_hero.update(System.currentTimeMillis());
		stage.draw();
		
		long time = System.currentTimeMillis();
        sprite.setAction(0);
		
        batch.begin();
		//sprite.update(System.currentTimeMillis());
		//spr_hero.update(time);
		//bitmapFont.draw(batch, "测试"+getWidth()+"*"+getHeight() ,10,getHeight()-100);
        //touchpad.draw(batch, 1);
		batch.end();
		
		int count = sprite.getCollidesCount();
		if(count>1){
			sprite.collidesWith(sprite);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
			startScreen(GameMenuScreen.class);
			dispose();
		}
		
        super.render(p1);
    }

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
		super.resize(width, height);
		stage.getViewport().update(width, height);
	}
	
	

    @Override
    public void dispose()
    {
        // TODO: Implement this method
        sprite.dispose();
		//spr_hero.dispose();
		//spr_ciwei.dispose();
		stage.dispose();
		skin.dispose();
        super.dispose();
    }



}
