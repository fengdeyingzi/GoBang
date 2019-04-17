package com.xl.gsnkxq;

import com.xl.gdx.XLScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
精灵测试Screen
spritex
*/

public class SpriteScreen extends XLScreen
{
	SpriteX sprite;
    SpriteBatch batch;
	public SpriteScreen(int width,int height)
	{
		super(width,height);
        batch = new SpriteBatch();
        sprite = new SpriteX("sprite/55.sprite","sprite/55.png");
        batch.setProjectionMatrix(getCamera().combined);
	}

    @Override
    public void render(float p1)
    {
        // TODO: Implement this method
        sprite.setAction(0);
        batch.begin();
		sprite.update(System.currentTimeMillis());
		sprite.paint(new Graphics(batch));
        batch.end();
		int count = sprite.getCollidesCount();
		if(count>1){
			sprite.collidesWith(sprite);
		}
        super.render(p1);
    }

    @Override
    public void dispose()
    {
        // TODO: Implement this method
        sprite.dispose();
        super.dispose();
    }
    
    
    
}
