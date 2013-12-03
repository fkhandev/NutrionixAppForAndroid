package com.nutrionixapp.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.nutrionixapp.AsyncResponse;
import com.nutrionixapp.NutritionixTask;
import com.nutrionixapp.R;
import com.nutrionixapp.adapters.ItemListAdapter;
import com.nutrionixapp.models.BrandListingItemModel;
import com.nutrionixapp.models.ItemModel;
import com.nutrionixapp.models.feedmodel.NutritionixItemModel;
import com.nutrionixapp.models.feedmodel.NutritionixModelResponse;


public class ListBrandItemsActivity extends Activity implements AsyncResponse {


	NutritionixTask nutritionixTask= null; 	
	ListView searchResultsBrandItemsListView;
	ArrayList<ItemModel> oslist = new ArrayList<ItemModel>();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_brand_items);
		searchResultsBrandItemsListView = (ListView)findViewById(R.id.branditemslistview);
		
		if(searchResultsBrandItemsListView != null)
		{
			searchResultsBrandItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			  @Override
			  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

			    ItemModel selectedItem = (ItemModel) searchResultsBrandItemsListView.getItemAtPosition(position);

			    Intent intent = new Intent(ListBrandItemsActivity.this, ItemDetailsActivity.class);
			    intent.putExtra("Item", selectedItem);
			    
			    startActivity(intent);
			    //Toast.makeText(MainActivity.this, "You Clicked at "+oslist.get(+position).get("Name"), Toast.LENGTH_SHORT).show();
			  }
			});
		}
		
		Bundle bundle = getIntent().getExtras();
		BrandListingItemModel branditem = (BrandListingItemModel)bundle.getSerializable("BrandListingItemModel");
		if(branditem !=null)
		{
			nutritionixTask = new NutritionixTask("787d8f28","0ed54244c9ad321336729664d3117924");
			nutritionixTask.delegate = this;
			nutritionixTask.SetContext(this);
			nutritionixTask.SearchByBrandID(branditem.getBrandId());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_brand_items_acitivity, menu);
		return true;
	}

	@Override
	public void processFinish(String  responseString) {
		// TODO Auto-generated method stub
		
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
			
			String itemid = model.getID();
			String brandname = model.getFields().brand_name;
			String itemname = model.getFields().item_name;
			
			ItemModel brandItem = new ItemModel(brandname,itemname,itemid);
			
			oslist.add(brandItem);

		}
		
		ListAdapter adapter = new ItemListAdapter(this,
                R.layout.search_results_brand_items, oslist);
                	
		searchResultsBrandItemsListView.setAdapter(adapter);
	}

}
