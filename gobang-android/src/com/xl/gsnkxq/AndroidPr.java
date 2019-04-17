package com.xl.gsnkxq;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class AndroidPr implements PlatformResolver {
	Context _context;
	Handler hd;
	public AndroidPr(Context context){
		_context=context;
		hd=new Handler();
	}
	public boolean isNetEnable() {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null)
		{
			return false;
		}
		else
		{
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0)
			{
				for (int i = 0; i < networkInfo.length; i++)
				{
					System.out.println(i + "==net state==" + networkInfo[i].getState());
					System.out.println(i + "===net type===" + networkInfo[i].getTypeName());
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}


	public void showQuickTip(final String text) {
		// TODO Auto-generated method stub
		hd.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(_context, text, Toast.LENGTH_LONG).show();
				}
			});


	}

	public BitmapFont getFont(String characters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//游戏内购
	
	@Override
	public void callPay(final String payee,final int money) {
		// TODO Auto-generated method stub
		/*hd.post(new Runnable() {
				public void run() {
					Intent intent=new Intent(_context, ShoppingCartActivity.class);

					Bundle bundle=new Bundle();
					bundle.putString("payee",payee);
					bundle.putInt("money", money);

					intent.putExtras(bundle);
					_context.startActivity(intent);
				}
			});

*/


	}
	
	//显示对话框
	@Override
	public void showQucikDialog(final String title, final String info, final Runnable callback) {
		// TODO Auto-generated method stub
		hd.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					new  AlertDialog.Builder(_context)    
						.setTitle(title )  
						.setMessage(info)  
						.setPositiveButton("确定" ,new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								if(callback!=null)
								Gdx.app.postRunnable(callback);

							}
						})  
						.show();
				}
			});

	}

}
