package com.guillaumelin.subtitlemarker;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import com.guillaumelin.subtitlemarker.R.id;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConfigActivity extends Activity {
	
	private static String START_ACTION = "com.guillaumelin.subtitle" ;
	
	private Button mStartButton = null;
	private Button mStopButton = null;
	private Button mContactButton = null;
	private Button mCloseButton = null;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_config);
		mStartButton = (Button) this.findViewById(R.id.startBtn);
		mStartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startMark();
			}
		});
		mStopButton = (Button) this.findViewById(R.id.stopBtn);
		mStopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopMark();
			}
		});
		mContactButton = (Button)this.findViewById(R.id.contactBtn);
		mContactButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contactAuthor();
			}
		});
		mCloseButton = (Button)this.findViewById(R.id.closeBtn);
		mCloseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		doRegister();
	}
	private void doRegister(){
		new Thread(){
			public void run(){
				Stat.register();
			}
		}.start();
	}
	private void startMark(){
		Intent intent = new Intent(START_ACTION);
		this.startService(intent);
	}
	private void stopMark(){
		Intent intent = new Intent(START_ACTION);
		intent.putExtra("remove", true);
		this.startService(intent);
		
	}
	private void accessWushiyin(){
		HttpGet hg = new HttpGet("http://www.wushiyin.com/subtitle_marker?q=android");
		DefaultHttpClient hc = new DefaultHttpClient();
		try {
			hc.execute(hg);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void contactAuthor(){
		new Thread(){
			@Override
			public void run(){
				accessWushiyin();
			}
		}.start();
		
		Uri uri = Uri.parse("http://www.wushiyin.com/subtitle_marker");
		
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		try{
			this.startActivity(intent);
		}catch(ActivityNotFoundException anf){
			
		}
	}
	public void onResume(){
		super.onResume();
	}
	public void onPause(){
		super.onPause();
	}
}
