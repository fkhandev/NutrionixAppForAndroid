package com.nutrionixapp.models;

import java.io.Serializable;

public class ItemModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String itemid;
	private String brandname;
	private String itemname;
	
	public ItemModel(String brandName, String itemname, String itemid)
	{
		this.brandname = brandName;
		this.itemname = itemname;
		this.itemid = itemid;
	}

	public String getBrandname() {
		return brandname;
	}


	public String getItemid() {
		return itemid;
	}

	public String getItemname() {
		return itemname;
	}

}
