package com.nutrionixapp.adapters;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nutrionixapp.R;
import com.nutrionixapp.models.BrandListingItemModel;


public class BrandItemListAdapter extends ArrayAdapter<BrandListingItemModel> {

    Context mContext;
    int layoutResourceId;
    ArrayList<BrandListingItemModel> data = null;
    
	public BrandItemListAdapter(Context context, int resource,
			ArrayList<BrandListingItemModel> oslist) {
		super(context, resource, oslist);
		this.mContext = context;
		this.layoutResourceId = resource;
		this.data = oslist;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
		BrandListingItemModel brandItem = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView brandname = (TextView) convertView.findViewById(R.id.name);
        brandname.setText(brandItem.getBrandname());
        
        TextView website = (TextView) convertView.findViewById(R.id.website);
        website.setText(brandItem.getWebsite());
        
        TextView totalitems = (TextView) convertView.findViewById(R.id.totalitems);
        totalitems.setText(Integer.toString(brandItem.getTotalItems()));
        
        return convertView;
	}

}
