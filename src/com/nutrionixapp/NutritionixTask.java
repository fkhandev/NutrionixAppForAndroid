package com.nutrionixapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.nutrionixapp.models.feedmodel.NutritionixModelResponse;


public class NutritionixTask extends AsyncTask<URL, Object, String> {

	private String APPID;
	private String APPKEY;	
	private Context context;
	private static ProgressDialog dialog = null;
	public AsyncResponse delegate = null;
	
	public enum NutritionixTaskType {
	    SEARCHBYBRAND,SEARCHBRANDID,SEARCHBYITEMID
	}
	
	public NutritionixTask(String APPID, String APPKEY)
	{
		this.APPID = APPID;
		this.APPKEY = APPKEY;
	}
	
	public void SetContext(Context context)
	{
		if (context != null)
			this.context = context;
	}
	
	/**
	 * Returns The brand search opperation allows for a stadard or autocomplete search.
	 */
	public void SearchByBrand(String query)
	{
		//GET "https://api.nutritionix.com/v1_1/brand/search?query=sugar&auto=true&type=2&min_score=1&limit=10&appId=787d8f28&appKey=0ed54244c9ad321336729664d3117924"
		String url = "https://api.nutritionix.com/v1_1/brand/search?query=%s&appId=%s&appKey=%s&auto=true&limit=15";
		url = String.format(url,query,APPID,APPKEY);
		
		try {
			this.execute(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SearchByBrandID(String brandID)
	{
		//https://api.nutritionix.com/v1_1/search/?brand_id=51db37be176fe9790a898f46&fields=*&appId=787d8f28&appKey=0ed54244c9ad321336729664d3117924
		
		String url = "https://api.nutritionix.com/v1_1/search/?brand_id=%s&appId=%s&appKey=%s&auto=true";
		url = String.format(url,brandID,APPID,APPKEY);
		try {
			this.execute(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SearchByItemID(String itemID)
	{
		//https://api.nutritionix.com/v1_1/search/?brand_id=51db37be176fe9790a898f46&fields=*&appId=787d8f28&appKey=0ed54244c9ad321336729664d3117924
		// https://api.nutritionix.com/v1_1/item?id=513fc991927da70408000274&appId=787d8f28&appKey=0ed54244c9ad321336729664d3117924"
		
		String url = "https://api.nutritionix.com/v1_1/item?id=%s&appId=%s&appKey=%s&auto=true";
		url = String.format(url,itemID,APPID,APPKEY);
		try {
			this.execute(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 @Override
	 protected void onPreExecute() {
		 dialog = ProgressDialog.show(context,"","Please Wait");
	 }
	 
	@Override
	protected String doInBackground(URL...urls) {
		// TODO Auto-generated method stub
		String serverUrl = urls[0].toString();

		String result = null;
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response;
		try {
			response = httpclient.execute(new HttpGet(serverUrl));
			StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        String responseString = out.toString();
		        
			    try{
			    	
			    	//result = gson.fromJson(responseString, NutritionixModelResponse.class);
			    	result = responseString;
			    }
			    catch(JsonSyntaxException e)
			    {
			    	e.printStackTrace();
			    }
		    } else{
		       
		        response.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		
		if (dialog!=null && dialog.isShowing()) {
            dialog.dismiss();
        }
		
		delegate.processFinish(result);

    }

}
