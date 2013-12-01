package com.nutrionixapp.models;

public class BrandListingItemModel {

	private String brandname;
	private String website;
	private int totalItems;
	
	public BrandListingItemModel(String brandName, String website, int totalitems)
	{
		this.brandname = brandName;
		this.website = website;
		this.totalItems = totalitems;
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
