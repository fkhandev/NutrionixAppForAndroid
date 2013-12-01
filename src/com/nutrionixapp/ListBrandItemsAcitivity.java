package com.nutrionixapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListBrandItemsAcitivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_brand_items_acitivity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_brand_items_acitivity, menu);
		return true;
	}

}
