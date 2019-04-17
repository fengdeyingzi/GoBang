package com.xl.gsnkxq;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
//import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Window.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.xl.sprite.*;
import com.rpsg.lazyFont.LazyFont;
import com.xl.gdx.XLScreen;
/*
游戏主画面
各种测试。。。。
*/
public class MainScreen extends XLScreen
{
	Texture texture;
	Texture img_hero;
	Texture pic;
	SpriteBatch batch;
	OrthographicCamera camera;
	
//	float zoom; //缩放比
	XLSprite hero;
	long time;
	int fps = 20;
	SpriteX sprite;
	LazyFont lazyFont;
	
	
	
	
	//	public static FreeTypeFontGenerator gont;
		//public static FreeTypeFontGenerator. FreeTypeBitmapFontData fontData;
	public MainScreen(int width,int height)
	{
		super(width,height);
		//FileLog.e("MainScreen");
		pic = new Texture("sprite/a.png");
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho( false, width, height);
		time = System.currentTimeMillis();
		//camera.rotate( 45 );
		//camera.zoom = 2f;
		img_hero = new Texture(Gdx.files.internal("sprite/mario_small.png"));
		//加载精灵
		ReadSprite readSprite = new ReadSprite();
		hero = readSprite.readSprite("sprite/mario_small.sprite",img_hero);
		//设置精灵动作
		hero.setAction(1);
		
		sprite = new SpriteX("sprite/55.sprite","sprite/55.png");
		//sprite.setVisible(true);
		create();
		
	//加载自定义字体

			//gont = new FreeTypeFontGenerator( Gdx.files.internal("fonts/plantagenet.ttf"));
			lazyFont = new LazyFont(32,Gdx.files.internal("fonts/plantagenet.ttf"));

//在这里把你要输出的汉字全部写上。不要出现重复的字，否则会出错

//如果标点符号也要用全角，也写上

	// fontData = gont.generateData(32, "歌唱我们亲爱的祖国， 从此走向繁荣富强风影子", false);


	/***** 分割线上下的代码可以不在同一个类 *****/


//文本使用自定义字体

	//font = new BitmapFont(fontData, fontData.getTextureRegion(), false);

//		
	}
	

	
	ImageButton Btn_A_OK;
	ImageButton Btn_B_Cancel;
	ImageButton Btn_SHOW;
	Stage stage;
	//BitmapFont font;
	Window dialogWindow;
	

	
	public void create() {

		Viewport stretchViewport = new StretchViewport(480, 800);
		stage = new Stage(stretchViewport);

		batch = new SpriteBatch();
    
		//font = new BitmapFont(Gdx.files.internal("data/font.fnt"),Gdx.files.internal("data/font.png"), false);
		//font = FreeFont.createBitmapFont("风的影子Chinese Font Support?", 20, 1);
  //font.setColor(0.1f,0.1f,0.1f,1f);
		//setButton();

		//setWindow();

		//setBtnListener();

		//stage.addActor(Btn_SHOW);
//Btn_SHOW.setVisible(false);
		Gdx.input.setInputProcessor(stage);

	}
/*
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

		Btn_A_OK = new ImageButton(Btn_A_UP, Btn_A_DOWN);

		Btn_B_Cancel = new ImageButton(Btn_B_UP, Btn_B_DOWN);

	}
	*/
/*
	public void setWindow() {
		TextureRegionDrawable WindowDrable = new TextureRegionDrawable(
			new TextureRegion(new Texture(
								  Gdx.files.internal("data/dialog.png"))));

		WindowStyle style = new WindowStyle(font, Color.RED, WindowDrable);

		dialogWindow = new Window("提示", style);

		dialogWindow.setWidth(width/1.5f);
		dialogWindow.setHeight(height/1.5f);

		dialogWindow.setPosition(width/10,height/10);

		dialogWindow.setMovable(true);


		Btn_A_OK.setPosition(width/10, 0);

		Btn_B_Cancel.setPosition(width/3, 0);


		dialogWindow.addActor(Btn_A_OK);

		dialogWindow.addActor(Btn_B_Cancel);

	}

	public void setBtnListener() {
		Btn_SHOW.addListener(new InputListener(){

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button) {

					stage.addActor(dialogWindow);

					return true;
				}

			});

		Btn_A_OK.addListener(new InputListener(){

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button) {

					Gdx.app.exit();

					return true;
				}

			}); 

		Btn_B_Cancel.addListener(new InputListener(){

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button) {

					dialogWindow.remove();

					return super.touchDown(event, x, y, pointer, button);
				}

			});
	}
	*/
	

	@Override
	public void render(float p1)
	{
		// TODO: Implement this method
		/*
		Gdx.gl.glClearColor(0.2f, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix( camera.combined );
		batch.begin();
		batch.draw(texture, Gdx.graphics.getWidth() / 4, 0, 
				   Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 2);
	    batch.draw(texture,width/2,0,
		width/4,height/4);
		batch.end();
		*/
		long temp_time=System.currentTimeMillis();
		int curtime = 1000/fps;
		if(temp_time-time >= curtime)
		{
		hero.logoc();
		time+=curtime;
		}
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix( camera.combined );
		
		batch.begin();
		//font.setColor(0xff000000);
		//font.draw(batch, "歌唱我们亲爱的祖国，从此走向繁荣富强!", 100, 100);
		Gdx.app.error("aa","log测试");
		
		//
		//font.draw(batch, "风的影子_", 100,0);
		lazyFont.autoLinefeed=true;
		lazyFont.drawMultiLine2(batch,"好的好的好的好的hhsg dhd hdhdhd hdhdhd hdhdhdhd\n呀呀呀",10,350,200);
		/*
		hero.draw(batch,0,100);
		sprite.setAction(0);
		sprite.update(System.currentTimeMillis());
		sprite.paint(new Graphics(batch));
		if(sprite.collidesWith2(0,20,60,20))
		{
			batch.draw(pic,0,30);
		}
		 elsedrawMultiLine(com.badlogic.gdx.graphics.g2d.Batch batch, java.lang.CharSequence str, float x, float y) {}

		 public com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds drawMultiLine(com.badlogic.gdx.graphics.g2d.Batch batch, java.lang.CharSequence str, float x, float y, float alignmentWidth, com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment alignment) {}
		 
		{
			batch.draw(pic,0,300);
		}*/
		//FileLog.e("paint");
		batch.end();
		
		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
		super.resize(width,height);
		/*
		zoom = ((float)this.width)/width;
		camera.zoom = 1.0f;
		this.height = this.width * height/width;
		camera.setToOrtho( false, this.width, this.height);
		camera.viewportHeight = this.height;
		camera.viewportWidth = this.width;
		*/
	}

	@Override
	public void show()
	{
		// TODO: Implement this method
		//FileLog.e("show");
	}

	@Override
	public void hide()
	{
		//FileLog.e("hide");
		
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
		//FileLog.e("pause");
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
		//FileLog.e("resume");
	}

	@Override
	public void dispose()
	{
		//texture.dispose();
		batch.dispose();
		lazyFont.dispose();
		//hero.dispose();
		//sprite.dispose();
		//FileLog.e("dispose");
	}
	
}
