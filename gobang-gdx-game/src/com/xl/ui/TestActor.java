package com.xl.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TestActor extends Actor
{
	// 用于展示该演员的纹理区域
    private TextureRegion region;
	//每秒移动速度
	private int speed;
	private float x,y;
	
	/**
     * 演员的逻辑处理
     * 
     * @paturn region;
	 */
	 public void setRegion(TextureRegion region) {
	 this.region = region;
	 // 重新设置纹理区域后, 需要重新设置宽高
	 setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
	 }
	 
	public void draw(com.badlogic.gdx.graphics.g2d.Batch batch, float parentAlpha) 
	{
		/*
         * 绘制纹理区域
         * 将演员中的 位置(position, 即 X, Y 坐标), 缩放和旋转支点(origin), 宽高尺寸, 缩放比, 旋转角度 应用到绘制中,
         * 最终 batch 会将综合结果绘制到屏幕上
         */
        batch.draw(
			region, 
			getX(), getY(), 
			getOriginX(), getOriginY(), //初始坐标？？旋转中心点(可能用于旋转)
			getWidth(), getHeight(), 
			getScaleX(), getScaleY(), //比例
			getRotation() //旋转角度
        );
	}

    public void act(float delta) 
	{
		x+= delta*speed;
	}
	
	
	
}
