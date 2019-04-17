package com.xl.gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import com.badlogic.gdx.utils.viewport.Viewport;

public class XLGame extends Game implements XLScreen.OnScreenListener 
{
  //screen被销毁
	@Override
	public void onDispose(XLScreen screen)
	{
	  list_screen.remove(screen);
	  if(list_screen.size()>0)
			setScreen(list_screen.get(list_screen.size()-1));
	}

	//screen启动
	@Override
	public void onStartScreen(XLScreen screen, Class<?> className)
	{
	startScreen(className);
	}
	
  private static String TAG = "XLGame";
	//游戏屏幕宽高
    private int width=640,height=480;
	//真实屏幕宽高
	private int DEVICE_WIDTH,DEVICE_HEIGHT;
	//游戏主照相机
	private OrthographicCamera camera;
	//是否创建屏幕
	private boolean isCreate;
	//默认舞台
	private Stage stage;
	//保存screen列表
	private ArrayList<XLScreen> list_screen;
	private Viewport viewport;
	
	@Override
	public void create()
	{
		// TODO: Implement this method
		isCreate=false;
		this.width = Gdx.graphics.getWidth();
		this.height =Gdx.graphics.getHeight();
		this.list_screen = new ArrayList<XLScreen>();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		stage = new Stage(viewport);
	}
	
	public Stage getStage()
	{
		return this.stage;
	}
	
	
	/*
	在init中初始化数据坐标可以保证屏幕位置没偏差
	*/
	public void init()
	{
		//camera.setToOrtho( false, width, height);
		
		
	}
	
	
	
	//获取照相机
	public Camera getCamera()
	{
		return camera;
	}
	
	/*
	设置屏幕宽高
	设置之后，创建的Screen也会跟着改变
	*/
	public void setScreenSize(int width,int height)
	{
		if(width<=0 && height<=0)
			Gdx.app.error(TAG,"setScreenSize error!");
		if(width==0)
		{
			this.width = this.height*DEVICE_WIDTH/DEVICE_HEIGHT;
			this.height=height;
		}
		else if(height==0)
		{
			this.height = this.width*DEVICE_HEIGHT/DEVICE_WIDTH;
			this.width=width;
		}
		else
		{
			this.width = width;
			this.height = height;
		}
		camera.setToOrtho( false, this.width, this.height);
		if(stage!=null)
			stage.setViewport(new StretchViewport(this.width,this.height));
   }
	
	 /*
	 获取游戏设置的宽度
	 */
	public int getGameWidth()
	{
		return this.width;
	}
	/*
	获取游戏设置的高度
	*/
	public int getGameHeight()
	{
		return this.height;
	}
	
	/*
	创建一个Screen
	*/
	public void startScreen(Class<?> className)
	{
		XLScreen screen = createScreen(className,getGameWidth(),getGameHeight());
		screen.setOnScreenListener(this);
		setScreen(screen);
		list_screen.add(screen);
	}
	
	/*
	 通过类class来创建Screen
	 */
	private XLScreen createScreen(java.lang.Class<?> screenClass,int width,int height)
	{
	Object obj=null;
	//调用处：构造方法带参：
	Class[] params = {int.class,int.class};
	//类类型
	Object[] values = {width,height};
	//调用构造方法,创建对象的Constructor对象，用他来获取构造方法的信息：即用其调用构造方法创建实例
	try
	{
	Constructor con = screenClass.getConstructor(params);
	//调用构造方法并创建实例
	try
	{
	obj = con.newInstance(values);
	}
	catch (InstantiationException e)
	{
	e.printStackTrace();
	}
	catch (InvocationTargetException e)
	{
	e.printStackTrace();
	}
	catch (IllegalAccessException e)
	{
	e.printStackTrace();
	}
	catch (IllegalArgumentException e)
	{
	e.printStackTrace();
	}
	}
	catch (SecurityException e)
	{
	e.printStackTrace();
	}
	catch (NoSuchMethodException e)
	{
	e.printStackTrace();
	}

	return (XLScreen)obj;
	}
	
	/*
	切换Screen
	*/
	public void render() 
	{
	Gdx.gl.glClearColor(1,1,1,1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
	
		super.render();
		stage.act();
		stage.draw();
	}
	
	/*
	
	*/
	@Override
	public void resize(int width, int height)
	{
		super.resize(width,height);
		this.DEVICE_WIDTH=width;
		this.DEVICE_HEIGHT=height;
		
		//float zoom = ((float)this.width)/width;
		//camera.zoom = 1.0f;
		/*
		camera.viewportWidth=this.width;
		camera.viewportHeight = this.height;
		*/
		//camera.setToOrtho( false, this.width, this.height);
        this.viewport.update(width,height);
		if(!isCreate)
		{
		setScreenSize(width,height);
		init();isCreate=true;
		}
	}

	@Override
	public void pause()
	{
		//FileLog.e("MainScreen pause");
		super.pause();
	}

	@Override
	public void resume()
	{
		//FileLog.e("MainScreen resume");
		super.resume();
	}
	
	public void dispose() 
	{
		isCreate=false;
		
	}
	
}
