package com.xl.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.files.FileHandle;

public class PicPackage
{
	TextureAtlas atlas;
	public PicPackage(String filename)
	{
		// 读取 myatlas.atlas 文件创建纹理图集
		FileHandle file = Gdx.files.internal(filename);
		if(file.exists())
		atlas = new TextureAtlas(file);
		
	}
	
	//读取图片
	public TextureAtlas.AtlasRegion readImg(String name)
	{
		// 根据名称从纹理图集中获取纹理区域
		return atlas.findRegion(name);
	}

	//
	
	//释放所有图片
	
}
