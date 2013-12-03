package com.nutrionixapp.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nutrionixapp.AsyncResponse;
import com.nutrionixapp.NutritionixTask;
import com.nutrionixapp.R;
import com.nutrionixapp.adapters.BrandItemListAdapter;
import com.nutrionixapp.models.BrandListingItemModel;
import com.nutrionixapp.models.feedmodel.NutritionixItemModel;
import com.nutrionixapp.models.feedmodel.NutritionixModelResponse;

public class MainActivity extends Activity implements AsyncResponse {

	NutritionixTask nutritionixTask= null; 	
	ListView searchResultsListView;
	ArrayList<BrandListingItemModel> oslist = new ArrayList<BrandListingItemModel>();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		searchResultsListView = (ListView)findViewById(R.id.listview);
		
		if(searchResultsListView != null)
		{
			searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			  @Override
			  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

			    BrandListingItemModel selectedBrandItem = (BrandListingItemModel) searchResultsListView.getItemAtPosition(position);

			    Intent intent = new Intent(MainActivity.this, ListBrandItemsActivity.class);
			    intent.putExtra("BrandListingItemModel", selectedBrandItem);
			    
			    startActivity(intent);
			    //Toast.makeText(MainActivity.this, "You Clicked at "+oslist.get(+position).get("Name"), Toast.LENGTH_SHORT).show();
			  }
			});
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void Search(View view)
	{
		EditText e = (EditText) findViewById(R.id.querytxt);
		
		String query = e.getText().toString();
		
		if(query == null || query.equals(""))
		{
			Toast.makeText(MainActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
			return;		
		}

		nutritionixTask = new NutritionixTask("787d8f28","0ed54244c9ad321336729664d3117924");
		nutritionixTask.delegate = this;
		nutritionixTask.SetContext(this);
		nutritionixTask.SearchByBrand(query);
	}

	@Override
	public void processFinish(String responseString) {
		
		if(responseString == null)
			return;
	
		if(oslist != null)
			oslist.clear();
		
		Gson gson = new Gson();		
		NutritionixModelResponse result = gson.fromJson(responseString, NutritionixModelResponse.class);
		if(result == null)
			return;
		
		for(int i =0; i< result.hits.length; i++)
		{
			NutritionixItemModel model = (NutritionixItemModel)result.hits[i];
			
			String brandid = model.getID();
			String name = model.getFields().name;
			String website = model.getFields().website;
			int total_items = model.getFields().total_items;
			BrandListingItemModel brandItem = new BrandListingItemModel(brandid,name,website,total_items);
			
			oslist.add(brandItem);

		}
		
		ListAdapter adapter = new BrandItemListAdapter(this,
                R.layout.search_results_lisitem, oslist);
                	
		searchResultsListView.setAdapter(adapter);
		
	}

}
