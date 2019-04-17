package com.xl.gsnkxq;


/*
文字绘制Screen
 LazyFont
*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rpsg.lazyFont.LazyFont;
import com.xl.gdx.XLScreen;


public class FontDrawScreen extends XLScreen
{
	private String TAG = "FontDrawScreen";
	LazyFont lazyFont;
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	
	public FontDrawScreen(int width,int height)
	{
		super(width,height);
		Gdx.app.error(TAG,"开始创建");
		//创建文字绘制类
	lazyFont = new LazyFont(32,Gdx.files.internal("fonts/plantagenet.ttf"));
		//创建画笔
		batch = new SpriteBatch();
		//绑定照相机
		batch.setProjectionMatrix(getCamera().combined);
		
		 shapeRenderer = new ShapeRenderer();
	shapeRenderer.setProjectionMatrix(getCamera().combined);
	}

	@Override
	public void render(float p1)
	{
		// TODO: Implement this method
		Gdx.app.error(TAG,"render");
		//清除画布
		Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		//画一个矩形
		drawRect(shapeRenderer,10,0,getWidth()-200,350,Color.BLACK);
		//设置字体颜色
		batch.begin();
		lazyFont.setColor(Color.WHITE);
		//设置自动换行
		lazyFont.autoLinefeed=true;
		//画多行文本
		lazyFont.drawMultiLine2(batch,"    测试字体绘制\n风的影子 制作\n英文换行测试：hhsg dhd hdhdhd hdhdhd hdhdhdhd\n文字换行测试：这里我们需要资源加载器来管理资源，这里为了测试，可以多加载些图片效果明显点。这里我们还使用了舞台，使用了自适应分辨率。 ",
		
		10,350,getWidth()-200);
		lazyFont.drawMultiLine(batch,"  测试字体绘制\n风的影子",10,800,getWidth()-20);
		batch.end();
		super.render(p1);
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
	public void drawRect(ShapeRenderer render, int x,int y,int w,int h,Color color)
	{
	render.setColor(color);
	render.begin(ShapeRenderer.ShapeType.Filled);
	render.rect(x,y,w,h);
	render.end();
	}
	
	
}
