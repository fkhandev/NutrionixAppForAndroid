package com.nutrionixapp;

import com.nutrionixapp.models.feedmodel.NutritionixModelResponse;

public interface AsyncResponse {
	void processFinish(NutritionixModelResponse result);
}
