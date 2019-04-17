package com.xl.sprite;

/*
接收服务器发送过来的数据

*/

public interface OnRead
{
		public void readText(String text);
		
		public void onLoadMap(int width,int height);
		//坦克上下左右 停止 开炮
		public void onAction(int action,String user,int x,int y);
		
		public void onShowTank(String user,int type,int leve,int direction,int isRun,int x,int y);
		
		//public void onAddTank(String user,int type,int leve,int direction,int isRun,int x,int y);
		
		public void onGetName(String user,String name);
				
		public void readError(String error);
		
		public void onExit();
}
