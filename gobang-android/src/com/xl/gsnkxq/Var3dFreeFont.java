package com.xl.gsnkxq;

import android.graphics.*;
import android.os.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.io.*;

public class Var3dFreeFont
 {
	private static TextureAtlas font;

	//
	static boolean isdebug=true;
	static String dir="AppProjects";
	static String bitmapname="font.png";
	static String fontname="font.fnt";
	static String atlasname="font.atlas";
	public void setDir(String dir)
	{
		this.dir=dir;
	}
	public static String getSDPath()
	{
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
		if(sdCardExist)
		{
			sdDir=Environment.getExternalStorageDirectory();//获取sd卡目录
		}
		else 
		{
			return null;
		}
		return sdDir.getPath();
	}
	
	
	public static void createTextureAtlasFont(String string, int size, int color) {
		int jj = 0;// 字符间距
		int scaleW=1024,scaleH=3072;
		
		String str_al = "font.png\r\nformat: RGBA8888\r\nfilter: Nearest,Nearest\r\nrepeat: none\r\n";
		String str_fnt = "info face=\"΢���ź�\" size="+size+" bold=0 italic=0 charset=\"\" unicode=0 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1\n"
		+"common lineHeight="+(size+jj)+" base="+size+" scaleW="+scaleW+" scaleH="+scaleH+" pages=1 packed=0\n"
		+"page id=0 file=\""+bitmapname+"\"\n"
		+"chars count="+string.length()+"\n";
		Bitmap newb = Bitmap.createBitmap(1024, 3072, Bitmap.Config.ARGB_8888);// 创建位图
		Canvas canvas = new Canvas(newb);// 创建画布
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
													  | Paint.FILTER_BITMAP_FLAG));
		Paint paint = new Paint();// 创建画笔
		paint.setColor(color);// 设置颜色
		paint.setTextSize(size);// 设置尺寸
		//string = string.replaceAll("(?s)(.)(?=.*\\1)", "");
		int maxx = 1000 / size;// 算出横向字符最多个数
		int maxy = string.length() / maxx;// 字符最大行数
        int yi=0;
		int xi=0;
		for (int i = 0; i < string.length(); i++) {
			//int yi = i / maxx + 1;
			//int xi = i % maxx;
			
			float font_w = size;
			if(string.charAt(i)<256)
			{
			font_w = paint.measureText(string.substring(i,i+1));
			if(font_w%1>0)font_w+=1;
			}
			canvas.drawText(string, i, i + 1, xi, yi+size-3, paint);
			if(isdebug)
			{
				canvas.drawLine(xi,yi,xi,yi+size,paint);
			}
			xi+=font_w;
			if(xi+size > 1024)
			{
				xi=0; yi+=size+jj;
			}
			// 转码
			String ma = "";
			try {
				ma = new String(string.substring(i, i + 1).getBytes(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			str_al += ma + "\r\n";
			str_al += "  rotate: false\r\n";
			str_al += "  xy:" + xi + ","
				+ (yi)  + "\r\n";
			
			str_al += "  size:" + (int)font_w + "," + size + "\r\n";
			str_al += "  orig:" + size + "," + size + "\r\n";
			str_al += "  offset:0,0\r\n  index:-1\r\n";
			str_fnt+="char id="+((int)string.charAt(i))+"   x="+"     y="+"     width=0     height="+xi+"     xoffset="+"     yoffset="+"    xadvance=9     page=0  chnl=0 \n";
		}
		canvas.save();// 保存绘制的图像到newb

		try {
			saveBitmap(newb);
			saveAtlas(str_al);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//font = new TextureAtlas(Gdx.files.external("var3d/font.atlas"));
	}

	public static void saveBitmap(Bitmap bitmap) throws IOException {
		File file = new File(getSDPath()+File.separator+ dir+File.separator+bitmapname);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveAtlas(String s) {
		try {
			FileOutputStream outStream = new FileOutputStream(
				getSDPath()+File.separator+ dir+File.separator+ atlasname, false);
			OutputStreamWriter writer = new OutputStreamWriter(outStream,
															   "utf-8");
			writer.write(s);
			writer.flush();
			writer.close();// 记得关闭
			outStream.close();
		} catch (Exception e) {
		}
	}
	
	public static void saveFont(String s) {
		try {
			FileOutputStream outStream = new FileOutputStream(
				getSDPath()+File.separator+ dir+File.separator+ atlasname, false);
			OutputStreamWriter writer = new OutputStreamWriter(outStream,
															   "utf-8");
			writer.write(s);
			writer.flush();
			writer.close();// 记得关闭
			outStream.close();
		} catch (Exception e) {
		}
	}
	

	public static void drawFont(SpriteBatch batch, String chinese, float x,
								float y) {
		float wi = x;
		for (int i = 0; i < chinese.length(); i++) {
			String name = "";
			try {
				name = new String(chinese.substring(i, i + 1).getBytes(),
								  "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			TextureRegion tex = font.findRegion(name);
			batch.draw(tex, wi, y);
			wi += tex.getRegionWidth();
		}
	}
}


