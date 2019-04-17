package com.xl.gsnkxq;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xl.game.tool.Log;
import java.io.IOException;
import java.io.InputStream;
import com.badlogic.gdx.Gdx;
import android.content.Intent;
import com.badlogic.gdx.Version;
import android.view.View;
import android.os.Build;

public class MainActivity extends AndroidApplication {
    
	MainGame gdxgame;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_FULLSCREEN |// View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
			//View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
			View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			;
		//decorView.setSystemUiVisibility(option);
		//getWindow().getDecorView().setSystemUiVisibility(option);
        Log.clear();
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //使用gles 2.0
				cfg.useGLSurfaceView20API18 = true;
		    //渲染级别 0到10
				cfg.numSamples=4;
		    //最大播放的同步声音数量
				cfg.maxSimultaneousSounds=10;
		//Gdx.app.getVersion();
		gdxgame = new MainGame();
		AndroidPr plat = new AndroidPr(this);
		gdxgame.setPlatFrom(plat);
		
        initialize(gdxgame, cfg);
		Toast.makeText(this,"gdx version:"+ Version.VERSION,0).show();
		//hideBar();
		hideBottomUIMenu();
		//Var3dFreeFont.createTextureAtlasFont(getTextFromAssets(this,"4000.txt","gbk"),24,0xfff0f0f0);
		//Var3dFreeFont.createTextureAtlasFont("风的影子 饕餮",24,0xff505050);
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		
	}
	
	

	public static String getTextFromAssets(Context context, String assetspath,String coding)
	{
		String r0_String;
		String r1_String = "";
		AssetManager assets = context.getResources().getAssets();
		try {
			InputStream input = assets.open(assetspath);
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			r0_String = new String(buffer, coding);
			input.close();
			return r0_String;
		} catch (IOException r0_IOException) {
			r0_String = r1_String;
		}


		return r0_String;

	}
	
	
	//显示导航栏
	public void showBar(){
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
			((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
           // Log.i(TAG, "Turning immersive mode mode off. ");


            //先取 非 后再 与， 把对应位置的1 置成0，原本为0的还是0

            if (Build.VERSION.SDK_INT >= 14) {
                newUiOptions &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }


            if (Build.VERSION.SDK_INT >= 16) {
                newUiOptions &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
            }


            if (Build.VERSION.SDK_INT >= 18) {
                newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        }
    }


	//隐藏导航栏
    public void hideBar() {
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
			((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (!isImmersiveModeEnabled) {
           // Log.i(TAG, "Turning immersive mode mode on. ");


            if (Build.VERSION.SDK_INT >= 14) {
                newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }


            if (Build.VERSION.SDK_INT >= 16) {
                newUiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            if (Build.VERSION.SDK_INT >= 18) {
                newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }

            getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        }
    }
	
	

	/**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
	
	
	public void debug(java.lang.String tag, java.lang.String message)
	{
		Log.e(tag,message);
		
	}

	public void debug(java.lang.String tag, java.lang.String message, java.lang.Throwable exception) 
	{
		Log.e(tag,message);
	}

	public void log(java.lang.String tag, java.lang.String message) 
	{
		Log.e(tag,message);
	}

	public void log(java.lang.String tag, java.lang.String message, java.lang.Throwable exception) 
	{
		super.log(tag,message,exception);
	}

	public void error(java.lang.String tag, java.lang.String message) 
	{
		Log.e(tag,message);
	}

	public void error(java.lang.String tag, java.lang.String message, java.lang.Throwable exception)
	{
		super.error(tag,message,exception);
	}

	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		//super.onBackPressed();
		
	}
	
	
	
}
