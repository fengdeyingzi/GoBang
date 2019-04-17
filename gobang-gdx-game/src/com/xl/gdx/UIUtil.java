package com.xl.gdx;


/*
使用代码创建draeable
绘制矩形 圆形 扇形 圆角矩形
风的影子
2018.5.18
*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class UIUtil
{
	
	
	/** 
	 * 绘制扇形 
	 * 
	 * @param x 
	 * 扇形x坐标 
	 * @param y 
	 * 扇形y坐标 
	 * @param radius 
	 * 半径 
	 * @param startAngle 
	 * 初始角度 
	 * @param interval 
	 * 扇形角度 
	 */ 
	public void renderSector(ShapeRenderer rend, float x, float y, float radius, float startAngle, float interval,Color color) 
	{ 
	int num = (int) interval; float[][] points = new float[num][2]; 
	for (int i = 0; i < num; i++)
	{
	points[i][0] = x + radius * MathUtils.cosDeg(startAngle + i);
	points[i][1] = y + radius * MathUtils.sinDeg(startAngle + i); 
	}
	rend.setColor(color);
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
	rend.begin(ShapeRenderer. ShapeType.Filled); 
	for (int i = 0; i < num - 1; i++)
	{
	rend.triangle(x, y, points[i][0], points[i][1], points[i + 1][0], points[i + 1][1]);
	} 
	rend.end();
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

	/*
	 绘制圆
	 参数：
	 圆x坐标
	 圆y坐标
	 圆半径
	 颜色
	 */
	public void drawCir(ShapeRenderer render,int x,int y,int r,Color color)
	{
	render.setColor(color);
	render.begin(ShapeRenderer.ShapeType.Filled);
	render.circle(x,y,r,r);
	render.end();

	}

	/*
	 绘制线
	 参数：
	 线起始x y坐标
	 终点x y坐标
	 颜色
	 */
	public void drawLine(ShapeRenderer render, int x,int y,int x2,int y2,Color color)
	{
	render.setColor(color);
	render.begin(ShapeRenderer.ShapeType.Filled);
	render.line(x,y,x2,y2,color,color);
	render.end();
	}

	/*
	 绘制矩形线
	 参数：
	 线段x y坐标
	 终点x y坐标
	 线宽度
	 颜色
	 */
	public void drawRectLine(ShapeRenderer render,int x,int y,int x2,int y2,int width,Color color)
	{
	render.setColor(color);
	render.begin(ShapeRenderer.ShapeType.Filled);
	render.rectLine(x,y,x2,y2,width);
	render.end();
	}

	/*
	 画环形
	 */


	/*
	 画圆角矩形
	 参数：
	 x x坐标
	 y y坐标
	 w 宽度
	 h 高度
	 r 圆角大小
	 color 颜色
	 */
	 
	 
	 /*
	 画圆弧
	 参数：
	   x
		 y
		 radius
		 color
	 
	 */
	 
	 /*
	 画文字
	 参数：
	   text
	   x
		 y
		 color
	 */


	/*
	 生成圆角图片
	 参数：
	 imgName 图片路径
	 r 圆角大小
	 */
	public static Pixmap createRoundedPixmap(String imgName,int r){ 
	Pixmap pixmap = new Pixmap(Gdx.files.internal(imgName));
//原图 生成一个圆角矩形图片 
	Pixmap mask = getPixmapRoundedRectangle(pixmap.getWidth(), pixmap.getWidth(),r,Color.WHITE);
//掩码 生成一个空图片
	Pixmap result=new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Pixmap.Format.RGBA8888);
	//保存结果
	pixmapMask(pixmap, mask, result, false); 
	return result;
	}



	public static Pixmap getPixmapRoundedRectangle(int width, int height, int radius, Color color) { 
	Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888); pixmap.setColor(color); 
// 横着的矩形
	pixmap.fillRectangle(0, radius, pixmap.getWidth(), pixmap.getHeight()-2*radius);
	// 垂直矩形
	pixmap.fillRectangle(radius, 0, pixmap.getWidth() - 2*radius, pixmap.getHeight()); 
// 左上 circle
	pixmap.fillCircle(radius, radius, radius); 
// 左下 circle
	pixmap.fillCircle(radius, pixmap.getHeight()-radius, radius); 
// 右上 circle 
	pixmap.fillCircle(pixmap.getWidth()-radius, radius, radius); // 右下 circle
	pixmap.fillCircle(pixmap.getWidth()-radius, pixmap.getHeight()-radius, radius); 
	return pixmap;
  }

	//（2）裁剪。原图结合掩码进行裁剪

	public static void pixmapMask(Pixmap pixmap, Pixmap mask, Pixmap result, boolean invertMaskAlpha){
	int pixmapWidth = pixmap.getWidth(); 
	int pixmapHeight = pixmap.getHeight();
	Color pixelColor = new Color(); 
	Color maskPixelColor = new Color(); 
	Pixmap.Blending blending = Pixmap.getBlending();
	Pixmap.setBlending(Pixmap.Blending.None);
	for (int x=0; x<pixmapWidth; x++){
	for (int y=0; y<pixmapHeight; y++){ 
	Color.rgba8888ToColor(pixelColor, pixmap.getPixel(x, y)); 
	// 获取原图像素颜色
	Color.rgba8888ToColor(maskPixelColor, mask.getPixel(x, y)); 
	// 获取掩码像素颜色
	maskPixelColor.a = (invertMaskAlpha) ? 1.0f-maskPixelColor.a : maskPixelColor.a; 
	// 如果转换掩码
	pixelColor.a = pixelColor.a * maskPixelColor.a;
	// 颜色吸相乘
	result.setColor(pixelColor); result.drawPixel(x, y); 
	}
	} Pixmap.setBlending(blending); 
	}
	
	/*
	创建圆角矩形
	参数分别是
	  颜色
		宽度
		高度
		圆角
	*/
	public static Pixmap getRoundedRectangle(Color color, int width, int height, int radius) {
	Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
	pixmap.setColor(color);
	for (int y = 0; y < pixmap.getHeight(); y++) {
	for (int x = 0; x < pixmap.getWidth(); x++) {
	if ((x >= 0 && x <= radius) && (y >= 0 && y <= radius)) { // bottom-left
	if (Math.sqrt((radius - x) * (radius - x) + (radius - y) * (radius - y)) > radius) {
	continue;
	}
	} else if ((x >= 0 && x <= radius) && (y >= (height - radius) && y <= height)) { // top-left
	if (Math.sqrt((radius - x) * (radius - x) + ((height - radius) - y) * ((height - radius) - y)) > radius) {
	continue;
	}
	} else if ((x >= (width - radius) && x <= width) && (y >= 0 && y <= radius)) {// bottom-right
	if (Math.sqrt(((width - radius) - x) * ((width - radius) - x) + (radius - y) * (radius - y)) > radius) {
	continue;
	}
	} else if ((x >= (width - radius) && x <= width) && (y >= (height - radius) && y <= height)) {// top-right
	if (Math.sqrt(((width - radius) - x) * ((width - radius) - x) + ((height - radius) - y) * ((height - radius) - y)) > radius) {
	continue;
	}
	}
	pixmap.drawPixel(x, y);
	}
	}
	return pixmap;
	}
	
	
	//创建一个ninepatch圆角drawable
	public static Drawable createRoundedRectDrawable(int width,int height,int rounds,Color color)
	{
		Pixmap map = getRoundedRectangle(color,width,height,rounds);
	  TextureRegion region = new TextureRegion(new Texture(map));
		NinePatch ninepatch = new NinePatch(region,width/3,height/3,width/3,height/3);
		return new NinePatchDrawable(ninepatch);
	}
	
	
}
