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
import com.nutrionixapp.models.ItemModel;


public class ItemListAdapter extends ArrayAdapter<ItemModel> {

    Context mContext;
    int layoutResourceId;
    ArrayList<ItemModel> data = null;
    
	public ItemListAdapter(Context context, int resource,
			ArrayList<ItemModel> oslist) {
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
		ItemModel item = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView brandname = (TextView) convertView.findViewById(R.id.brandname);
        brandname.setText(item.getBrandname());
        
        TextView itemname = (TextView) convertView.findViewById(R.id.itemname);
        itemname.setText(item.getItemname());
        
        TextView itemid = (TextView) convertView.findViewById(R.id.itemid);
        itemid.setText(item.getItemid());
        
        return convertView;
	}

}