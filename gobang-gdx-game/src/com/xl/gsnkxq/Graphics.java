package com.xl.gsnkxq;


import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Graphics
{
	
	public static int TOP=0,LEFT=0,RIGHT=0,BOTTOM=0;
	public static int BASELINE=0,HCENTER=0,VCENTER=0;
	Batch batch;
	
	public Graphics(Batch batch)
	{
		this.batch = batch;
	}
	
	public int getClipX()
	{
		return 0;
	}
	
	public int getClipY()
	{
		return 0;
	}
public int getClipWidth()
{
	return 1024;
}

public int getClipHeight()
{
	return 1024;
}
	

public void clipRect(int x,int y,int w,int h)
{
	
}

public void setClip(int x,int y,int w,int h)
{
	
}

public void drawImage(Texture img,int x,int y,int type)
{
	//System.out.println("drawImage\n");
	batch.draw(img,x,y);
}
	public void drawRegion(Texture img,int x,int y,int w,int h,int tans,int tx,int ty,int a)
	{
		//System.out.print("drawRegion"+x+" "+y+" "+w+" "+h+" "+tans+" "+tx+" "+ty+"\n");
	//FileLog.e("drawRegin"+" "+x+" "+y+" "+w+" "+h);
	boolean flipX=false, flipY=false;
		if(tans == 2){
			flipX = true;
		}
		if(img!=null)
		{
		batch.draw(img,tx,ty-h,w,h, x,y,w,h,flipX,flipY);
		//batch.draw(img,tx,ty,0,0,40,40);
		}
		else
		{
			//FileLog.e("img==null");
		}
		
	}
	
}
