package com.nutrionixapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nutrionixapp.AsyncResponse;
import com.nutrionixapp.NutritionixTask;
import com.nutrionixapp.R;
import com.nutrionixapp.models.ItemModel;
import com.nutrionixapp.models.feedmodel.NutritionixSearchFieldsModel;

public class ItemDetailsActivity extends Activity implements AsyncResponse {

	NutritionixTask nutritionixTask= null; 	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_details);
		
		
		Bundle bundle = getIntent().getExtras();
		ItemModel branditem = (ItemModel)bundle.getSerializable("Item");
		if(branditem !=null)
		{
			nutritionixTask = new NutritionixTask("787d8f28","0ed54244c9ad321336729664d3117924");
			nutritionixTask.delegate = this;
			nutritionixTask.SetContext(this);
			nutritionixTask.SearchByItemID(branditem.getItemid());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_details, menu);
		return true;
	}

	@Override
	public void processFinish(String responseString) {

		Gson gson = new Gson();
		
		NutritionixSearchFieldsModel result = gson.fromJson(responseString, NutritionixSearchFieldsModel.class);
		if(result == null)
		{
			return;
		}
		
		((TextView)findViewById(R.id.brandname)).setText(result.brand_name);
		
		((TextView)findViewById(R.id.itemname)).setText(result.item_name);
		
		((TextView)findViewById(R.id.itemdesc)).setText(result.item_description);

		String nf_ingredient_statement = result.nf_ingredient_statement == null ? "" : android.text.Html.fromHtml(result.nf_ingredient_statement).toString();
		
		((TextView)findViewById(R.id.nf_ingredient_statement)).setText(nf_ingredient_statement);
		
		((TextView)findViewById(R.id.nf_calories)).setText(result.nf_calories);
		
		((TextView)findViewById(R.id.nf_calories_from_fat)).setText(result.nf_calories_from_fat);
		
		((TextView)findViewById(R.id.nf_total_fat)).setText(result.nf_total_fat);
		
		((TextView)findViewById(R.id.nf_saturated_fat)).setText(result.nf_saturated_fat);
		
		((TextView)findViewById(R.id.nf_monounsaturated_fat)).setText(result.nf_monounsaturated_fat);
		
		((TextView)findViewById(R.id.nf_trans_fatty_acid)).setText(result.nf_trans_fatty_acid);
		
		((TextView)findViewById(R.id.nf_cholesterol)).setText(result.nf_cholesterol);
		
		((TextView)findViewById(R.id.nf_sodium)).setText(result.nf_sodium);
		
		((TextView)findViewById(R.id.nf_sodium)).setText(result.nf_sodium);
		
		((TextView)findViewById(R.id.nf_total_carbohydrate)).setText(result.nf_total_carbohydrate);
		
		((TextView)findViewById(R.id.nf_dietary_fiber)).setText(result.nf_dietary_fiber);
		
		((TextView)findViewById(R.id.nf_sugars)).setText(result.nf_sugars);
		
		((TextView)findViewById(R.id.nf_protein)).setText(result.nf_protein);
		
		((TextView)findViewById(R.id.nf_servings_per_container)).setText(result.nf_servings_per_container);
		
		((TextView)findViewById(R.id.nf_serving_size_qty)).setText(result.nf_serving_size_qty);
		
		((TextView)findViewById(R.id.nf_serving_size_unit)).setText(result.nf_serving_size_unit);
		
		((TextView)findViewById(R.id.nf_serving_weight_grams)).setText(result.nf_serving_weight_grams);
		
		
		
		
	}

}
