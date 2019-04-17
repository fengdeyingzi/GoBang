package com.xl.sprite;

/*

照相机类

*/

public class Camera
{
		int x,y,w,h;
		int dx,dy; //目标位置
		int v; //当前移动速度
		int maxlen; //最大移动距离
		public Camera(int width,int height)
		{
				this.x=0;
				this.y=0;
				this.w=width;
				this.h=height;
				this.v=0;
				this.maxlen=6;
		}
		
		
		public int getX()
		{
				return x;
		}
		
		public void setX(int x)
		{
				this.x=x;
				this.dx=x;
		}
		
		public int getY()
		{
				return y;
		}
		
		public void setY(int y)
		{
				this.y=y;
				this.dy=y;
		}
		
		public int getWidth()
		{
				return w;
		}
		
		public int getHeight()
		{
				return h;
		}
		
		public void move(int vx,int vy)
		{
				this.x+=vx;
				this.y+=vy;
				this.dx=this.x;
				this.dy=this.y;
		}
		
		//移动照相机到指定的位置，所谓的丝滑般照相机→_→
		public void moveTo(int x,int y)
		{
				this.dx=x;
				this.dy=y;
		}
		
		//照相机运动方法，用于丝滑般照相机。。。
		public void logoc()
		{
				if(this.dx!=this.x)
				{
						if(this.dx > this.x+v)
						{
								this.x+=v;
								this.v++;
								if(v>maxlen)v=maxlen;
						}
						if(this.dx < this.x-v)
						{
								this.x-=v;
								this.v++;
								if(v>maxlen)v=maxlen;
						}
				}
				
				
				if(this.dy!=this.y)
				{
						if(this.dy > this.y+v)
						{
								this.y+=v;
								this.v++;
								if(v>maxlen)v=maxlen;
						}
						if(this.dy < this.y-v)
						{
								this.y-=v;
								this.v++;
								if(v>maxlen)v=maxlen;
						}
				}
				
		}
}
