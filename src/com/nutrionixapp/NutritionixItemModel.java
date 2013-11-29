package com.nutrionixapp;

import java.io.Serializable;

import com.google.gson.JsonObject;

public class NutritionixItemModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _index;
	private String _type;
	private String _id;
	private double _score;
	//private  NutritionixSearchFieldsModel fields;
	private NutritionixSearchFieldsModel fields;
}
