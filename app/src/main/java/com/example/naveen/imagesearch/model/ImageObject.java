package com.example.naveen.imagesearch.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by naveen on 06/08/2017.
 */

public class ImageObject {
    private String fullImg;
    private String thumbnail;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getFullImg() {
        return fullImg;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public ImageObject(JSONObject jsonObject){
        try {
            this.fullImg = jsonObject.getString("link");
            this.thumbnail = jsonObject.getJSONObject("image").getString("thumbnailLink");
            this.title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
