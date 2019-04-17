package com.xl.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.xl.gsnkxq.Graphics;
import com.xl.gsnkxq.SpriteX;

public class SpriteActor extends Actor
{
	SpriteX sprite;
	public SpriteActor(String spritepath,String imgpath)
	{
		sprite = new SpriteX(spritepath,imgpath);
		sprite.setAction(0);
	}
	
	public void setAction(int action){
		sprite.setAction(action);
	}

	@Override
	public void setPosition(float x, float y)
	{
		// TODO: Implement this method
		super.setPosition(x, y);
		sprite.setPosition((int)x,(int)y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		// TODO: Implement this method
		super.draw(batch, parentAlpha);
		sprite.paint(new Graphics(batch));
		
	}

	@Override
	public void act(float delta)
	{
		// TODO: Implement this method
		super.act(delta);
		sprite.update(System.currentTimeMillis());
		
	}
	
	
	
}
