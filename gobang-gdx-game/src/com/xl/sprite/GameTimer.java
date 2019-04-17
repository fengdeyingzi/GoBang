package com.xl.sprite;
/*
游戏定时器控制

*/

public class GameTimer
{
		int speed; //
		long lasttime; //
		TimerListener listener;
		public GameTimer(TimerListener listener)
		{
				this.listener=listener;
				this.speed=30;
				this.lasttime=System.currentTimeMillis();
				
		}
		boolean logoc()
		{
				long temp;
				boolean isRun=false;
				while((temp=System.currentTimeMillis()) - lasttime>= speed)
				{
						lasttime+=speed;
						isRun=true;
						if(listener!=null)
								listener.onTimer();
				}
				return isRun;
		}
		
		
		public void pause()
		{
				
		}
		
		public void resume()
		{
				lasttime=System.currentTimeMillis();
		}
		
		public void setOnTimerListener(TimerListener lis)
		{
				this.listener=lis;
		}
		
		
		public interface TimerListener
		{
				public void onTimer();
				
						
				
		}
		
}
