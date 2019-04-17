package com.xl.gsnkxq;
/*
控制画面透明渐变

*/
public class ViewTask implements TaskItem
{
    String name="透明渐变";
		boolean isEnd;
		float time; //总时间
		float curtime; //当前时间
		float endtime; //结束时间
		int progress; //进度
		
		int apha; //不透明度 255为不透明
		int startapha; //起始不透明度
		int endapha; //结束不透明度
		String info="在规定时间内完成透明渐变效果，可用于文字或图片的透明动画。";
		
		public ViewTask()
		{
				this.time = 1000;
				this.curtime=0;
				this.apha = 255;
				this.progress = 0;
				
		}
		
		//设置透明的起始点和结束点
		public void setData(int start,int end, float time)
		{
				this.startapha = start;
				this.endapha = end;
				this.time = time;
		}
		@Override
		public void setName(String name)
		{
				this.name=name;
		}

		@Override
		public String getName()
		{
				
				return this.name;
		}

		@Override
		public boolean isEnd()
		{
				// TODO: Implement this method
				return isEnd;
		}

		@Override
		public void run(float time)
		{
				if(isEnd)return;
				
				if(this.progress==0)
				{
						this.curtime=time;
						this.endtime = this.time+time;
				}
				else
				{
						curtime += time;
						if(curtime >= endtime)
						{
								this.progress=100;
								this.isEnd=true;
						}
						else
						{
						//透明度= 起始透明度 + 透明数*(已运行时间数/总时间)
						apha = (int)(startapha + (endapha-startapha)* (this.time-(endtime-curtime))/this.time);
								FileLog.e("透明度："+apha+startapha+" "+endapha+" "+this.time+ "\n");
						}
						
				}
				
				this.progress++;
		}

		@Override
		public int getProgress()
		{
				
				return this.progress;
		}

		@Override
		public String getInformation()
		{
				
				return info;
		}

		@Override
		public void setInformation(String text)
		{
				this.info = text;
		}
		
		
		//获取当前透明度
		public int getApha()
		{
				return this.apha;
		}
}
