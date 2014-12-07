package com.guillaumelin.subtitlemarker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;



public class MyFloatView extends android.widget.ImageView {
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    
    private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    
    //��wmParamsΪ��ȡ��ȫ�ֱ��������Ա����������ڵ�����
    private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();

    public MyFloatView(Context context) {
        super(context);        
        // TODO Auto-generated constructor stub
    }
    Paint paint = new Paint();
    @Override
    public void onDraw(Canvas canvas){
    	paint.setColor(Color.BLACK);
    	canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }
     @Override
     public boolean onTouchEvent(MotionEvent event) {
         
         //getRawX()��ȡ�����Ļ�����꣬������Ļ���Ͻ�Ϊԭ��         
         x = event.getRawX();   
         y = event.getRawY()-25;   //25��ϵͳ״̬���ĸ߶�
         Log.i("currP", "currX"+x+"====currY"+y);
         switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //getX()��ȡ���View�����꣬���Դ�View���Ͻ�Ϊԭ��
                mTouchStartX =  event.getX(); 
                mTouchStartY =  event.getY();
                
                Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);
                
                break;
            case MotionEvent.ACTION_MOVE:                
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchStartX=mTouchStartY=0;
                break;
            }
            return true;
        }
     
     private void updateViewPosition(){
        //���¸�������λ�ò���,x���������Ļ��λ�ã�mTouchStartX�������ͼƬ��λ��
        wmParams.x=(int)( x-mTouchStartX);
        System.out.println(mTouchStartX);
        wmParams.y=(int) (y-mTouchStartY);
        wm.updateViewLayout(this, wmParams);
        
     }

}
