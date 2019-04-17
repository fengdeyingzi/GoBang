package com.xl.gsnkxq;
/*
主角逻辑

*/
public class HeroActor extends SpriteXActor
{
	
	public HeroActor(){
		super("sprite/hero.sprite", "sprite/hero.png");
		
	}
	
	//向左走
	public void left(){
		if(getAction()!=2){
			setAction(2);
		}
	}
	
	public void right(){
		if(getAction()!=1){
			setAction(1);
		}
	}
	
	//砍
	
	
	
}
