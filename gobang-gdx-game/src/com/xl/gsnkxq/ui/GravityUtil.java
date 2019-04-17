package com.xl.gsnkxq.ui;


/*
对齐工具
根据视口大小进行对齐
支持以下几种对齐方式
  上对齐
	下对齐
	左对齐
	右对齐
	居中对齐
  左上角对齐(类似于屏幕坐标对齐方式)
	左下角对齐(libgdx默认的对齐方式)
	右上角对齐
	右下角对齐
	


风的影子
2018.5.19
*/

import com.badlogic.gdx.scenes.scene2d.Actor;

public class GravityUtil
{
	
	public static final int
	LEFT_TOP=2,
	LEFT_BOTTOM=0,
	RIGHT_TOP=3,
	RIGHT_BOTTOM=1,
	H_CENTER=4, //横向居中
	V_CENTER=6  //纵向居中
	;
	//对齐方式
	public static final int
	LEFT=0,
	CENTER=4, //居中对齐
	TOP=2,
	RIGHT=1,
	BOTTOM=0;
	
	
	private int GAME_WIDTH,GAME_HEIGHT;
	
	/*
	构造方法
	传入视口宽高，用于匹配对齐
	*/
	public GravityUtil(int view_width,int view_height)
	{
		this.GAME_WIDTH = view_width;
		this.GAME_HEIGHT = view_height;
		
	}
	
	
	/*
	 设置对齐方式，取值如下
	   LEFT
	   TOP
		 RIGHT
		 BOTTOM 
		 CENTER //居中
	 对齐方式可用|运算符叠加使用，例如
	   左上角对齐：LEFT|TOP
		 右上角对齐：RIGHT|TOP
	 注意：
	 在对齐之前需要提前设置actor的坐标
	*/
	public Actor setGravity(Actor actor,int gravity)
	{
						
			
		if((gravity& CENTER)>0)
				actor.setPosition(GAME_WIDTH/2-actor.getWidth()/2+actor.getX(),GAME_HEIGHT/2-actor.getHeight()/2-actor.getY());
	  if((gravity&TOP) >0)
		actor.setPosition(actor.getX(),GAME_HEIGHT-actor.getHeight()- actor.getY());


	  if((gravity& RIGHT) >0)
		actor.setPosition(GAME_WIDTH- actor.getWidth()-actor.getX(), actor.getY());
	
		return actor;
	}
	
	
	/*
	设置对齐方式，并设置actor的xy坐标
	*/
	public Actor setGravity(Actor actor,int gravity,float x,float y)
	{
		actor.setPosition(x,y);
		return this.setGravity(actor,gravity);
	}
	
}
