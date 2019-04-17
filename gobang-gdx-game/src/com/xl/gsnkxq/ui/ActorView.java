package com.xl.gsnkxq.ui;
import com.badlogic.gdx.scenes.scene2d.Actor;

/*
演员类绑定控件
可以对演员进行命名 设置id等操作

*/
public class ActorView
{
  private String name;
	private int id;
	private Actor actor;
	
	public ActorView(Actor actor)
	{
		this.actor = actor;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
	
	//获取演员
	public Actor getView(){
		return this.actor;
	}
	
}
