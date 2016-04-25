package com.example.bkchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class BkIntentService  {

	public static final String serviceEndPoint = "http://10.0.2.2:8080/bksvr/rq/bkservice?m=";
	
	public BkIntentService() {
		//super("BkIntentService");
	}
	
	
	protected void onHandleIntent(Intent intent) {
		String inputMsg = "What is the amount on my account";
		
		URL url = null;
		HttpURLConnection urlConn = null;
		String answer = "";
		BufferedReader bin = null;
		Bundle b = new Bundle();
		final ResultReceiver rec = (ResultReceiver) intent.getParcelableExtra("rec");

		try {
			url = new URL(serviceEndPoint + URLEncoder.encode(inputMsg,"UTF-8"));
			urlConn = (HttpURLConnection) url.openConnection();
			
			int respCode = -1;
				respCode = urlConn.getResponseCode();
				if(respCode == HttpURLConnection.HTTP_OK) {
				   StringBuilder ans = new StringBuilder(100000);
				   bin = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				   String inputLine;
				   while((inputLine = bin.readLine()) != null) {
					   ans.append(inputLine);
					   //ans.append("\n");
				   }
				   answer = ans.toString();
				   urlConn.disconnect();
				} else {
					urlConn.disconnect();
				}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		b.putString("resultMsg", answer);
		rec.send(0, b);
	}


	
}
