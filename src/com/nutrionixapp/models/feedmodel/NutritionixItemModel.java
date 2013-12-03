package com.nutrionixapp.models.feedmodel;

import java.io.Serializable;

public class NutritionixItemModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _index;
	private String _type;
	private String _id;
	private double _score;
	private NutritionixSearchFieldsModel fields;
	
	public String getIndex()
	{
		return _index;
	}
	
	public String getType() {return _type;}
	
	public String getID() {return _id;}
	
	public double getScore(){return _score;}
	
	public NutritionixSearchFieldsModel getFields(){return fields;}
	
}
