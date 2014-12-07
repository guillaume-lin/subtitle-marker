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
    	//��ȡWindowManager
    	wm=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    	//����LayoutParams(ȫ�ֱ�������ز���
    	wmParams = ((MyApplication)getApplication()).getMywmParams();
    	
    	/**
    	 *���¶���WindowManager.LayoutParams���������
    	 * ������;�ɲο�SDK�ĵ�
    	 */
    	wmParams.type=LayoutParams.TYPE_PHONE;   //����window type
    	wmParams.format=PixelFormat.RGBA_8888;   //����ͼƬ��ʽ��Ч��Ϊ����͸��
    	
    	//����Window flag
    	wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
    	                               | LayoutParams.FLAG_NOT_FOCUSABLE;
    	/*
    	 * �����flags���Ե�Ч����ͬ����������
    	 * ���������ɴ������������κ��¼�,ͬʱ��Ӱ�������¼���Ӧ��
    	          wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL 
    	                                | LayoutParams.FLAG_NOT_FOCUSABLE
    	                                | LayoutParams.FLAG_NOT_TOUCHABLE;
    	 */
    	

    	wmParams.gravity=Gravity.LEFT|Gravity.TOP;   //�����������������Ͻ�
    	int sw = wm.getDefaultDisplay().getWidth();
    	int sh = wm.getDefaultDisplay().getHeight();
    	
    	//����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
    	wmParams.x=0;
    	wmParams.y=sh - 50;
    	
    	//�����������ڳ�������
    	wmParams.width= sw;
    	wmParams.height=50;
    	     
    	//��ʾmyFloatViewͼ��
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
