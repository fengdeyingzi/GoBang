package com.xl.gsnkxq;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AboutDialog extends Dialog
{
	public AboutDialog(java.lang.String title, com.badlogic.gdx.scenes.scene2d.ui.Skin skin) {
		super("",skin);
		Texture texture = new Texture(Gdx.files.internal("btn_close.png"));
		
		ImageButton btn_back = new ImageButton(new SpriteDrawable(new Sprite(texture)));
		
		Label label = new Label("风的影子 制作\n游戏开发QQ交流群：496717720",skin,"dialog");
		
		setSize(520,380);
		add(label);
	    /*
		row();
		row();
		add(text);
		row();
		add(text2);
		row();
		row();
		row();
		text.setColor(Color.WHITE);
		text2.setColor(Color.WHITE);
		*/
		btn_back.setPosition(getWidth()-150, getHeight()-100);
		//btn_back.setZIndex(1000);
		setOrigin(getWidth()/2,getHeight()/2);
		setColor(1,1,1,0);
		addActor(btn_back);
		btn_back.addListener(new ClickListener(){
				public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
					hide();
				}
				public boolean keyUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, int keycode) {
					if(keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE){
						hide();
						return true;
					}
					return false;
				}
		});
	}
	public void draw(com.badlogic.gdx.graphics.g2d.Batch batch, float parentAlpha) {
		super.draw(batch,parentAlpha);
		if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
			hide();
		}
	}
	
	
}
