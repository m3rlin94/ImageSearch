package com.example.naveen.imagesearch.adapter;

import com.example.naveen.imagesearch.model.ImageObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by naveen on 06/08/2017.
 */

public class ConvertJsonToList {
    public static ArrayList<ImageObject> fromJsonArray(JSONArray array){

        ArrayList<ImageObject> results = new ArrayList<>();
        for (int i=0; i < array.length(); i++){
            try {
                results.add(new ImageObject(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }
}
