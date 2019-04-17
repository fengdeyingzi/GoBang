package com.xl.gsnkxq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xl.gdx.BaseResources;
import com.xl.gdx.XLScreen;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MenuScreen extends XLScreen
{
	TextButton btn_sprite, btn_map, btn_loadui, btn_draw,btn_fontdraw, btn_pixmap;
	Stage stage;
	Viewport viewport;
	public MenuScreen(int width,int height)
	{
		super(width,height);
		Gdx.app.log("Menu","width:"+width+" "+height);
		btn_sprite = createTextButton("精灵",300,80);
		btn_sprite.setPosition(20,100);
		
		btn_map = createTextButton("地图",300,80);
		btn_map.setPosition(20,200);
		
		btn_draw = createTextButton("图形绘制",300,80);
		btn_draw.setPosition(20,300);
		
		btn_loadui = createTextButton("加载布局",300,80);
		btn_loadui.setPosition(20,400);
		
		btn_fontdraw = createTextButton("文字绘制",300,80);
		btn_fontdraw.setPosition(20,500);
		
		this.viewport = new StretchViewport(width,height);
		stage = new Stage(viewport);
		stage.addActor(btn_sprite);
		stage.addActor(btn_map);
		stage.addActor(btn_draw);
		stage.addActor(btn_loadui);
		stage.addActor(btn_fontdraw);
		Gdx.input.setInputProcessor(stage);
		
		btn_sprite.addListener(new ClickListener()
		{
                public void clicked(InputEvent event,float x,float y)
                {
                    startScreen(SpriteScreen.class);
                }
			
		});
		
		btn_map.addListener(new InputListener()
		{
			public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
			{
			//startScreen(
			return true;
			}
			public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
			{
startScreen(MapScreen.class);
			}
		});
		
		btn_draw.addListener(new InputListener()
		{
			public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
			{
			//startScreen(
			return true;
			}
			public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
			{
startScreen(DrawLineScreen.class);
			}
		});
		
		btn_loadui.addListener(new InputListener()
		{
			public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
			{
			//startScreen(
			return true;
			}
			public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
			{
startScreen(LoadUIScreen.class);
			}
		});
	btn_fontdraw.addListener(new ClickListener()
	{
		public void clicked(InputEvent event,float x,float y)
		{
			startScreen(FontDrawScreen.class);
		}
	});
	
		
	}

	@Override
	public void render(float p1)
	{
	// TODO: Implement this method
	//清除画布
	Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
	
	stage.act(p1);
	stage.draw();
	super.render(p1);
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
		super.resize(width, height);
		this.viewport.update(width,height);
	}
	
	

	@Override
	public void dispose()
	{
	// TODO: Implement this method
	super.dispose();
	stage.dispose();
	}
	
	
	
	
	
	//创建圆角按钮
	//参数：按钮文本，按钮宽高
	public TextButton createTextButton(String text,int width,int height)
	{
	TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
	style.down = BaseResources.drawable_button_down;
	//style.over = BaseResources.texture_toast_background;
	style.font = BaseResources.font;
	style.fontColor = new Color(0x505050ff);
	style.up = BaseResources.drawable_button_up;
	//style.fo
	TextButton btn_test = new TextButton(text,style);

	btn_test.setWidth(width);
	btn_test.setHeight(height);
	return btn_test;
	}
	
	
	
	
}
