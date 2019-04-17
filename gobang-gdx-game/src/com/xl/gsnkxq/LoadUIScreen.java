package com.xl.gsnkxq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.xl.gdx.BaseResources;
import com.xl.gdx.GdxToast;
import com.xl.gdx.XLScreen;
import com.xl.gsnkxq.tool.FpsCheck;
import com.xl.gsnkxq.ui.IniSource;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class LoadUIScreen extends XLScreen
{
	IniSource source =null;
	Stage stage;
	SpriteBatch batch;
	BitmapFont bitmapFont;
	FpsCheck fps;
	TextButton btn_hello;
	
	public LoadUIScreen(int width,int height)
	{
		super(width,height);
		fps = new FpsCheck();
		//加载布局
		source = new IniSource("ui/btn_start.ini",width,height);
		stage = new Stage(new StretchViewport(width,height));
	GdxToast toast=GdxToast.makeToast("aaddjehhdbdhd",0);
	toast.setPosition(getWidth()/2-toast.getWidth()/2,300);

	toast.show();
	stage.addActor(toast);
		//
		Gdx.input.setInputProcessor(stage);
		//将解析的控件添加到舞台
		source.addToStage(stage);
		
		//btn_test.setPosition(230,getHeight()-btn_test.getHeight()-300);
		btn_hello = createTextButton("hello",200,80);
		stage.addActor(btn_hello);
		
		
		batch = new SpriteBatch();
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false);
		batch.setProjectionMatrix(getCamera().combined);
	}

	@Override
	public void render(float p1)
	{
		// TODO: Implement this method
		//清除画布
		Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		stage.act(p1);
		fps.act(p1);
		
		stage.draw();
		
		batch.begin();
		
		bitmapFont.draw(batch,fps.toString() ,10,getHeight());
		batch.end();
		super.render(p1);
	}
	
	

	@Override
	public void dispose()
	{
		// TODO: Implement this method
		super.dispose();
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
