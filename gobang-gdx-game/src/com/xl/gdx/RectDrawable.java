package com.xl.gdx;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

/*
这个drawable用来实现toast的背景
*/
public class RectDrawable implements Drawable
{
  private float minWidth;
	private float minHeight;
	private int x;
	private int y;
	private int width;
	private int height;
	private float left_width;
	private float right_width;
	private float up_height;
	private float bottom_height;
	
	public RectDrawable()
	{
		this.minWidth = 300;
		this.minHeight = 60;
		this.left_width=200;
		this.right_width=200;
		this.up_height=30;
		this.bottom_height=30;
	}
	
	
	@Override
	public void draw(Batch p1, float p2, float p3, float p4, float p5)
	{
	// TODO: Implement this method
	//ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	//drawRect(shapeRenderer,p2,p3,p4,p5,Color.BLACK);
	}
	

	/*
	 * 绘制矩形
	 * 参数：
	 矩形x坐标
	 矩形y坐标
	 矩形宽度
	 矩形高度
	 矩形颜色
	 */
	public void drawRect(ShapeRenderer render, float x,float y,float w,float h,Color color)
	{
	render.setColor(color);
	render.begin(ShapeRenderer.ShapeType.Filled);
	render.rect(x,y,w,h);
	render.end();
	}
	

	@Override
	public float getLeftWidth()
	{
	// TODO: Implement this method
	return left_width;
	}

	@Override
	public void setLeftWidth(float p1)
	{
	// TODO: Implement this method
	this.left_width=p1;
	}

	@Override
	public float getRightWidth()
	{
	// TODO: Implement this method
	return right_width;
	}

	@Override
	public void setRightWidth(float p1)
	{
	// TODO: Implement this method
	this.right_width=p1;
	}

	@Override
	public float getTopHeight()
	{
	// TODO: Implement this method
	return up_height;
	}

	@Override
	public void setTopHeight(float p1)
	{
	// TODO: Implement this method
	this.up_height=p1;
	}

	@Override
	public float getBottomHeight()
	{
	// TODO: Implement this method
	return bottom_height;
	}

	@Override
	public void setBottomHeight(float p1)
	{
	// TODO: Implement this method
	this.bottom_height=p1;
	}

	@Override
	public float getMinWidth()
	{
	// TODO: Implement this method
	return minWidth;
	}

	@Override
	public void setMinWidth(float p1)
	{
	// TODO: Implement this method
	this.minWidth=p1;
	}

	@Override
	public float getMinHeight()
	{
	// TODO: Implement this method
	return this.minHeight;
	}

	@Override
	public void setMinHeight(float p1)
	{
	// TODO: Implement this method
	this.minHeight=p1;
	}
	
	
}
