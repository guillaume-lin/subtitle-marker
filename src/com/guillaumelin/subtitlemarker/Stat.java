package com.guillaumelin.subtitlemarker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import android.util.SparseArray;

/**
 * stat how many user have use this application
 * from where
 * @author jingx_000
 *
 */
public class Stat {
	private static final String sURL="http://www.wushiyin.com/clojure";
	public static String collect(){
		String prop[]  = {
				"Board",
				android.os.Build.BOARD,
				"Bootloader",
				android.os.Build.BOOTLOADER,
				"Brand",
				android.os.Build.BRAND,
				"Cpu_abi",
				android.os.Build.CPU_ABI,
				"Cpu_abi2",
				android.os.Build.CPU_ABI2,
				"Device",
				android.os.Build.DEVICE,
				"Display",
				android.os.Build.DISPLAY,
				"Fingerprint",
				android.os.Build.FINGERPRINT,
				"Hardware",
				android.os.Build.HARDWARE,
				"Host",
				android.os.Build.HOST,
				"Id",
				android.os.Build.ID,
				"Manufacturer",
				android.os.Build.MANUFACTURER,
				"Model",
				android.os.Build.MODEL,
				"Product",
				android.os.Build.PRODUCT,
				"Serial",
				android.os.Build.SERIAL,
				"Tags",
				android.os.Build.TAGS,
				"Type",
				android.os.Build.TYPE,
				"User",
				android.os.Build.USER,
				"Codename",
				android.os.Build.VERSION.CODENAME,
				"Incremental",
				android.os.Build.VERSION.INCREMENTAL,
				"Release",
				android.os.Build.VERSION.RELEASE,
				
				
		};
		StringBuffer qs = new StringBuffer();
		qs.append(sURL);
		qs.append('?');
	
		qs.append("Time=");
		qs.append(android.os.Build.TIME);
		qs.append('&');
		String version = getAndroidVersion();
		qs.append("Version=");
		qs.append(version);
		qs.append('&');
		qs.append("RadioVersion=");
		qs.append(android.os.Build.getRadioVersion());
		qs.append('&');
		qs.append("mac=");
		String mac = getMac("eth0");
		if(mac == null)
			mac = getMac("wlan0");
		qs.append(mac);
		for(int i=0; i<prop.length;i+=2){
			qs.append('&');
			qs.append(prop[i]);
			qs.append('=');
			qs.append(prop[i+1]);
		}
		
		return qs.toString();
	}
	private static SparseArray<String> sVersionMap = null;
	private static String getAndroidVersion(){
		if(sVersionMap == null){
			sVersionMap = new SparseArray<String>();
			sVersionMap.put(android.os.Build.VERSION_CODES.BASE,"BASE");
			sVersionMap.put(android.os.Build.VERSION_CODES.BASE_1_1,"BASE_1_1");
			sVersionMap.put(android.os.Build.VERSION_CODES.CUPCAKE,"CUPCAKE");
			sVersionMap.put(android.os.Build.VERSION_CODES.KITKAT_WATCH,"KITKAT_WATCH");
			sVersionMap.put(android.os.Build.VERSION_CODES.KITKAT,"KITKAT");
			sVersionMap.put(android.os.Build.VERSION_CODES.JELLY_BEAN_MR2,"JELLY_BEAN_MR2");
			sVersionMap.put(android.os.Build.VERSION_CODES.JELLY_BEAN_MR1,"JELLY_BEAN_MR1");
			sVersionMap.put(android.os.Build.VERSION_CODES.JELLY_BEAN,"JELLY_BEAN");
			sVersionMap.put(android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1,"ICE_CREAM_SANDWICH_MR1");
			sVersionMap.put(android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH,"ICE_CREAM_SANDWICH");
			sVersionMap.put(android.os.Build.VERSION_CODES.HONEYCOMB_MR2,"HONEYCOMB_MR2");
			sVersionMap.put(android.os.Build.VERSION_CODES.HONEYCOMB_MR1,"HONEYCOMB_MR1");
			sVersionMap.put(android.os.Build.VERSION_CODES.HONEYCOMB,"HONEYCOMB");
			sVersionMap.put(android.os.Build.VERSION_CODES.GINGERBREAD_MR1,"GINGERBREAD_MR1");
			sVersionMap.put(android.os.Build.VERSION_CODES.GINGERBREAD,"GINGERBREAD");
			sVersionMap.put(android.os.Build.VERSION_CODES.FROYO,"FROYO");
			sVersionMap.put(android.os.Build.VERSION_CODES.ECLAIR_MR1,"ECLAIR_MR1");
			sVersionMap.put(android.os.Build.VERSION_CODES.ECLAIR_0_1,"ECLAIR_0_1");
			sVersionMap.put(android.os.Build.VERSION_CODES.ECLAIR,"ECLAIR");
			sVersionMap.put(android.os.Build.VERSION_CODES.DONUT,"DONUT");
			sVersionMap.put(android.os.Build.VERSION_CODES.CUR_DEVELOPMENT,"CUR_DEVELOPMENT");
		}
		return sVersionMap.get(android.os.Build.VERSION.SDK_INT);
	}
	public static void register(){
		//Log.d("===","register");
		HttpGet get = new HttpGet(collect());
		try {
			while(true){
				//Log.d("===","start get");
				HttpResponse res = new DefaultHttpClient().execute(get);
				if(res.getStatusLine().getStatusCode() == 200){
					// OK
					//Log.d("===","OK");
					break;
				}
				try {
					//Log.d("===","failed. sleep");
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private static String getMac(String dev) {
        String macSerial = null;
        String str = "";
        try {
                Process pp = Runtime.getRuntime().exec("cat /sys/class/net/"+dev+"/address ");
                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);

                for (; null != str;) {
                        str = input.readLine();
                        if (str != null) {
                                macSerial = str.trim();// È¥¿Õ¸ñ
                                break;
                        }
                }
        } catch (IOException ex) {
                ex.printStackTrace();
                return null;
        }
        return macSerial;
    }



}
