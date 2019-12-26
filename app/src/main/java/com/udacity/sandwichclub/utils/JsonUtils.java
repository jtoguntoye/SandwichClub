package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject JSONSandwich = new JSONObject(json);
            JSONObject name = JSONSandwich.optJSONObject("name");

            //get the mainName of the sandwich type
            String MainName = name.optString("MainName");

            //get the alias names for the sandwich
            JSONArray alsoKnownAsArray = name.optJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            //loop through the JSONArray and store it values in a List of string if it is not null
            for(int i=0; i<alsoKnownAsArray.length(); i++){
                alsoKnownAs.add(alsoKnownAsArray.optString(i)+ ",");
                }

            //Parse the sandwich origin, description and image url from the JSON object
            String placeOfOrigin = JSONSandwich.optString("placeOfOrigin");
            String description = JSONSandwich.optString("description");
            String imageSrc = JSONSandwich.optString("image");

            //get the array of the ingredients
            JSONArray ingredientsArray = JSONSandwich.optJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();

            //loop through the JSONArray and store it values in a List of string
            for(int i=0; i<ingredientsArray.length();i++){
                ingredients.add(ingredientsArray.optString(i));
            }

            //create an instance of Sandwich.java to return to the DetailActivity
            Sandwich sandwich = new Sandwich(MainName, alsoKnownAs, placeOfOrigin, description
            , imageSrc,ingredients);

        return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
    }
}
