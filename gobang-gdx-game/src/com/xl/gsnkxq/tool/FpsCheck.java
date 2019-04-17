package com.xl.gsnkxq.tool;

/*
fps检测工具
*/
public class FpsCheck
{
	int fps;
	int load;
	float time;
	long time_num;
	
	public FpsCheck()
	{
		this.fps=0;
		this.load = 0;
		this.time=0;
	}
	
	public void act(float time)
	{
		this.time+=time;
		this.load++;
		if(this.time>=1)
		{
			this.time-=1;
			this.fps = this.load;
			this.load=0;
		}
	}
	
	public void act()
	{
		long tt = System.currentTimeMillis();
		this.load++;
		if(tt- time_num>1000)
		{
			this.fps = this.load;
			this.load=0;
			this.time_num=tt;
		}
	}
	
	//获得帧率
	public int getFps(){
		return this.fps;
	}
	
	public String toString()
	{
		return "fps:"+this.fps;
	}
	
}
