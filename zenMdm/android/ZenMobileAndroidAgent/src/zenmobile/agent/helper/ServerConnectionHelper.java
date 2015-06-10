package zenmobile.agent.helper;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.net.HttpURLConnection;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.List;



import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.BufferedHttpEntity;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicHttpResponse;

import org.apache.http.protocol.HTTP;

import org.apache.http.util.EntityUtils;



import android.util.Log;



import zenmobile.agent.util.Constants;



/*

* Connects to the http server for polling, fetching data and pushing data to the server

*/

public class ServerConnectionHelper {

    static final String TAG = "ServerConnectionHelper";

    public ServerConnectionHelper() {

        

    }

    

    public boolean isDeviceEnrolled() {

        String enrollStatus =  this.readFromServer(Constants.POLLING_SERVER + Constants.DEVICE_ENROLL_URL);

        if("Y".equals(enrollStatus)) 

            return true;

        else return false;  

    }

    

   public String readFromServer(String urlParam) {



       try {

           URL url = new URL(urlParam);

           HttpGet httpRequest = null;

               httpRequest = new HttpGet(url.toURI());



           HttpClient httpclient = new DefaultHttpClient();

           HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);

           HttpEntity entity = response.getEntity();

           BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);

           InputStream input = bufHttpEntity.getContent();

           BufferedReader r = new BufferedReader(new InputStreamReader(input));

               String result = "";

               String line = "";

               while((line = r.readLine()) != null) {

                   result += line;

               }

               return result;

               //             input.close();

       } catch (MalformedURLException e) {

           e.printStackTrace();

       } catch (Exception e) {

           e.printStackTrace();

       }

       

       return "no Result";

    }

   

   public void postDataToServer(String urlParam,List<NameValuePair> nameValuePairs) {

       // Create a new HttpClient and Post Header

       HttpClient httpclient = new DefaultHttpClient();

       HttpPost httppost = new HttpPost(urlParam);



       try {

           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));



           // Execute HTTP Post Request

           HttpResponse response = httpclient.execute(httppost);

           HttpEntity entity = response.getEntity();

           Log.i(TAG, EntityUtils.getContentCharSet(entity));

       } catch (ClientProtocolException e) {

           e.printStackTrace();

       } catch (IOException e) {

           e.printStackTrace();

       }

   } 

    

   //posting xml

   public void postXmlToServer(String urlParam,String dataXML) {

       // Create a new HttpClient and Post Header

       HttpClient httpclient = new DefaultHttpClient();

       HttpPost httppost = new HttpPost(urlParam);       

       try {

               StringEntity se = new StringEntity(dataXML);

                   httppost.setEntity(se);  

               HttpResponse httpresponse = httpclient.execute(httppost);

               HttpEntity resEntity = httpresponse.getEntity();

               Log.i(TAG,EntityUtils.toString(resEntity));

       }

       catch (ClientProtocolException e) {

               e.printStackTrace();

       } 

       catch (IOException e) {

               e.printStackTrace();

       }



   } 

   

   public void postXmlToServer(String dataXML) {

       // Create a new HttpClient and Post Header

       HttpClient httpclient = new DefaultHttpClient();

       HttpPost httppost = new HttpPost(Constants.MDM_SERVER_URL);       

       try {

               StringEntity se = new StringEntity(dataXML);

                   httppost.setEntity(se);  

               HttpResponse httpresponse = httpclient.execute(httppost);

               HttpEntity resEntity = httpresponse.getEntity();

               Log.i(TAG,EntityUtils.toString(resEntity));

       }

       catch (ClientProtocolException e) {

               e.printStackTrace();

       } 

       catch (IOException e) {

               e.printStackTrace();

       }



   }    

   

}