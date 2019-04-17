package com.xl.gsnkxq;
import java.util.*;
/*
任务管理系统
addTask 增加任务


*/
public class GameTask
{
		
		int type; //任务类型
		//任务列表
		ArrayList<Task> tasklist;
		class Task
		{
			private TaskItem item;
			private OnTaskListener listener;
			public Task(TaskItem item,OnTaskListener listener)
			{
				this.item = item;
				this.listener = listener;
			}
			
			public TaskItem getTaskItem()
			{
				return this.item;
			}
			
			public OnTaskListener getTaskListener()
			{
				return this.listener;
			}
		}
		
		public GameTask()
		{
				tasklist = new ArrayList<Task>();
				
				
		}
		
		//增加一个任务
		public void addTask(TaskItem task,OnTaskListener list)
		{
				tasklist.add(new Task(task,list));
		}
		
		//删除一个任务
		public void removeTask(TaskItem task)
		{
			for(int i=0;i<tasklist.size();i++)
			{
				if(tasklist.get(i).getTaskItem().equals(task))
				{
				tasklist.remove(i);
				}
			}
				
		}
		
		//步进运行
		public void logoc(float time)
		{
				TaskItem task=null;
				if(tasklist.size()>0)
				{
						task = tasklist.get(0).getTaskItem();
						task.run(time);
						if(task.isEnd())
						{
							  OnTaskListener listener = tasklist.get(0).getTaskListener();
								if(listener!=null)
									listener.onTaskOk(task);
								
								removeTask(task);
						}
				}
		}
		
		
		
		//判断任务是否全部完成
		public boolean isEnd()
		{
				if(tasklist.size()==0)
						return true;
				return false;
		}
		
		//获取当前任务
		public TaskItem getTask()
		{
				TaskItem task=null;
				if(tasklist.size()>0)
				{
						return tasklist.get(0).getTaskItem();
				}
				return null;
		}
		
		public interface OnTaskListener
		{
			public void onTaskOk(TaskItem item);
		}
}
