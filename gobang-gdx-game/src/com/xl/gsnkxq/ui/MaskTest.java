package com.xl.gsnkxq.ui;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by tian on 2016/9/27.
 遮罩绘制
 */

public class MaskTest implements  Screen
{
private String TAG="mask";
	public MaskTest()
	{
		Gdx.app.error(TAG,"initdata");
		initData();
		Gdx.app.error(TAG,"initUI");
		initUI();
	}

	@Override
	public void render(float p1)
	{
		// TODO: Implement this method
		//Gdx.app.error(TAG,"render");
		update(p1);
	}

	@Override
	public void resize(int p1, int p2)
	{
		// TODO: Implement this method
	}

	@Override
	public void show()
	{
		// TODO: Implement this method
	}

	@Override
	public void hide()
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}

	private SpriteBatch batch;
	private Texture bg;
	private Texture img;
	private Texture mask;
	private BitmapFont font;
	EraserActor actor_er;
	Stage stage;
	
	void initData() {
		font =new BitmapFont();
	}

	void initUI() {
		
		Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(),
															 Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GRAY);
		pixmap.fill();
Gdx.app.error("","1");
		bg = new Texture(pixmap);
		Gdx.app.error("","2");
		mask = new Texture("mask_new.png");
		mask.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		Gdx.app.error("","3");
		img =new Texture(Gdx.files.internal("sprite.png"));
		img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		Gdx.app.error("","4");
		batch =new SpriteBatch();
		//actor_er = new EraserActor(img,30);
		// 创建舞台, 并添加演员
		//stage = new Stage(new StretchViewport(500,700));
		 
		//stage.addActor(actor_er);

		/* 事件初始化 */

		// 首先必须注册输入处理器（stage）, 将输入的处理设置给 舞台（Stage 实现了 InputProcessor 接口）
		// 这样舞台才能接收到输入事件, 分发给相应的演员 或 自己处理。
		//Gdx.input.setInputProcessor(stage);

		// 给舞台添加输入监听器（包括触屏, 鼠标点击, 键盘按键 的输入）
		/*
		
		stage.addListener(new InputListener()
			{
				public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
				{
					actor_er.maskDown(x,y);
					Gdx.app.error("touchstage","down");
					return true;
				}

				public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
				{
					Gdx.app.error("touchstage","up");
					actor_er.maskUp(x,y);
				}




			});
   */
		// 给演员添加一个 点击 监听器（只包括 手指点击 或 鼠标点击）
		/*
		actor_er.addListener(new InputListener()
			{
				public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
				{
					actor_er.maskDown(x,y);
					Gdx.app.error("touch","down");
					return true;
				}

				public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) 
				{
					Gdx.app.error("touch","up");
					actor_er.maskUp(x,y);
				}




			});
   */
		// 只有需要监听输入的舞台/演员才需要添加监听器
		// 如果要移除指定监听器, 可以调用相应的 removeListener(listener) 方法
		
		
		
		
	}

	void update(float dt) {
		if (batch == null)
			return;

		batch.begin();
//		drawBackground(batch);
		
		//画背景
		drawBackground(batch);
		Gdx.app.error("","5");
		//画遮罩
		drawAlphaMask(batch);
		Gdx.app.error("","6");
		//画前景色
		drawForeground(batch, 0, 0, mask.getWidth(), mask.getHeight());
		
		//stage.act();
//		stage.draw();
		//actor_er.act(Gdx.graphics.getDeltaTime());
    //actor_er.draw(batch,1);
		
		batch.end();
	}

	//画需要裁剪的sprite
	private void drawForeground(SpriteBatch batchPara , int clipX,int clipY, int clipWidth, int clipHeight) {
		Gdx.gl.glColorMask(true, true, true, true);
		batchPara.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

		Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
		Gdx.gl.glScissor(clipX, clipY, clipWidth, clipHeight);

		batchPara.draw(img, 0,0,mask.getWidth(), mask.getHeight());

		batchPara.flush();

		Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
		/*
	 //now that the buffer has our alpha, we simply draw the sprite with the mask applied
	 Gdx.gl.glColorMask(true, true, true, true);
	 batch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);

	 //The scissor test is optional, but it depends 
	 Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
	 Gdx.gl.glScissor(clipX, clipY, clipWidth, clipHeight);

	 //draw our sprite to be masked
	 batch.draw(sprite, 0, 0, 250, 250);

	 //remember to flush before changing GL states again
	 batch.flush();

	 //disable scissor before continuing
	 Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
	 
		*/
	}

	//画遮罩
	private void drawAlphaMask(SpriteBatch batch ) {
		Gdx.gl.glColorMask(false, false, false, true);

		//change the blending function for our alpha map
		batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

		//draw alpha mask sprite(s)
		batch.draw(mask, 0f, 0f, mask.getWidth(), mask.getHeight());

		//flush the batch to the GPU
		batch.flush();
		
		/*
	 //disable RGB color, only enable ALPHA to the frame buffer
	 Gdx.gl.glColorMask(false, false, false, true);

	 //change the blending function for our alpha map
	 batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

	 //draw alpha mask sprite(s)
	 batch.draw(alphaMask, x, y, width, height);

	 //flush the batch to the GPU
	 batch.flush();
	 
		*/
	}

	//画背景
	private void drawBackground(SpriteBatch batch) {
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//... draw background entities/tiles here ...
		batch.draw(bg, 0f, 0f);

		batch.flush();
		/*
	 //regular blending mode
	 batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


	 //... draw background entities/tiles here ...


	 //flush the batch to the GPU
	 batch.flush();
		*/
	}
}
