package com.nutrionixapp;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.nutrionixapp.models.NutritionixItemModel;
import com.nutrionixapp.models.NutritionixModelResponse;

public class MainActivity extends Activity implements AsyncResponse {

	NutritionixTask o = new NutritionixTask("787d8f28","0ed54244c9ad321336729664d3117924");	
	ListView searchResultsListView;
	ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		o.delegate = this;

		setContentView(R.layout.activity_main);
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
		o.SetContext(this);
		o.SearchByBrand(query);
	}

	@Override
	public void processFinish(NutritionixModelResponse result) {
		// TODO Auto-generated method stub
		for(int i =0; i< result.hits.length; i++)
		{
			NutritionixItemModel model = (NutritionixItemModel)result.hits[i];
			
			String name = model.getFields().name;
			String website = model.getFields().website;
			int total_items = model.getFields().total_items;
			
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Name", name);
			map.put("Website", website);
			map.put("Total Items", Integer.toString(total_items));			
			
			oslist.add(map);
			
			searchResultsListView = (ListView)findViewById(R.id.listview);
			ListAdapter adapter = new SimpleAdapter(MainActivity.this, oslist,
                    R.layout.search_results_lisitem,
                    new String[] { "Name","Website", "Total Items"}, new int[] {
                            R.id.name,R.id.website, R.id.totalitems});

			searchResultsListView.setAdapter(adapter);
			
			searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				  @Override
				  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				    Object o = searchResultsListView.getItemAtPosition(position);
				    /* write you handling code like...
				    String st = "sdcard/";
				    File f = new File(st+o.toString());
				    // do whatever u want to do with 'f' File object
				    */  
				    Toast.makeText(MainActivity.this, "You Clicked at "+oslist.get(+position).get("Name"), Toast.LENGTH_SHORT).show();
				  }
				});
		}

	}

}
