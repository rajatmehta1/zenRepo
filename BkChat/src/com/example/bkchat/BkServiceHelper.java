package com.example.bkchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.os.AsyncTask;

//keeping the class simple due to lack of time
public class BkServiceHelper extends AsyncTask<String, Void, String>{
	
	public static final String serviceEndPoint = "http://10.0.2.2:8080/bksvr/rq/bkservice?m=";
	
	//public static final String serviceEndPoint = "http://10.87.0.28:8080/bksvr/rq/bkservice?m=";
	private String resultMsg; 
	
	public BkServiceHelper() {		
	}
	
	public String getResponse(String inputMsg) {
		return this.serviceCall(inputMsg);
	}
	
	public String serviceCall(String inputMsg) {
		URL url = null;
		HttpURLConnection urlConn = null;
		String answer = "";
		BufferedReader bin = null;
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
					return "Sorry service is down currently. Please try later.";
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
		return answer;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return this.serviceCall(params[0]);
	}
	
	@Override
	protected void onPostExecute(String result) {
		//this.resultMsg = value;
		super.onPostExecute(result);
	}
	
	public String getResult() {
		return this.resultMsg;
	}
	
}
