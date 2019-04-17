package com.xl.sprite;

/*
画面块
矩形裁剪区
显示图像基础类
^上级：picTrue
*/

public class GameRect
{
		int x;
		int y;
		int w;
		int h;
		int px; //显示的相对位置 可以为负
		
		int py;
		
		public GameRect()
		{
			this.x=0;
			this.y=0;
			this.w=0;
			this.h=0;
		}
		
		public GameRect(int w,int h)
		{
			this.x=0;
			this.y=0;
			this.w=w;
			this.h=h;
		}
		
		public GameRect(int x,int y,int w,int h)
		{
				this.x=x;
				this.y=y;
				this.w=w;
				this.h=h;
		}
		
		public void setX(int x)
		{
				this.x=x;
		}
		
		public int getX()
		{
				return this.x;
		}
		
		public void setY(int y)
		{
				this.y=y;
		}
		
		public int getY()
		{
				return this.y;
		}
		
		public void setWidth(int w)
		{
				this.w=w;
		}
		
		public int getWidth()
		{
				return this.w;
		}
		
		public void setHeight(int h)
		{
				this.h=h;
		}
		
		public int getHeight()
		{
				return this.h;
		}
		
		public int getPX()
		{
			return px;
		}
		
		public void setPX(int px)
		{
			this.px=px;
		}
		
		public int getPY()
		{
			return py;
		}
		
		public void setPY(int py)
		{
			this.py=py;
		}
}
