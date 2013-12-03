package com.nutrionixapp.models.feedmodel;

import java.io.Serializable;

public class NutritionixModelResponse  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	public int total;
	public double max_score;
	
	//public JsonArray hits;
	public NutritionixItemModel [] hits;
	
}
