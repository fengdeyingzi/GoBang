package com.xl.gsnkxq.tool;
import com.badlogic.gdx.scenes.scene2d.Actor;
/*
控制帧率的actor控件
可直接继承使用
*/
public class SpeedActor extends Actor
{
	int fps=30;
	float time;
	float speed;
	
	public SpeedActor(int fps){
		this.fps=fps;
		this.speed = 1.0f / fps;
	}

	@Override
	public void act(float delta)
	{
		// TODO: Implement this method
		super.act(delta);
		time+=delta;
		if(time>=speed){
			time-=speed;
			logoc();
		}
	}
	
	public void logoc(){
		
	}
	
	
}
