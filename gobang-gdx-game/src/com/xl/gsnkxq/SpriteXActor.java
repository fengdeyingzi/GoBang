package com.xl.gsnkxq;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.Gdx;

public class SpriteXActor extends Actor
{
	SpriteX sprite;
	long time;
	//Graphics gr;
	public SpriteXActor(String spxName, String imageName) {
	   sprite = new SpriteX(spxName, imageName);
	   this.time = 0;
	}
	
	public SpriteXActor(SpriteXActor actor){
		sprite = actor.getSprite();
		this.time = 0;
	}
	
	
	public void act(float delta) {
        super.act(delta);
		time += (long)(delta*1000);
        // 现在这里一般没有什么逻辑要处理
		sprite.update(time);
		//Gdx.app.log("act",""+(int)(delta*1000));
    }
	
	
	/**
     * 绘制演员
     * 
     * @param batch
     *      纹理画布, 用于绘制演员封装的纹理。SpriteBatch 就是实现了 Batch 接口
     * 
     * @param parentAlpha 
     *      父节点的透明度, 处理透明度和演员的颜色属性有关, 稍微复杂, 这里暂时先不处理
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
		Graphics gr = new Graphics(batch);
		sprite.paint(gr);
	}
	
	
	public void setAction(int action){
		sprite.setAction(action);
	}
	
	public int getAction(){
		return sprite.getAction();
	}

	@Override
	public void moveBy(float x, float y)
	{
		// TODO: Implement this method
		super.moveBy(x, y);
		sprite.move(x,y);
	}

	@Override
	public void setPosition(float x, float y)
	{
		// TODO: Implement this method
		super.setPosition(x, y);
		sprite.setPosition(x,y);
	}
	
	public void update(){
		sprite.update();
	}

	public void update(long time){
		sprite.update(time);
	}
	
	
	public int getCollidesCount(){
		return sprite.getCollidesCount();
	}
	
	public SpriteX getSprite(){
		return this.sprite;
	}
	
	public int getFrame(){
		return sprite.getFrame();
	}
	
	public boolean collidesWith(SpriteXActor actor){
		return sprite.collidesWith(actor.getSprite());
	}
	
	public void dispose(){
		sprite.dispose();
	}
}
