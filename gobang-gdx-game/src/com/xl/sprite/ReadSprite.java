package com.xl.sprite;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.xl.game.math.*;
//import com.xl.game.tool.*;
/*
精灵脚本加载器
还可以编写其它加载器，加载不同的脚本
*/

public class ReadSprite
{
	class StringPtr
	{
		String text;
		int ptr;
		
		public StringPtr(String str)
		{
			this.text=str;
			this.ptr=0;
		}
		
		
		public StringPtr next()
		{
			ptr++;
			if(ptr>=text.length())
			{
				ptr=text.length()-1;
			}
			return this;
		}
		public String getString()
		{
			return this.text;
		}
		public StringPtr up()
		{
			if(ptr>0)ptr--;
			return this;
		}
		//判断是否到达字符末尾
		public boolean isEnd()
		{
			if(text.length()-1==ptr)
				return true;
			return false;
		}
		public void setPtr(int index)
		{
			this.ptr=index;
		}
		public char charAt()
		{
			return text.charAt(ptr);
		}
		public int getPtr()
		{
			return ptr;
		}
	}
	//读取底层嵌套(只做解析，无意义)
	StringPtr readflip(StringPtr text)
	{
		int type=0;
		char c;
		
		while(!text.isEnd())
		{
			c=text.charAt();
			switch(type)
			{
				case 0: //读取左括号
					if(c=='<')
						type=1;


				case 1:


				case 2:


				case 3:


				case 4:
				case 8: //读取/
					if(c=='/')
					{
						type=10;
					}
					break;
				case 10: //读取最后>
					if(c=='>')
					{
						return text;
					}

			}

			text.next();
		}
		return text;
	}
//跳过头信息
	static StringPtr breakHead(StringPtr text)
	{
		int type=0;
		
		while(!text.isEnd())
		{
			switch(type)
			{
				case 0:
					if(text.charAt()=='<')
					{
						type=1;
					}
					break;
				case 1:
					if(text.charAt()=='>')
					{
						return text;
					}
					break;
				case 2:
					break;

			}
			text.next();
		}
		return text;
	}
	
	
	
	
	//读取rectflip层，返回读取后指针
	StringPtr readRectFlip(PicTure pic,StringPtr text)
	{
		int type=0;
		char c=0;
		String head=null;
		String item=null;
		String word=null;
		int start=0,end=0;
		GameRect rect=null;
		while(!text.isEnd())
		{
			c=text.charAt();//printf("%c%d ",c,type);
			switch(type)
			{
				case 0://读取左括号
					if(c=='<')
					{
						type=-1;
						start=text.getPtr()+1;
						head=text.getString().substring( start);
					}
					break;
				case -1:
					if(c=='/')
					{
						//  if(Str.strcmp(text,i,"</"+head))


						return text.up(); 
						



					}
					else if(c=='!')
					{
						text=breakHead(text);
						type=0;
					}
					else if(c=='?')
					{
						text=breakHead(text);
						type=0;
					}
					else
					{
						type=1;
					}
					break;

				case 1://读取头信息
					if(c>='0' && c<='z')
					{

					}
					else if(c=='>')//信息结束
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')//如果是上一个的括号。。。
					{

					}
					else
					{
						end=text.getPtr();
						head = text.getString().substring(start,end);
						//strncpy(head, start,(int)end-(int)start);
						//if(memcmp(head,"rectflip",8)==0)
						if(head.startsWith("rectflip"))
						{
							rect=new GameRect();
							
							// printf("添加rect\n");
							type=3;
						}
						else
						{
							//  printf("读取rectflip失败\n");
							
							return text;
						}
					}
					break;
				case 2://读取空格或回车
					if(c==' ' || c=='\n' || c=='\t')
					{
						type=3;
					}
					else if(c=='>')
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')
					{
						type=10;
					}
					break;
				case 3://读取参数
					if(c>='a' && c<='z')
					{
						start=text.getPtr();
						type=4;
					}
					else if(c=='>') //若遇到反括号，就跳转到下一层
					{

						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')
					{
						type=10;
					}
					break;
				case 4://读取= 或空格
					if(c==' '|| c=='=')
					{
						end=text.getPtr();
						item=text.getString().substring(start,end);
						type=5;
					}
					break;
				case 5://读取值
					if(c=='\"')
					{
						start=text.next().getPtr();
						type=6;
					}
					break;
				case 6:
					if(c=='\"')
					{
						end=text.getPtr();
						word=text.getString().substring(start,end);
						type=2;
						//if(memcmp(item,"x",1)==0)
						if(item.equals("x"))
						{
							rect.x=Str.atoi(word);
							//System.out.println("rectflip x="+word);
						}
						//else if(memcmp(item,"y",1)==0)
						else if(item.equals("y"))
						{
							rect.y=Str.atoi(word);
						}
						//else if(memcmp(item,"px",2)==0)
						else if(item.equals("px"))
						{
							rect.px=Str.atoi(word);
						}
						//else if(memcmp(item,"py",2)==0)
						else if(item.equals("py"))
						{
							rect.py=Str.atoi(word);
						}
						else if(item.equals("width"))
						{
							rect.w=Str.atoi(word);
						}
						else if(item.equals("height"))
						{
							rect.h=Str.atoi(word);
						}
					}
					break;
				case 7://读取末尾括号
					if(c=='<')
					{
						type=8;
					}
					break;
				case 8: //读取/
					if(c=='/')
					{
						type=10;
					}
					else if(c>='a' && c<='z')
					{
						type=1;
						start=text.getPtr();
					}
					break;
				case 10: //读取最后>
					if(c=='>')
					{
						type=0;
						pic.addRect(rect);
						// return text+1;
					}
			}
			text.next();
		}
		return text;
	}

//读取picture层
	StringPtr readPicture(Action ac,StringPtr text)
	{

		int type=0;
		char c;
		String head=null;
		String item=null;
		String word=null;
		int start=0,end=0;
		PicTure pic=null;

		while(!text.isEnd())
		{
			c=text.charAt();// printf("%c ",c);
			//System.out.print(c);
			switch(type)
			{
				case 0://读取左括号
					if(c=='<')
					{
						type=-1;
						start=text.getPtr()+1;
						head=text.getString().substring( start);
					}
					break;
				case -1:
					if(c=='/')
					{
						//  if(Str.strcmp(text,i,"</"+head))

						return text.up();




					}
					else if(c=='!')
					{
						text=breakHead(text);
						type=0;
					}
					else if(c=='?')
					{
						text=breakHead(text);
						type=0;
					}
					else
					{
						type=1;
					}
					break;

				case 1://读取头信息

					if(c=='>')//信息结束
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')//如果是上一个的括号。。。
					{

					}
					if(c<'a' || c>'z')
					{
						end=text.getPtr();
						head= text.getString().substring( start,end);//malloc((int)end-(int)start+1);
						//strncpy(head, start,(int)end-(int)start);
						if(head.equals("picture"))
						{
							pic=new PicTure(ac.getBitmap());
							ac.addPic(pic);
							//printf("添加picture\n");
							type=3;
						}
						else
						{
 Gdx.app.log("picture","读取picture失败\n"+head);
 System.out.println("读取pictrue失败"+head);
							return null;
						}
					}
					break;
				case 2://读取空格或回车
					if(c==' ' || c=='\n' || c=='\t')
					{
						type=3;
					}
					else if(c=='>')
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')
					{
						type=10;
					}
					break;
				case 3://读取参数
					if(c>='a' && c<='z')
					{
						start=text.getPtr();
						type=4;
					}
					else if(c=='>') //若遇到反括号，就跳转到下一层
					{
						// RECTFLIP *rect=new_rect();
						text=readRectFlip(pic,text.next());
						//  pic_add(pic,rect);
						type=7;
						text.up();
					}
					break;
				case 4://读取= 或空格
					if(c==' '|| c=='=')
					{
						end=text.getPtr();
						item= text.getString().substring( start,end);
						type=5;
					}
					break;
				case 5://读取值
					if(c=='\"')
					{
						start=text.next().getPtr();
						type=6;
					}
					break;
				case 6:
					if(c=='\"')
					{
						end=text.getPtr();
						word= text.getString().substring( start,end);
						type=2;

					}
					break;
				case 7://读取末尾括号
					if(c=='<')
					{
						type=8;
					}
					break;
				case 8: //读取/
					if(c=='/')
					{
						type=10;
					}
					break;
				case 10: //读取最后>
					if(c=='>')
					{
						type=0;
						//return text+1;
					}
			}
			text.next();
		}

		return text;
	}

//读取action层
	StringPtr readAction(XLSprite sp,StringPtr text)
	{
		int type=0;
		char c=0;
		String head=null;
		String item=null;
		String word=null;
		int start=0, end=0;
		Action ac=null;
		//printf("读取Action\n");
		while(!text.isEnd())
		{
			c=text.charAt();
			switch(type)
			{
				case 0://读取左括号
					if(c=='<')
					{
						type=-1;
						start=text.getPtr()+1;
						head= text.getString().substring( start);
					}
					break;
				case -1:
					if(c=='/')
					{
						//  if(Str.strcmp(text,i,"</"+head))

						return text.up();


					}
					else if(c=='!')
					{
						text=breakHead(text);
						type=0;
					}
					else if(c=='?')
					{
						text=breakHead(text);
						type=0;
					}
					else
					{
						type=1;
					}
					break;

				case 1://读取头信息

					if(c=='>')//信息结束
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')//如果是上一个的括号。。。
					{

					}
					if(c<'a' || c>'z')
					{
						end=text.getPtr();
						head= text.getString().substring(start);//malloc((int)end-(int)start+1);
						//strncpy(head, start,(int)end-(int)start);
						//if(memcmp(head,"action",6)==0)
						if(head.startsWith("action"))
						{
							ac=new Action(sp.getBitmap());
							sp.addAction(ac);
							type=3;
						}
						else
						{
//     printf("读取action失败\n");
							return null;
						}
					}
					break;
				case 2://读取空格或回车
					if(c==' ' || c=='\n' || c=='\t')
					{
						type=3;
					}
					else if(c=='>')
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')
					{
						type=10;
					}
					break;
				case 3://读取参数
					if(c>='a' && c<='z')
					{
						start=text.getPtr();
						type=4;
					}
					else if(c=='>') //若遇到反括号，就跳转到下一层
					{
						//printf("读取picture\n");
						//PICTURE *pic=pic_new();
						text=readPicture(ac,text.next());
						//ac_add(ac,pic);

						type=7;
						text.up();
					}
					break;
				case 4://读取= 或空格
					if(c==' '|| c=='=')
					{
						end=text.getPtr();
						item=text.getString().substring(start,end);
						type=5;
					}
					break;
				case 5://读取值
					if(c=='\"')
					{
						start=text.next().getPtr();
						type=6;
					}
					break;
				case 6:
					if(c=='\"')
					{
						end=text.getPtr();
						word= text.getString().substring(start,end);
						type=2;
						//if(memcmp(item,"mode",4)==0)
						if(item.equals("mode"))
						{
							ac.mode=Str.atoi(word);
							//printf("rectflip x=%d\n",rect->x);
						}
					}
					break;
				case 7://读取末尾括号
					if(c=='<')
					{
						type=8;
					}
					break;
				case 8: //读取/
					if(c=='/')
					{
						type=10;
					}
					break;
				case 10: //读取最后>
					if(c=='>')
					{
						type=0;
						//return text+1;
					}
			}
			text.next();
		}

		return text;

	}


//读取Sprite层
	public XLSprite readSprite(String filename,Texture bitmap)

	{
		int len=0;
		//String buf= XL.getTextFromAssets(context, filename);
		String buf=null;
		FileHandle file = Gdx.files.internal(filename);
		
		buf = file.readString();
		String temp;
		StringPtr text=new StringPtr(buf);
		
		/*
		 int len=getlen(filename);
		 char *buf=malloc(len+1);
		 *(buf+len)=0
		 int f=open(filename,1);
		 read(f,buf,len);
		 close(f);
		 */
		XLSprite sp=new XLSprite();
		sp.setBitmap(bitmap);
		XLSprite sprite= sp;
		//printf("创建精灵\n");
		sp.setBitmap(bitmap);

		sp.setDraw(true);
		int type=0;
		char c;
		String head=null;
		String item=null;
		String word=null;
		int start=0, end=0;
// printf("开始循环\n");
		END:
		while(!text.isEnd())
		{
			c= text.charAt();//printf(" %c%d",c,type);
			//System.out.print(c);
			switch(type)
			{
				case 0://读取左括号
					if(c=='<')
					{
						type=-1;
						start=text.getPtr()+1;
						head= text.getString().substring( start);
					}
					break;
				case -1:
					if(c=='/')
					{
						//  if(Str.strcmp(text,i,"</"+head))



						type=10;
						text.next();
						start=text.getPtr();



					}
					else if(c=='!')
					{
						text=breakHead(text);
						type=0;
					}
					else if(c=='?')
					{
						text=breakHead(text);
						type=0;
					}
					else
					{
						type=1;
					}
					break;

				case 1://读取头信息

					if(c=='>')//信息结束
					{
						text=readflip(text.next());
						type=7;
					}
					else if(c=='/')//如果是上一个的括号。。。
					{

					}
					if(c<'a' || c>'z')
					{
						end=text.getPtr();
						head= text.getString().substring( start,end);//malloc((int)end-(int)start+1);
						//strncpy(head, start,(int)end-(int)start);
						if(head.equals("sprite"))
							type=3;
						else
						{
							//    printf("读取action失败\n");
							return null;//
						}
					}
					break;
				case 2://读取空格或回车
					if(c==' ' || c=='\n' || c=='\t')
					{
						type=3;
					}
					else if(c=='>')
					{
						text=readAction(sp,text.next());
						type=7;
					}
					else if(c=='/')
					{
						type=10;
					}
					break;
				case 3://读取参数
					if(c>='a' && c<='z')
					{
						start=text.getPtr();
						type=4;
					}
					else if(c=='>') //若遇到反括号，就跳转到下一层
					{
						// ACTION *ac=ac_new();
						text=readAction(sp,text.next());
						// sp_add(sp,ac);
						type=7;
					}
					break;
				case 4://读取= 或空格
					if(c==' '|| c=='=')
					{
						end=text.getPtr();
						item=text.getString().substring(start,end);
						type=5;
					}
					break;
				case 5://读取值
					if(c=='\"')
					{
						start=text.next().getPtr();
						type=6;
					}
					break;
				case 6:
					if(c=='\"')
					{
						end=text.getPtr();
						word=text.getString().substring(start,end);
						type=2;

						if(item.equals("width"))
						{
							sprite.w=Str.atoi(word);
							//printf("rectflip x=%d\n",rect->x);
						}
						else if(item.equals("height"))
						{
							sprite.h=Str.atoi(word);
						}
						else if(item.equals("ox"))
						{
							sprite.ox=Str.atoi(word);
						}
						else if(item.equals("oy"))
						{
							sprite.oy=Str.atoi(word);
						}
					}
					break;
				case 7://读取末尾括号
					if(c=='<')
					{
						type=8;
					}
					break;
				case 8: //读取/
					if(c=='/')
					{
						type=10;
					}
					break;
				case 10: //读取最后>
					if(c=='>')
					{
						//printf("返回\n");
						type=0;
						break END;
					}
					break;
			}
			text.next();
			//printf("自增\n");
		}

		
		
		return sp;
	};
/*
	int sp_read(char *filename,int bitmap)
	{
		return (int)readSprite(filename,bitmap);
	}
*/

	
	
	
}
