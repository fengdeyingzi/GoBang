package com.xl.gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import com.xl.gdx.XLScreen.OnScreenListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class XLScreen implements Screen
{
	private OrthographicCamera camera;
	private int width=640,height=480;
    private Viewport viewport;
	private XLScreen.OnScreenListener listener;
	private Stage stage;
	
	/*
	直接将屏幕宽高传递过来，这样就不用设置屏幕宽高了
	*/
	public XLScreen(int width,int height)
	{
		camera = new OrthographicCamera(width,height);
		//使用此方法，摄像机将会默认对准画布中心
		camera.setToOrtho( false, width, height);
		this.width = width;
		this.height = height;
		this.viewport = new StretchViewport(width,height,camera);
		//为视口设置相机
		viewport.setCamera(camera);
		stage = new Stage(viewport);
	}

	//设置舞台
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	//获取舞台
	public Stage getStage()
	{
		return stage;
	}
	
	//获取照相机
	public OrthographicCamera getCamera()
	{
		return this.camera;
	}
	
	//设置照相机(可以不用设置)
	public void setCamera(OrthographicCamera camera)
	{
		this.camera = camera;
	}
	
	
	
	@Override
	public void render(float p1)
	{
		stage.act(p1);
		stage.draw();
		// TODO: Implement this method
	}
	
	//或者屏幕宽度(虚拟屏幕)
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
		//float zoom = ((float)this.width)/width;
		//camera.zoom = 1.0f;
		//this.height = this.width * height/width;
		
		//camera.setToOrtho( false, this.width, this.height);
		//camera.viewportHeight = this.height;
		//camera.viewportWidth = this.width;
		this.viewport.update(width,height);
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
		if(listener!=null)
			listener.onDispose(this);
		stage.dispose();
	}
	
	public void setOnScreenListener(OnScreenListener listener)
	{
		this.listener = listener;
	}
	
	public boolean startScreen(Class<?> className)
	{
		if(listener!=null)
		{
			listener.onStartScreen(this,className);
			return true;
		}
		else
			return false;
	}
	
	
	
	
	/*
	监听器 用于监听screen销毁和screen启动命令
	*/
	public interface OnScreenListener
	{
		//当screen销毁时调用此方法
		public void onDispose(XLScreen screen);
		//当screen启动时调用此方法
		public void onStartScreen(XLScreen screen, Class<?> className);
	}
}
