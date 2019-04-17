package com.xl.gsnkxq;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xl.gdx.BaseResources;
import com.xl.gdx.XLScreen;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Input;
import com.xl.gdx.GdxToast;


public class GameMenuScreen extends XLScreen
{
	public static final String TAG = "GameMenu";
	TextButton btn_start, btn_help, btn_about, btn_exit,btn_setting, btn_sound;
	Stage stage;
	Table menu;
	GdxToast toast;
	Skin skin;
	Image img_background,img_logo;
	AboutDialog dialog_about;
	//Viewport viewport;
	public GameMenuScreen(int width,int height)
	{
		super(width,height);
		Gdx.app.log(TAG,"加载skin");
		skin = new Skin(Gdx.files.internal("ui/packer.json"));
		toast = new GdxToast("再按一次返回键退出", skin,"toast");
		dialog_about = new AboutDialog("关于",skin);
		Gdx.app.log("Menu","width:"+width+" "+height);
		menu = new Table();
		Texture texture_background = new Texture(Gdx.files.internal("img/menu_background.png"));
		Texture texture_logo = new Texture(Gdx.files.internal("img/logo.png"));
		
		img_background = new Image(texture_background);
		img_background.setPosition((width-img_background.getWidth())/2,(height - img_background.getHeight())/2);
		img_logo = new Image(texture_logo);
		img_logo.setPosition(width/2-320, height/2-150);
		btn_start = new TextButton("开始游戏",skin,"menu");
        btn_about = new TextButton("关  于",skin,"menu");
		menu.add(btn_start);
		menu.row();
		menu.add(btn_about);
		menu.row();
		//menu.add(btn_about);
		menu.setPosition(width/2+200, height/2-120);
		//menu.setPosition(0,0);
		
		//this.viewport = new StretchViewport(width,height);
		//viewport.getCamera().
		stage = getStage();
		stage.addActor(img_background);
		stage.addActor(img_logo);
		stage.addActor(menu);
		stage.addActor(toast);
		
		stage.setDebugAll(true);
		Gdx.input.setInputProcessor(stage);

		btn_start.addListener(new ClickListener()
			{
                public void clicked(InputEvent event,float x,float y)
                {
                    startScreen(GameScreen.class);
					dispose();
                }

			});

		btn_about.addListener(new ClickListener()
			{
                public void clicked(InputEvent event,float x,float y)
                {
                    
					dialog_about.show(stage);
                }

			});


	}

	long time=0;
	@Override
	public void render(float p1)
	{
		// TODO: Implement this method
		//清除画布
		Gdx.gl.glClearColor(0.2f,0.2f,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

		stage.act(p1);
		stage.draw();
		if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
			
				if(System.currentTimeMillis()-time > 3000){
					toast.setPosition((getWidth()-toast.getWidth())/2, getHeight()*1/6);
					toast.show();
					time = System.currentTimeMillis();
				}
				else{
					Gdx.app.exit();
				}
				
			
			
		}
		super.render(p1);
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
		super.resize(width, height);
		//this.viewport.update(width,height);
	}



	@Override
	public void dispose()
	{
		// TODO: Implement this method
		super.dispose();
		//stage.dispose();
		skin.dispose();
	}





	//创建圆角按钮
	//参数：按钮文本，按钮宽高
	public TextButton createTextButton(String text,int width,int height)
	{
		TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
		style.down = BaseResources.drawable_button_down;
		//style.over = BaseResources.texture_toast_background;
		style.font = BaseResources.font;
		style.fontColor = new Color(0x505050ff);
		style.up = BaseResources.drawable_button_up;
		//style.fo
		TextButton btn_test = new TextButton(text,style);

		btn_test.setWidth(width);
		btn_test.setHeight(height);
		return btn_test;
	}




}
