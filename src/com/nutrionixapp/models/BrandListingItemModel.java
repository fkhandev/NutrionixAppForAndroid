package com.nutrionixapp.models;

import java.io.Serializable;

public class BrandListingItemModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brandid;
	private String brandname;
	private String website;
	private int totalItems;
	
	public BrandListingItemModel(String brandid, String brandName, String website, int totalitems)
	{
		this.brandid = brandid;
		this.brandname = brandName;
		this.website = website;
		this.totalItems = totalitems;		
	}

	public String getBrandId(){
		return brandid;
	}
	
	public int getTotalItems() {
		return totalItems;
	}

	public String getWebsite() {
		return website;
	}

	public String getBrandname() {
		return brandname;
	}

}
