package com.guillaumelin.subtitlemarker;


import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class SubtitleMarkerService extends Service{

   
    private WindowManager wm=null;
    private WindowManager.LayoutParams wmParams=null;
         
    private MyFloatView myFV=null;
    private void createView(){
    	myFV=new MyFloatView(getApplicationContext());
    	//myFV.setImageResource(R.drawable.icon);
    	//获取WindowManager
    	wm=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    	//设置LayoutParams(全局变量）相关参数
    	wmParams = ((MyApplication)getApplication()).getMywmParams();
    	
    	/**
    	 *以下都是WindowManager.LayoutParams的相关属性
    	 * 具体用途可参考SDK文档
    	 */
    	wmParams.type=LayoutParams.TYPE_PHONE;   //设置window type
    	wmParams.format=PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明
    	
    	//设置Window flag
    	wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
    	                               | LayoutParams.FLAG_NOT_FOCUSABLE;
    	/*
    	 * 下面的flags属性的效果形同“锁定”。
    	 * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
    	          wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL 
    	                                | LayoutParams.FLAG_NOT_FOCUSABLE
    	                                | LayoutParams.FLAG_NOT_TOUCHABLE;
    	 */
    	

    	wmParams.gravity=Gravity.LEFT|Gravity.TOP;   //调整悬浮窗口至左上角
    	int sw = wm.getDefaultDisplay().getWidth();
    	int sh = wm.getDefaultDisplay().getHeight();
    	
    	//以屏幕左上角为原点，设置x、y初始值
    	wmParams.x=0;
    	wmParams.y=sh - 50;
    	
    	//设置悬浮窗口长宽数据
    	wmParams.width= sw;
    	wmParams.height=50;
    	     
    	//显示myFloatView图像
    	wm.addView(myFV, wmParams);
    	Log.d("Subtitle","showed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
    	createView();
    	return START_STICKY;
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
