package com.xl.gsnkxq.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.xl.game.math.Str;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.xl.gdx.RectDrawable;
import com.xl.gdx.BaseResources;

/*
读取ini布局
生成控件

目前支持以下控件
  ImageButton
	Image
	textbutton
	button
	lebel
*/
public class IniSource
{
	private String TAG = "INI source";
	//所有控件
	ArrayList<Actor> list_view;
	//所有贴图
	ArrayList<Texture> list_texture;
	BitmapFont font;
	String loadDir;
	int GAME_WIDTH,GAME_HEIGHT;
	
	public IniSource(String filename,int screen_w,int screen_h)
	{
		this.GAME_WIDTH = screen_w;
		this.GAME_HEIGHT = screen_h;
		this.list_view = new ArrayList<Actor>();
		this.list_texture = new ArrayList<Texture>();
		FileHandle file = Gdx.files.internal(filename);
		String text = file.readString("UTF-8");
		//font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"),Gdx.files.internal("fonts/font.png"),false);
		font = BaseResources.font;
		Gdx.app.error(TAG,"readini");
		loadDir = getDir(filename);
		readIni(text);
		//font.draw(
	}
	
	//获取目录
	private String getDir( String filename)
	{
		int end=0;
		for(int i=0;i<filename.length();i++)
		{
			char c = filename.charAt(i);
			if(c=='/'|| c=='\\')
				end=i+1;
		}
		return filename.substring(0,end);
	}
	
	//读取配置文件
	private void readIni(String text)
	{
		int type=0;
		int start=0;
		Actor actor = null;
		String head = null;
		String key = null;
		String value;
		int cen_x = 0,cen_y = 0;
		String[] items;
		Texture texture = null;
		TextureRegion texture_up = null,texture_down = null;
    String btn_text = null;
		String name = null;
		ActorFactory factory=new ActorFactory(font,GAME_WIDTH,GAME_HEIGHT);
		Color color_text = Color.WHITE;
		for(int i=0;i<text.length();i++)
		{
			char c = text.charAt(i);
			switch(type)
			{
				case 0: //[
					if(c=='[')
						type=1;
					else if(c=='#')
						type=7;
					break;
				case 1: //a-z A-Z
					if((c>='a' && c<='z') || (c>='A' && c<='Z'))
					{
						type=2; start=i;
					}
					break;
				case 2: //]
					if(c==']')
					{
						type=3;
						head = text.substring(start,i);
						factory.setName(head);
						}
					break;
				case 3: //\n
					if(c=='\n')
						type=4;
					break;
				case 4: //a-z
					if((c>='a' && c<='z') || (c>='A'&&c<='Z'))
					{
						type=5; start = i;
						}
					else if(c=='[' || i==text.length()-1){
					type=1;
					actor = factory.create();
					list_view.add(actor);
					factory.clearItem();
					/*
					if(texture_up!=null && texture_down!=null)
					{
					if(head.equals("IMAGEBUTTON"))
					{
					actor = new ImageButton(new TextureRegionDrawable(texture_up),new TextureRegionDrawable(texture_down));
					list_view.add(actor);
					}
					if(head.equals("BUTTON"))
					{
					actor = new Button(new TextureRegionDrawable(texture_up),new TextureRegionDrawable(texture_down));
					list_view.add(actor);
					}
					if(head.equals("TEXTBUTTON"))
					{
					TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
					style.up = new TextureRegionDrawable( texture_up);
					style.down = new TextureRegionDrawable(texture_down);
					style.font = font;
					style.fontColor = color_text;
					actor = new TextButton(btn_text, style);

					list_view.add(actor);
					}
					}
					if(head.equals("LABEL"))
					{

					Label.LabelStyle style =new  Label.LabelStyle();
					style.font = font;
					style.fontColor = color_text;
					style.background = new RectDrawable();
					Label label = new Label(btn_text,style);
					// 设置字体大小
					label.setFontScale(1.6F);
					// 标签包裹字体
					label.setSize(label.getPrefWidth(), label.getPrefHeight());
					list_view.add(label);
					actor = label;
					}
					*/
					}
						
					else if(c=='#')
						type=7;
					
					break;
				case 5: // =
				  if(c=='=')
					{
						type=6; key = text.substring(start,i);
						start = i+1;
						}
				  break;
				case 6: // \n
				  if(c=='\n')
					{
						type=4; 
						value = text.substring(start,i);
						value = value.trim();
						Gdx.app.error(TAG,value);
						factory.addItem(key,value);
						
						if(key.equals("img"))
						{
							texture = new Texture(Gdx.files.internal(loadDir+ value));
							list_texture.add(texture);
							factory.setTexture(texture);
						}
						/*
						if(key.equals("flip"))
						{
							items = value.split(" |,");
							int x= Str.atoi(items[0]);
							int y = Str.atoi(items[1]);
							int w = Str.atoi(items[2]);
							int h = Str.atoi(items[3]);
							
							if(head.equals("IMAGE"))
							{
								actor = new Image(new TextureRegion(texture,x,y,w,h));
								list_view.add(actor);
							}
						}
						if(key.equals("up"))
						{
							items = value.split(" |,");
							int x= Str.atoi(items[0]);
							int y = Str.atoi(items[1]);
							int w = Str.atoi(items[2]);
							int h = Str.atoi(items[3]);
							
							texture_up = new TextureRegion(texture,x,y,w,h);
							
						}
						if(key.equals("down"))
						{
							items = value.split(" |,");
							int x= Str.atoi(items[0]);
							int y = Str.atoi(items[1]);
							int w = Str.atoi(items[2]);
							int h = Str.atoi(items[3]);

							texture_down = new TextureRegion(texture,x,y,w,h);
							
						}
						if(key.equals("text"))
						{
							btn_text = value;
							
						}
						if(key.equals("textColor"))
						{
							color_text = new Color(getColorRGBA(value));
						}
						if(key.equals("x"))
						{
							actor.setX(Str.atoi(value));
						}
						else if(key.equals("y"))
						{
							actor.setY(Str.atoi(value));
						}
						else if(key.equals("name"))
						{
							actor.setName(value);
						}
						else if(key.equals("alpha"))
						{
							actor.setColor(1,1,1,((float)Str.atoi(value))/255);
						}
						else if(key.equals("width"))
						{
							actor.setWidth(Str.atoi(value));
						}
						else if(key.equals("height"))
						{
							actor.setHeight(Str.atoi(value));
							
						}
						else if(key.equals("originX"))
						{
							actor.setOriginX(Str.atoi(value));
						}
						else if(key.equals("originY"))
						{
							actor.setOriginY(Str.atoi(value));
						}
						else if(key.equals("center_x"))
						{
							cen_x = Str.atoi(value);
							actor.setCenterPosition(cen_x,cen_y);
						}
						else if(key.equals("center_y"))
						{
							cen_y = Str.atoi(value);
							actor.setCenterPosition(cen_x,cen_y);
						}
						else if(key.equals("rotation"))
						{
							actor.setRotation(Str.atoi(value));
						}
						else if(key.equals("scale"))
						{
							actor.setScale(Str.atoi(value));
						}
						else if(key.equals("visible"))
						{
							if(value.equals("true"))
							actor.setVisible(true);
							else
								actor.setVisible(false);
						}
						*/
					}
				  break;
				case 7: // 跳过
				if(c=='\n')
					type=4;
				
				break;
			}
		}
		
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
	
	
	//添加一个控件
	public void addView(Actor view)
	{
		list_view.add(view);
	}
	
	//搜索控件
	public Actor findViewByName(String name)
	{
		for(int i=0;i<list_view.size();i++)
		{
			Actor actor = list_view.get(i);
			if(actor.getName()!=null)
			{
				if(actor.getName().equals(name))
				{
					return actor;
				}
			}
		}
		return null;
	}
	
	//将所有控件添加到stage
	public void addToStage(Stage stage)
	{
		for(int i=0;i<list_view.size();i++)
		{
			stage.addActor(list_view.get(i));
		}
	}
	
	//销毁
	public void dispose()
	{
		for(int i=0;i<list_texture.size();i++)
		{
			Texture texture = list_texture.get(i);
			if(texture != null)
			{
				texture.dispose();texture=null;
			}
			if(font!=null)
			{
				font.dispose();font=null;
			}
		}
	}
	
}
