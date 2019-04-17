package com.xl.sprite;
//import android.graphics.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
/*
画面
显示一张静态图
上级：Action
*/

public class PicTure
{
		ArrayList<GameRect> rects;
	Texture bitmap;
	ArrayList<TextureRegion> texttureRegions;
	public PicTure(Texture bitmap)
		{
				this.rects=new ArrayList<GameRect>();
				this.bitmap = bitmap;
				this.texttureRegions = new ArrayList<TextureRegion>();
		}
		
		public PicTure()
		{
			this.bitmap=null;
			this.rects=new ArrayList<GameRect>();
			this.texttureRegions = new ArrayList<TextureRegion>();
		}
		
	public void setBitmap(Texture bitmap)
		{
			this.bitmap=bitmap;
		}
		
		//给画面添加一个裁剪区域
		public void addRect(GameRect rect)
		{
				rects.add(rect);
			// 创建纹理区域, region 表示在 texture 中 坐标 (0, 0) 为起点, 宽高均为 128 的矩形区域（即为图片 1/4 的左上角部分）
			TextureRegion region = new TextureRegion(bitmap, rect.getPX(), rect.getPY(), rect.getWidth(), rect.getHeight());
			//System.out.println("add "+rect.getX()+" "+rect.getY()+" "+rect.getWidth()+" "+rect.getHeight());
			texttureRegions.add(region);
		}
		//显示画面
	public void draw(SpriteBatch canvas,int x,int y)
		{
				/*
				for(GameRect rect:rects)
				{
						Rect dst=new Rect(x+rect.getX(),y+rect.getY(),x + rect.getX()+ rect.getWidth(), y + rect.getY()+rect.getHeight());
						Rect src=new Rect(rect.getX(),rect.getY(),rect.getX() + rect.getWidth(),rect.getY() + rect.getHeight());
						
						canvas.drawBitmap(bitmap,src,dst,null);
				}
				*/
				for(int i=0;i<rects.size();i++)
				{
					TextureRegion region = texttureRegions.get(i);
					GameRect rect = rects.get(i);
				canvas.draw(region,x+rect.getX(),y+rect.getY());
				}
		}
		//销毁画面(不需要，自动回收)
		
}
