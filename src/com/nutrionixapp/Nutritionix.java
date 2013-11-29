package com.nutrionixapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class Nutritionix extends AsyncTask<URL, Object, Object> {

	private String APPID;
	private String APPKEY;	
	private Context context;
	
	public Nutritionix(String APPID, String APPKEY)
	{
		this.APPID = APPID;
		this.APPKEY = APPKEY;
	}
	
	public void SetContext(Context context)
	{
		this.context = context.getApplicationContext();
	}
	/**
	 * Returns The brand search opperation allows for a stadard or autocomplete search.
	 */
	public void SearchByBrand(String query)
	{
		//GET "https://api.nutritionix.com/v1_1/brand/search?query=sugar&auto=true&type=2&min_score=1&limit=10&appId=787d8f28&appKey=0ed54244c9ad321336729664d3117924"
		String url = "https://api.nutritionix.com/v1_1/brand/search?query=%s&appId=%s&appKey=%s&auto=true&limit=15";
		url = String.format(url, query,APPID,APPKEY);
		
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
		url = String.format(url, brandID);
		try {
			this.execute(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	protected Object doInBackground(URL...urls) {
		// TODO Auto-generated method stub
		String serverUrl = urls[0].toString();
		
		//NutritionixModel result = null;
		//ArrayList<NutritionixModel> r = new ArrayList<NutritionixModel>();
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		 
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
			    	Gson gson = new Gson();
			    	NutritionixModelResponse modelresponse = gson.fromJson(responseString, NutritionixModelResponse.class);
			    	
			    	//NutritionixSearchItemsByBrandResponse [] resultjson = gson.fromJson(responseString, NutritionixSearchItemsByBrandResponse[].class);
			    	
			    	
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
	protected void onPostExecute(Object result) {
		
		if(context == null)
			return;
		
		// launch activity 
		NutritionixSearchFieldsModel model = (NutritionixSearchFieldsModel)result;
				
		//ListAdapter<String> adapter = new ListAdapter<String>();
		
		Intent intent = new Intent(context, ItemDetails.class);
		
		intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("NutritionixModel", model);
		
		context.startActivity(intent);
    }

}
