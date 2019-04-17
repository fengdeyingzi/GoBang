package com.xl.gsnkxq;

import java.io.*;

public class FileLog
{
	private static String dir= "mnt/sdcard/";
		public static String filename = "file.log";
	public static void e(String text)
	{
		File file_dir= new File(dir);
		File file=null;
		if(!file_dir.isDirectory()){
			file= new File(filename);
		}
		else
		 file= new File(file_dir,filename);
		FileOutputStream out=null;
		
		try
		{
			 out = new FileOutputStream(file,true);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			out.write(text.getBytes());
			out.write("\n".getBytes());
		}
		catch (IOException e)
		{
			
		}
		try
		{
			out.close();
		}
		catch (IOException e)
		{}

	}
	
	
	public static void delete()
	{
			File file =new File(filename);
			if(file.exists())
			{
					file.delete();
			}
	}
	
}
