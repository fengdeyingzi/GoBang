package com.xl.gsnkxq;

public interface TaskItem
{
		//设置任务名字
		public void setName(String name);
		//获取任务名字
		public String getName();
		
		//判断任务是否结束
		public boolean isEnd();
		//任务执行
		public void run(float time);
		//获取当前执行进度
		public int getProgress();
		//获取任务说明
		public String getInformation();
		//设置任务说明
		public void setInformation(String text);
}
