package com.xl.sprite;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
/*
动作类
上级：精灵

*/
public class Action
{
		ArrayList<PicTure> pics;
	Texture bitmap;
		String name; //动作名称
		int offset; //当前所处的帧
		//int size;//帧数
		int id;//id
		int mode; //动画播放模式 0普通模式 1循环模式
		public static final int
		MODE_NORMAL=0, //不循环
		MODE_LOOP=1, //循环
		MODE_LOOP2=2, //倒序播放
		MODE_LOOP3=3; //先顺序播放，再倒序播放，依次循环
		
		
		
	public Action(Texture bitmap)
		{
				this.pics=new ArrayList<PicTure>();
				/*
				GameRect rect = new GameRect(0,0,bitmap.getWidth(),bitmap.getHeight());
				PicTrue pic = new PicTrue(bitmap);
				pic.addRect(rect);
				addPic(pic);
				*/
				this.bitmap=bitmap;
		}
		
		public Action()
		{
			this.pics=new ArrayList<PicTure>();
			this.bitmap=null;
		}
		
	public Action(Texture bitmap, ArrayList<PicTure> pics)
		{
				this.bitmap=bitmap;
				this.pics=pics;
		}
		
	public void setBitmap(Texture bitmap)
		{
			this.bitmap=bitmap;
			for(PicTure pic:pics)
			{
				pic.setBitmap(bitmap);
			}
		}
		
		public Texture getBitmap()
		{
			return bitmap;
		}
		
	public void draw(SpriteBatch canvas,int x,int y)
		{
				PicTure pic = pics.get( offset);
				pic.draw(canvas,x,y);
		}
		
		public void setId(int id)
		{
				this.id=id;
		}
		
		public void addPic(PicTure pic)
		{
				pics.add(pic);
		}
		
		public Action copy()
		{
				return new Action(this.bitmap,this.pics);
		}
		
		public void setName(String name)
		{
				this.name=name;
		}
		
		public String getName()
		{
				return this.name;
		}
		
		public void setPlayMode(int mod)
		{
				this.mode=mod;
		}
		
		public int getPlayMode()
		{
				return this.mode;
		}
		
		public int getOffset()
		{
				return this.offset;
		}
		//动作初始化，将动作还原
		public void reset()
		{
			this.offset=0;
		}
		
		public void logoc()
		{
				int size=pics.size();
				
				offset++;
				
				if(offset>=size)
				{
						switch(mode)
						{
								case MODE_NORMAL:
										offset=size-1;
										break;
								case MODE_LOOP:
										offset=0;
						}
						
				}
				
				
				
				
		}
		
}
