package com.xl.gobang.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

/**
 * 模仿java的流布局
 * @author Administrator
 *
 */
public class FlexGroup extends Group{
	
	public static String TAG= "FlexGroup";

	public static final int LEFT=1,
			TOP=2,
			RIGHT=4,
			BOTTON=6,
			CENTER=8;
	
	int padding_left,padding_right,padding_top,padding_bottom;
	
	
	//布局间隔
	int padding_view;
	//对齐方向
	int gravity;
	
	
	public FlexGroup(int width,int height){
		setWidth(width);
		setHeight(height);
		
	}
	
	//添加一个演员
	@Override
	public void addActor(Actor actor) {
		// TODO Auto-generated method stub
		super.addActor(actor);
	}
	
	//刷新布局
	public void invalidate(){
		onLayout((int)getX(), (int)getY(), (int)(getX()+getWidth()), (int)(getY()+getHeight()));
	}
	
	//
	public void onLayout(int left,int top,int right,int bottom){
		int width= right-left;
		int height= bottom-top;
		int ix=left+getPaddingLeft();
		int iy=top+getPaddingTop();
		int x_min= getPaddingLeft();
		int y_min= getPaddingTop();
		int x_max= width- getPaddingRight();
		int y_max= height-getPaddingBottom();
		
		int line_max=0;
		int line_width=0;
		int start=0;
		int start_x=x_min;
		int end=0;
		Gdx.app.log(TAG, "x_max="+x_max+" x_min="+x_min+" width="+width+"groupWidth="+getWidth());
		//开始布局内部演员
		//
		
		for(int i=0;i<getChildren().size;i++){
			Array<Actor> array_temp= getChildren();
			Actor actor= array_temp.get(i);
			//先计算当前行宽度
			if(line_width+actor.getWidth()>(width-getPaddingLeft()-getPaddingRight()) ){
				//Gdx.app.log(TAG, "line_width="+line_width);
				if((gravity&LEFT)!=0){
					start_x= x_min;
				}
				else if((gravity&RIGHT)!=0){
					start_x= x_max-line_width;
				}
				else{
					start_x= ((x_max-x_min)-line_width)/2;
				}
				line_width=(int)actor.getWidth();
				for(int ii=start;ii<i;ii++){
					Actor actor_temp= getChildren().get(ii);
					actor_temp.setPosition(start_x, iy);
					Gdx.app.log(TAG, "设置控件坐标："+start_x+" "+iy);
					Gdx.app.log(TAG, ""+(i-start));
					line_max= (int) Math.max(line_max, actor_temp.getHeight());
					start_x+=actor_temp.getWidth()+padding_view;
				}
				start=i;
				iy+=line_max+padding_view;
				line_max=0;
				
				if(iy>y_max){
				return;
				}
			}
			else if(i== getChildren().size-1){
				line_width+=actor.getWidth()+padding_view;
				if((gravity&LEFT)!=0){
					start_x= x_min;
				}
				else if((gravity&RIGHT)!=0){
					start_x= x_max-line_width;
				}
				else{
					start_x= ((x_max-x_min)-line_width)/2;
				}
				
				for(int ii=start;ii<=i;ii++){
					Actor actor_temp= getChildren().get(ii);
					actor_temp.setPosition(start_x, iy);
					Gdx.app.log(TAG, "设置控件坐标："+start_x+" "+iy);
					line_max= (int) Math.max(line_max, actor_temp.getHeight());
					start_x+=actor_temp.getWidth()+padding_view;
				}
				start=i;
				iy+=line_max+padding_view;
				line_max=0;
				
				if(iy>y_max){
				return;
				}
			}
			else{
				
				if(line_width==0){
					line_width+=actor.getWidth();
				}
				else
				line_width+=actor.getWidth()+padding_view;
				Gdx.app.log(TAG, "line_width"+line_width+" actor="+actor.getWidth());
			}
			
			
			
		}
	}
	
	//设置布局间隔
	public void setPaddingView(int padding){
		this.padding_view= padding;
	}
	
	public int getPaddingLeft(){
		return this.padding_left;
	}
	
	public int getPaddingRight(){
		return this.padding_right;
	}
	
	public int getPaddingTop(){
		return this.padding_top;
	}
	
	public int getPaddingBottom(){
		return this.padding_bottom;
	}
	
	public void setPaddingLeft(int paddingLeft){
		this.padding_left= paddingLeft;
	}
	
	public void setPaddingTop(int paddingTop){
		this.padding_top= paddingTop;
	}
	
	public void setPaddingRight(int paddingLeft){
		this.padding_right= paddingLeft;
	}
	
	public void setPaddingBottom(int paddingBottom){
		this.padding_bottom= paddingBottom;
	}
	
	public void setPadding(int left,int top,int right,int bottom) {
		this.padding_left=left;
		this.padding_right= right;
		this.padding_bottom= bottom;
		this.padding_top= top;
	}
	
	
	public void dispose() {
		Array<Actor> array= getChildren();
		for(int i=0;i<getChildren().size;i++){
			Actor actor= array.get(i);
			
		}
	}
	
	
	
}
