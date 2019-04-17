package com.xl.sprite;
//import android.graphics.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;


/*
游戏精灵类
管理所有动作
*/



public class XLSprite
{
		int x; //屏幕坐标
		int y;
		int w; //精灵宽度
		int h;
		int ox,oy; //精灵原点位置
		
		Texture bitmap;//图片资源
		ArrayList<Action> actions;//动作数组
		
		int id; //精灵id
		int offset;//当前所处的动作
		//int size; //动作的数量
		int gravity; //显示方式
		boolean isDraw; //显示/隐藏
		
		
		/*
		创建一个空的精灵
		
		*/
		public XLSprite()
		{
			actions = new ArrayList<Action>();
			this.x=0;
			this.y=0;
			this.w=0;
			this.h=0;
			isDraw=false;
			this.bitmap=null;
			
		}
		
		/*
		通过脚本创建精灵
		
		*/
		public XLSprite(String filename)
		{
				
		}
		
		/*
		直接输入脚本创建精灵
		
		*/
		public XLSprite(String data,Texture bitmap)
		{
			
		}
		
		
		
		
		public XLSprite(Texture bitmap,ArrayList<Action> actions)
		{
				this.bitmap=bitmap;
				this.actions=actions;
				this.isDraw=true;
		}
		
		//通过图片创建精灵
		public XLSprite(Texture bitmap,int itemw,int itemh)
		{
				this.bitmap=bitmap;
				this.actions=new ArrayList<Action>();
				GameRect rect = new GameRect(itemw,itemh);
				PicTure pic = new PicTure(bitmap);
				pic.addRect(rect);
				Action action= new Action(bitmap);
				action.addPic(pic);
				addAction(action);
				this.isDraw=true;
		}
		
		public XLSprite(Texture bitmap)
		{
				this.bitmap=bitmap;
				this.actions=new ArrayList<Action>();
				GameRect rect = new GameRect(bitmap.getWidth(),bitmap.getHeight());
				PicTure pic = new PicTure(bitmap);
				pic.addRect(rect);
				Action action = new Action(bitmap);
				action.addPic(pic);
				addAction(action);
			    this.isDraw=true;
		}
		
		
		
		public void setBitmap(Texture bitmap)
		{
			this.bitmap=bitmap;
			for(Action ac:actions)
			{
				ac.setBitmap(bitmap);
			}
		}
		
		public Texture getBitmap()
		{
			return bitmap;
		}
		
		public int getFrame()
		{
				Action temp_action=actions.get(offset);
				return temp_action.getOffset();
		}
		
		public void setId(int id)
		{
				this.id=id;
		}
		
		public int getId()
		{
				return this.id;
		}
	  
		public void setDraw(boolean isdraw)
		{
				this.isDraw=isdraw;
		}
		
		public boolean isDraw()
		{
				return isDraw;
		}
		
		//添加一个动作
		public void addAction(Action action)
		{
				this.actions.add(action);
		}
		
		public XLSprite copy()
		{
				ArrayList<Action> temp_actions=new ArrayList<Action>();
				for(Action temp:actions)
				{
						temp_actions.add(temp.copy());
				}
				
				
				return new XLSprite(this.bitmap,temp_actions);
		}
		
		public int getAction()
		{
				return this.offset;
		}
		
		public void setAction(int ac)
		{
				this.offset=ac;
		}
		
		
	public void draw(SpriteBatch canvas,int x,int y)
		{
			if(actions.size()==0)
				return;
				Action temp_action=actions.get(offset);
				temp_action.draw(canvas,x-ox,y-oy);
		}
		
		public void logoc()
		{
			if(actions.size()==0)
				return;
				Action temp_action=actions.get(offset);
				temp_action.logoc();
		}
		
	public void dispose()
	{
		this.bitmap.dispose();
	}
}
