package com.xl.gsnkxq.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.xl.game.math.Str;
import java.util.HashMap;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.xl.gdx.RectDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/*
演员构造工厂
*/

public class ActorFactory
{
	String TAG = "ActorFactory";
	BitmapFont font;
	String name;
	HashMap<String,String> map;
	String fileDir;
	Texture texture;
	int gravity;
	//屏幕宽高，用于对齐
	int GAME_WIDTH,GAME_HEIGHT;
	//对齐方式
	public static final int
	LEFT=0,
	CENTER=4, //居中对齐
	TOP=2,
	RIGHT=1,
	BOTTOM=0;
	
	
	
	public ActorFactory(BitmapFont font,int screen_w,int screen_h)
	{
		this.font= font;
		//this.name= name;
		this.GAME_WIDTH = screen_w;
		this.GAME_HEIGHT = screen_h;
		this.map = new HashMap<String,String>();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	public ActorFactory addItem(String name,String value)
	{
		map.put(name,value);
		return this;
	}
	
	//清空item
	public ActorFactory clearItem()
	{
		map.clear();
		return this;
	}
	
	public Actor create(){
		Actor actor=null;
		String value;
		//Texture texture = null;
		Drawable drawable_up = null;
		Drawable drawable_down = null;
		
		if(name.equals("BUTTON"))
		{
			if(map.get("up")!=null)
			{
				value = map.get("up");
			String items[] = value.split(" |,");
		int x= Str.atoi(items[0]);
		int y = Str.atoi(items[1]);
		int w = Str.atoi(items[2]);
		int h = Str.atoi(items[3]);

		drawable_up = new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h));
		  }
			else
			{
				Gdx.app.error(TAG,"up 未知");
			}
			if(map.get("down")!=null)
			{
			value = map.get("down");
			String items[] = value.split(" |,");
			int x= Str.atoi(items[0]);
			int y = Str.atoi(items[1]);
			int w = Str.atoi(items[2]);
			int h = Str.atoi(items[3]);

			drawable_down = new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h));
			
			}
			else
			{
				Gdx.app.error(TAG,"down 未知");
			}
			
			actor = new Button(drawable_up,drawable_down);
		}
		else if(name.equals("IMAGE"))
		{
			if(map.get("flip")!=null)
			{
				value = map.get("filp");
			String items[] = value.split(" |,");
			int x= Str.atoi(items[0]);
			int y = Str.atoi(items[1]);
			int w = Str.atoi(items[2]);
			int h = Str.atoi(items[3]);
			actor = new Image(new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h)));
			}
			else
			{
				Gdx.app.error(TAG,"flip 未知");
			}
		}
		else if(name.equals("TEXTBUTTON"))
		{
		if(map.get("up")!=null)
		{
		value = map.get("up");
		String items[] = value.split(" |,");
		int x= Str.atoi(items[0]);
		int y = Str.atoi(items[1]);
		int w = Str.atoi(items[2]);
		int h = Str.atoi(items[3]);

		drawable_up = new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h));
		}
		else
		{
		Gdx.app.error(TAG,"up 未知");
		}
		if(map.get("down")!=null)
		{
		value = map.get("down");
		String items[] = value.split(" |,");
		int x= Str.atoi(items[0]);
		int y = Str.atoi(items[1]);
		int w = Str.atoi(items[2]);
		int h = Str.atoi(items[3]);

		drawable_down = new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h));

		}
		else
		{
		Gdx.app.error(TAG,"down 未知");
		}
		
		String btn_text = null;
		if(map.get("text")!=null)
			btn_text = map.get("text");
			TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
			style.up = drawable_up;
			style.down = drawable_down;
			style.font = font;
			if(map.get("textColor")!=null)
			{
				style.fontColor = new Color(getColorRGBA(map.get("textColor")));
			}
		actor = new TextButton(btn_text,style);
		}
		else if(name.equals("LABEL")){
		Label.LabelStyle style =new  Label.LabelStyle();
		style.font = font;
		Color color_text=null;
		String btn_text = null;
		if(map.get("textColor")!=null)
		{
			value = map.get("textColor");
		  color_text = new Color(getColorRGBA(value));
		
		}
		style.fontColor = color_text;
		//style.background = new RectDrawable();
		Label label = new Label(btn_text,style);
		// 设置字体大小
		label.setFontScale(1.6F);
		// 标签包裹字体
		label.setSize(label.getPrefWidth(), label.getPrefHeight());
		
		actor = label;
		}
		else if(name.equals("IMAGEBUTTON"))
		{
		if(map.get("up")!=null)
		{
		value = map.get("up");
		String items[] = value.split(" |,");
		int x= Str.atoi(items[0]);
		int y = Str.atoi(items[1]);
		int w = Str.atoi(items[2]);
		int h = Str.atoi(items[3]);

		drawable_up = new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h));
		}
		else
		{
		Gdx.app.error(TAG,"up 未知");
		}
		if(map.get("down")!=null)
		{
		value = map.get("down");
		String items[] = value.split(" |,");
		int x= Str.atoi(items[0]);
		int y = Str.atoi(items[1]);
		int w = Str.atoi(items[2]);
		int h = Str.atoi(items[3]);

		drawable_down = new TextureRegionDrawable(new TextureRegion(texture,x,y,w,h));

		}
		else
		{
		Gdx.app.error(TAG,"down 未知");
		}

		actor = new Button(drawable_up,drawable_down);
		
		}
		
		//遍历hashmap
		//查找对齐方式
		if(map.get("gravity")!=null)
		{
			value = map.get("gravity");
			String items[] = value.split("\\|");
			for(int i=0;i<items.length;i++)
			{
				if(items[i].equals("left"))
				{
					//this.gravity|=LEFT;
				}
				else if(items[i].equals("right"))
				{
					this.gravity|=RIGHT;
				}
				else if(items[i].equals("top"))
				{
					this.gravity|=TOP;
				}
				else if(items[i].equals("bottom"))
				{
					//this.gravity|=BOTTOM;
				}
				else if(items[i].equals("center"))
				{
					this.gravity|=CENTER;
				}
			}
			
		}
		
		//查找宽高设置
		if(map.get("width")!=null)
		{
			actor.setWidth(Str.atoi(map.get("width")));
		}
		if(map.get("height")!=null)
		{
			actor.setHeight(Str.atoi(map.get("height")));
			
		}
		//设置xy
		if(map.get("x")!=null)
		{
			if((gravity & RIGHT)==0) //左对齐
			actor.setX(Str.atoi(map.get("x")));
			if((gravity & RIGHT)!=0) //右对齐
			actor.setX(GAME_WIDTH- actor.getWidth()-Str.atoi(map.get("x")));
			if((gravity & CENTER)!=0) //
			actor.setX((GAME_WIDTH-actor.getWidth())/2+Str.atoi(map.get("x")));
		}
		if(map.get("y")!=null)
		{
			if((gravity& TOP)!=0)
			{
				actor.setY(GAME_HEIGHT-actor.getHeight()- Str.atoi(map.get("y")));
			}
			else if((gravity & CENTER)!=0)
			{
				actor.setY(GAME_HEIGHT/2-actor.getHeight()/2-Str.atoi(map.get("y")));
			}
			else
			actor.setY(Str.atoi(map.get("y")));
		}
		//设置alpha
		if(map.get("alpha")!=null)
		{
			value = map.get("alpha");
		actor.setColor(1,1,1,((float)Str.atoi(value))/255);
		}
		//获取旋转中心
		if(map.get("center_x")!=null && map.get("center_y")!=null)
		{
			value = map.get("center_x");
			
			actor.setCenterPosition(Str.atoi(map.get("center_x")),Str.atoi(map.get("center_y")));
		}
		//设置旋转角度
		if(map.get("rotation")!=null)
		{
			value = map.get("rotation");
			actor.setRotation(Str.atoi(value));
		}
		
		//比例
		if(map.get("scale")!=null)
		{
			actor.setScale(Str.atoi(map.get("scale")));
		}
		//是否显示
		if(map.get("isVisible")!=null)
		{
			if(map.get("isVisible").equals("false"))
			{
				actor.setVisible(false);
			}
		}
		//设置名字
		if(map.get("name")!=null)
		{
			value = map.get("name");
			actor.setName(value);
		}
		//起源？？
		if(map.get("origin_x")!=null)
		{
			value = map.get("origin_x");
			actor.setOriginX(Str.atoi(value));
		}
		if(map.get("origin_y")!=null)
		{
			value = map.get("origin_y");
			actor.setOriginY(Str.atoi(value));
		}
		
		return actor;
	}


	private int getColorRGBA(String text)
	{
	int color=0;
	int argb[]=new int[4];
	int start=0;
	int i=0;
	int hex=0; //颜色位数 有3 4 6 8
	for(i=0;i<text.length();i++)
	{
	if(text.charAt(i)=='#')
	{
	start=i+1;
	hex=text.length()-start;
	}

	}
	if(hex==3)
	{
	for(i=0;i<3;i++)
	{
	char c=text.charAt(start+i);
	argb[0]=0xff;
	if(c>='A'&&c<='F')
	{
	argb[i+1]=(c-'A'+10)*16;
	}
	else if(c>='a'&&c<='f')
	{
	argb[i+1]=(c-'a'+10)*16;
	}
	else if(c>='0'&&c<='9')
	{
	argb[i+1]=(c-'0')*16;
	}
	}
	}
	else if(hex==6)
	{
	argb[0]=0xff;
	for(i=0;i<3;i++)
	{
	char c=text.charAt(start+i*2);
	char c2=text.charAt(start+i*2+1);

	if(c>='A'&&c<='F')
	{
	argb[i+1]=(c-'A'+10)<<4;
	}
	else if(c>='a'&&c<='f')
	{
	argb[i+1]=(c-'a'+10)<<4;
	}
	else if(c>='0'&&c<='9')
	{
	argb[i+1]=(c-'0')<<4;
	}
	if(c2>='A'&&c2<='F')
	{
	argb[i+1]|=(c2-'A'+10);
	}
	else if(c2>='a'&&c2<='f')
	{
	argb[i+1]|=(c2-'a'+10);
	}
	else if(c2>='0'&&c2<='9')
	{
	argb[i+1]|=(c2-'0');
	}
	}
	}
	else if(hex==4)
	{
	for(i=0;i<4;i++)
	{
	char c=text.charAt(start+i);
	if(c>='A'&&c<='Z')
	{
	argb[i]=((c-'A')+10)*16;
	}
	else if(c>='a'&&c<='z')
	{
	argb[i]=(c-'a'+10)*16;
	}
	else if(c>='0'&&c<='9')
	{
	argb[i]=(c-'0')*16;
	}
	}
	}
	else if(hex==8)
	{
	for(i=0;i<4;i++)
	{
	char c=text.charAt(start+i*2);
	char c2=text.charAt(start+i*2+1);
	if(c>='A'&&c<='F')
	{
	argb[i]=(c-'A'+10)<<4;
	}
	else if(c>='a'&&c<='f')
	{
	argb[i]=(c-'a'+10)<<4;
	}
	else if(c>='0'&&c<='9')
	{
	argb[i]=(c-'0')<<4;
	}
	if(c2>='A'&&c2<='F')
	{
	argb[i]|=(c2-'A'+10);
	}
	else if(c2>='a'&&c2<='f')
	{
	argb[i]|=(c2-'a'+10);
	}
	else if(c2>='0'&&c2<='9')
	{
	argb[i]|=(c2-'0');
	}
	}
	}
	color=(argb[1]<<24)|(argb[2]<<16)|(argb[3]<<8)|(argb[0]);

	return color;
	}
	
}
