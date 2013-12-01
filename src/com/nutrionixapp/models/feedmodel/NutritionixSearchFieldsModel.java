package com.nutrionixapp.models.feedmodel;

import java.io.Serializable;

public class NutritionixSearchFieldsModel implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String brand_name;
	public String name;
	public String website;
	public String type;
	public int total_items;
	public String item_name;
	public String brand_id;
	public String item_id;
	public String upc;
	public String item_type;
	public String item_description;	
	public String nf_ingredient_statement;	
	public String nf_water_grams;	
	public String nf_calories;
	public String nf_calories_from_fat;	
	public String nf_total_fat;
	public String nf_saturated_fat;
	public String nf_monounsaturated_fat;
	public String nf_polyunsaturated_fat;
	public String nf_trans_fatty_acid;
	public String nf_cholesterol;
	public String nf_sodium;
	public String nf_total_carbohydrate;
	public String nf_dietary_fiber;
	public String nf_sugars;
	public String nf_protein;
	public String nf_vitamin_a_iu;	
	public String nf_vitamin_a_dv;
	public String nf_vitamin_c_mg;
	public String nf_vitamin_c_dv;
	public String nf_calcium_mg;
	public String nf_calcium_dv;
	public String nf_iron_mg;
	public String nf_iron_dv;
	public String nf_refuse_pct;
	public String nf_servings_per_container;
	public int nf_serving_size_qty;
	public String nf_serving_size_unit;
	public int nf_serving_weight_grams;
	public boolean allergen_contains_milk;
	public boolean allergen_contains_eggs;
	public boolean allergen_contains_fish;
	public boolean allergen_contains_shellfish;
	public boolean allergen_contains_tree_nuts;
	public boolean allergen_contains_peanuts;
	public boolean allergen_contains_wheat;
	public boolean allergen_contains_soybeans;
	public boolean allergen_contains_gluten;
	public String created_at;
	public String updated_at;	
}
