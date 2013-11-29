package com.nutrionixapp;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends Activity {

	Nutritionix o = new Nutritionix("787d8f28","0ed54244c9ad321336729664d3117924");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		final Button searchbtn = (Button)findViewById(R.id.searchbtn);
		
		
		
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

}
