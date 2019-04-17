package com.xl.gsnkxq;

//import android.util.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xl.gdx.XLGame;
import com.xl.gsnkxq.ui.MaskTest;
import com.xl.gdx.GdxToast;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.xl.gdx.BaseResources;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.xl.gdx.UIUtil;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.xl.gsnkxq.box2d.DemoScreen;
import com.xl.ui.TouchPadScreen;
import com.xl.ui.CheckBoxScreen;

/*
游戏主进程

*/
public class MainGame extends XLGame
{
	PlatformResolver plat;
//    Screen scr_main;
//	Screen scr_game;
	Texture img_black;
	Batch batch;
	//int width = 320;
//	int height=480;
	GameTask gameTask;
	//BitmapFont font;
	
    //设置横屏和竖屏
    private boolean isHor;
	
	
	int SCREEN_WIDTH=1024,
    SCREEN_HEIGHT=540;
	
	float zoom;
	@Override
	public void create()
	{
		//super.create();
	super.create();
		FileLog.delete();
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		//初始化字体文件
		//font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"),Gdx.files.internal("fonts/font.png"),false);
		
		//BaseResources.texture_toast_background = new TextureRegionDrawable(new TextureRegion( new Texture(Gdx.files.internal("ui/toast_background_white.png"))));
		Color color_grey = new Color(0xd0d0d0ff);
		Color color_white = new Color(0xf0f0f0ff);
		TextureRegion re= new TextureRegion(new Texture(UIUtil.getPixmapRoundedRectangle(90,90,20,Color.WHITE)));
		NinePatch niewpath = new NinePatch(re,30,30,30,30);
		BaseResources.drawable_toast_background = new NinePatchDrawable(niewpath);
		//TextureRegion re= new TextureRegion(new Texture(UIUtil.getPixmapRoundedRectangle(90,90,20,Color.WHITE)));
		BaseResources.drawable_button_up = UIUtil.createRoundedRectDrawable(60,60,8,color_white);
		BaseResources.drawable_button_down = UIUtil.createRoundedRectDrawable(60,60,8,color_grey);
		
		gameTask = new GameTask();
		ViewTask ahpa1 = new ViewTask();
		ahpa1.setData(0,255,1);
		ViewTask ahpa2 = new ViewTask();
		ahpa2.setData(255,255,1);

		ViewTask ahpa3 = new ViewTask();
		ahpa3.setData(255,0,1);
		gameTask.addTask(ahpa1,new GameTask.OnTaskListener()
			{

				@Override
				public void onTaskOk(TaskItem item)
				{
					// TODO: Implement this method
				}


			});
		//gameTask.addTask(ahpa2,null);
		gameTask.addTask(ahpa3,null);
		//FileLog.e("MainScreen create");
		//scr_main = new MapScreen();
		//setScreen(scr_main);
		//Screen drawScreen = new DrawLineScreen(SCREEN_WIDTH,SCREEN_HEIGHT);

		//setScreen(new FontDrawScreen(SCREEN_WIDTH,SCREEN_HEIGHT));
		
		img_black = new Texture("img/black.png");
		batch = new SpriteBatch();
		//Log.e("MyGdxGame",""+Gdx.app.getVersion());
		
		//plat.showQucikDialog("","hh",null);
		
		
	}

	@Override
	public void init()
	{
		// TODO: Implement this method
		super.init();
		//this.SCREEN_WIDTH = getGameWidth();
		//this.SCREEN_HEIGHT = getGameHeight();
		//setScreen(new FontDrawScreen(SCREEN_WIDTH,SCREEN_HEIGHT));
		//setScreen(new MaskTest());
		//setScreen(new ButtonScreen(SCREEN_WIDTH,SCREEN_HEIGHT));
		//setScreen(new MapScreen(SCREEN_WIDTH,SCREEN_HEIGHT));
		//setScreen(new LoadUIScreen(SCREEN_WIDTH,SCREEN_HEIGHT));
        //横屏
		
		if(Gdx.graphics.getWidth()> Gdx.graphics.getHeight())
		{
			setScreenSize(SCREEN_HEIGHT * Gdx.graphics.getWidth()/Gdx.graphics.getHeight(), SCREEN_HEIGHT);
		}
		//竖屏
		else
		{
			setScreenSize(SCREEN_HEIGHT * Gdx.graphics.getHeight()/Gdx.graphics.getWidth(), SCREEN_HEIGHT);
		}
		
		
		startScreen(GameMenuScreen.class);
		//startScreen(DemoScreen.class);
		//startScreen(TouchPadScreen.class);
		//startScreen(TextRectScreen.class);
	
	}
	
	

	@Override
	public void render()
	{  
	
	  super.render();
		
			batch.setProjectionMatrix( getCamera().combined );
			
			int apha = 0;
			gameTask.logoc(Gdx.graphics.getDeltaTime());
			TaskItem item = gameTask.getTask();
			if(item!=null)
			{
				/*
					if(item instanceof ViewTask)
					{
							 apha = ((ViewTask)item).getApha();
							batch.setColor(1f,1.0f,1f,apha/255f);
							batch.begin();
							batch.draw(img_black,0,0,width,height);
							batch.end();
					}
					*/
			}
			batch.begin();
			//toast.draw(batch,0.5f);
			batch.end();
	}

	@Override
	public void dispose()
	{
		getScreen().dispose();
		//FileLog.e("MainScreen dispose");
		super.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width,height);
        
		/*
			zoom = ((float)this.width)/width;
			camera.zoom = 1.0f;
			this.height = this.width * height/width;
			camera.viewportWidth = this.width;
			camera.viewportHeight = this.height;
			//camera.setToOrtho( false, this.width, this.height);
			//camera.viewportHeight=this.height;
			*/
	}

	@Override
	public void pause()
	{
		//FileLog.e("MainScreen pause");
		super.pause();
	}

	@Override
	public void resume()
	{
		//FileLog.e("MainScreen resume");
		super.resume();
	}
	
	public void setPlatFrom(PlatformResolver plat)
	{
		this.plat = plat;
	}
	
}
