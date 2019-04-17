package com.xl.gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GdxToast extends Label
{
	private CharSequence text;
	private int time;
	private int load;
	private boolean isShow;
	private static GdxToast toast;
	public GdxToast(java.lang.CharSequence text, com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style) 
	{
		super(text,style);
	}
	
	public GdxToast(java.lang.CharSequence text, Skin skin){
		super(text,skin,"toast");
		
	}
	
	public GdxToast(java.lang.CharSequence text, Skin skin, String styleName){
		super(text,skin,styleName);
		// 标签包裹字体
		//this.setSize(getPrefWidth()+70, getPrefHeight());
		this.setAlignment(Align.center);
		this.time=2300;
		setVisible(false);
	}
	public static GdxToast makeToast(CharSequence text,int type)
	{
		if(toast==null)
		{
			Label.LabelStyle style = new Label.LabelStyle();
			style.fontColor = Color.BLACK;
			style.font = BaseResources.font;
			style.background = BaseResources.drawable_toast_background;
			toast = new GdxToast(text,style);
			
			
		}
	toast.isShow=false;
	toast.text = text;
	toast.setText(text);
	toast.setScaleX(toast.getWidth()/2);
	// 设置字体大小
	toast.setFontScale(1.6F);
	// 标签包裹字体
	toast.setSize(toast.getPrefWidth()+70, toast.getPrefHeight());
	//toast.setBounds(30,30,30,30);
	//toast.setCenterPosition(toast.getWidth()/2,toast.getHeight()/2);
	toast.setAlignment(Align.center);
	toast.load=0;
	if(type==0)
		toast.time=2300;
	else if(type==1)
		toast.time=5000;
	toast.setVisible(false);
		return toast;
	}
	
	public void show()
	{
		this.isShow=true;
		this.setVisible(true);
		this.load=0;
	}

	@Override
	public void act(float delta)
	{
	// TODO: Implement this method
	super.act(delta);
	this.load+= (int)(delta*1000);
	if(load>=time && isVisible())
	{
		setVisible(false);
		this.isShow=false;
		this.load =0;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
	// TODO: Implement this method
	super.draw(batch, parentAlpha);
	}
	
	
	
}
