package com.xl.gsnkxq;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.xl.gdx.XLScreen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
/*
地图绘制
实现了照相机移动


*/
public class MapScreen extends XLScreen
{
private String TAG = "MapScreen";
	
	//int item_w=20,item_h=20;
  OrthographicCamera camera;
	
	
	
	
	//private OrthographicCamera camera; 
	//private SpriteBatch batch; 
	TiledMap map; 
	
	OrthogonalTiledMapRenderer render_map;
  
	public MapScreen(int width,int height)
	{
		super(width,height);
	this.camera = getCamera();
	Gdx.app.log("MapScreen", "width:"+width+" "+height);
	map=new TmxMapLoader().load("map/mali.tmx");
	//获取map数组
		TiledMapTileLayer objs =  (TiledMapTileLayer) map.getLayers().get(0);
		objs.getCell(0,0);
	  
	//根据map创建reander 注意，这里可以设置像素比，最好设置为1
	render_map=new OrthogonalTiledMapRenderer(map,1);
	//将摄像机x方向归0 y方向对准800/2
	camera.position.x=900;
	//camera.position.y=800/2;
	//camera.setToOrtho(false,item_w,item_h);
  }  
	
	
	public void render(float p1)
	{ 
	Gdx.gl.glClearColor(1,1,1,1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
  
	camera.update();
	render_map.setView(camera); 
	render_map.render(); 
	
	
	
	//镜头移动
	/*
	camera.translate(2,0);
	if(camera.position.x >= 200*20)
		camera.translate(-2,0);
		*/
	//测试地图碰撞事件
	if(Gdx.input.isTouched())
	{
		//获取屏幕宽高
		int screen_x=Gdx.input.getX();
		int screen_y = Gdx.graphics.getHeight()-1- Gdx.input.getY();
		//根据屏幕坐标计算成相机世界坐标
		float x= screen_x* camera.viewportWidth/Gdx.graphics.getWidth()+camera.position.x-camera.viewportWidth/2;
		float y =  screen_y * camera.viewportHeight/Gdx.graphics.getHeight()+camera.position.y-camera.viewportHeight/2;
		TiledMapTileLayer objs =  (TiledMapTileLayer) map.getLayers().get(0);
		//除以图块宽度 获得图块的点阵位置(在libgdx中这个点阵是反的，所以不需要再反过来)
		int cell_x = (int)(x/objs.getTileWidth());
		int cell_y = (int)( y/objs.getTileHeight());
		Gdx.app.error(TAG,"x"+"y:"+x+" "+y+" cell:"+cell_x+" "+cell_y);
		
		//判断是否点到图块
		if(objs.getCell(cell_x ,cell_y)!=null)
		test(); //用这个方法来进行测试
	}
	
	}
	
	
	//画形状测试
	private void test()
	{
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		//batch.setProjectionMatrix(getCamera().combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
    //shapeRenderer.setTransformMatrix(getCamera().combined);
		//shapeRenderer.updateMatrices();
		//设置自动绘制类型
		shapeRenderer.setAutoShapeType(true);
		//设置填充模式
		//shapeRenderer.set(ShapeRenderer.ShapeType.Line);
		//shapeRenderer.updateMatrices();
		shapeRenderer.setColor(0.2f,0.7f,0.9f,1.0f);
		//开始绘制
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//画矩形
		shapeRenderer.rectLine(10, 10, 3000, 30, 80);
		shapeRenderer.line(10,10,200,500,new Color(1.0f,0.5f,0.5f,0.5f),new Color(1.0f,0.2f,0.7f,0.95f));
		for(int ix=0;ix<20;ix++)
		shapeRenderer.point(ix,2,0);
		//绘制结束
		shapeRenderer.end();
		//renderSector(shapeRenderer,200,200,30,0,90,Color.WHITE);
		
	}
	
	
	@Override
	public void resize(int width, int height)
	{
	// TODO: Implement this method
	//float zoom = ((float)this.item_w)/width;
	camera.zoom = 1.0f;
	//this.height = this.width * height/width;
	//this.item_h = this.item_w * height/width;
	
	//camera.setToOrtho( false, this.width, this.height);
	
	//camera.viewportHeight = this.item_h;
	//camera.viewportWidth = this.item_w;
	/*
  camera.setToOrtho(false,item_w,item_h);
	*/
	}
	@Override 
	public void pause(){
		
	}
	@Override public void resume()
	{ 
	} 
	
	
	
}
