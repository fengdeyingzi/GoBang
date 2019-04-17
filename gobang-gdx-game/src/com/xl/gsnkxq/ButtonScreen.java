package com.xl.gsnkxq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.xl.gdx.XLScreen;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;


/*
按钮测试
*/
public class ButtonScreen extends XLScreen
{
	Texture texture;
	Stage stage;
	ImageButton Btn_SHOW,Btn_A,Btn_B;
	
	public ButtonScreen(int width,int height)
	{
		super(width,height);
		stage = new Stage(new StretchViewport(width,height));
		setButton();
		/* 事件初始化 */

		// 首先必须注册输入处理器（stage）, 将输入的处理设置给 舞台（Stage 实现了 InputProcessor 接口）
		// 这样舞台才能接收到输入事件, 分发给相应的演员 或 自己处理。
		Gdx.input.setInputProcessor(stage);
	}
	
	
	
	 public void setButton() {
	  texture = new Texture(Gdx.files.internal("data/control.png"));

	 TextureRegion[][] spilt = TextureRegion.split(texture, 64, 64);

	 TextureRegion[] regionBtn = new TextureRegion[6];
	 // 近抗
	 regionBtn[0] = spilt[0][0];
	 regionBtn[1] = spilt[0][1];
	 // 朱缶
	 regionBtn[2] = spilt[0][2];
	 regionBtn[3] = spilt[0][3];
	 // 年係
	 regionBtn[4] = spilt[1][0];
	 regionBtn[5] = spilt[1][1];

	 TextureRegionDrawable Btn_SHOW_UP = new TextureRegionDrawable(
	 regionBtn[0]);
	 TextureRegionDrawable Btn_SHOW_DOWN = new TextureRegionDrawable(
	 regionBtn[1]);

	 TextureRegionDrawable Btn_A_UP = new TextureRegionDrawable(regionBtn[2]);
	 TextureRegionDrawable Btn_A_DOWN = new TextureRegionDrawable(
	 regionBtn[3]);

	 TextureRegionDrawable Btn_B_UP = new TextureRegionDrawable(regionBtn[4]);
	 TextureRegionDrawable Btn_B_DOWN = new TextureRegionDrawable(
	 regionBtn[5]);

	 Btn_SHOW = new ImageButton(Btn_SHOW_UP, Btn_SHOW_DOWN);

	 Btn_A = new ImageButton(Btn_A_UP, Btn_A_DOWN);

	 Btn_B = new ImageButton(Btn_B_UP, Btn_B_DOWN);
   Btn_SHOW.setPosition(30,30);
	 Btn_A.setPosition(getWidth()- 60,60);
	 Btn_B.setPosition(getWidth()- 30,30);
	 stage.addActor(Btn_A);
	 stage.addActor(Btn_B);
	 stage.addActor(Btn_SHOW);
	 
	 }

	 @Override
	 public void render(float p1)
	 {
		 // TODO: Implement this method
		 super.render(p1);
		 Gdx.gl.glClearColor(1,1,1,1);
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		 
		 stage.act(p1);
		 stage.draw();
		 
	 }
	 
	 
	 
	@Override
	public void dispose()
	{
		// TODO: Implement this method
		stage.dispose();
		texture.dispose();
		
	}
	
}
