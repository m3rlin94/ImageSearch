package com.example.naveen.imagesearch.adapter;

import android.content.Context;
import android.widget.Toast;

import com.example.naveen.imagesearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.net.URLEncoder;

/**
 * Created by naveen on 06/08/2017.
 */

public class SearchImages {
    private static final String API_KEY = "AIzaSyAeOxyQBD8dGgifX-NqP-L-NVIcxBAIo_s";
    private static final String CX_KEY = "005950685689427299036:0w3_0uka6xs";
    private AsyncHttpClient searchClient;

    public SearchImages() {
        this.searchClient = new AsyncHttpClient();
    }

    public void runQuery(String query, int page, Context context, JsonHttpResponseHandler handler) {
        try {
            String url = "https://www.googleapis.com/customsearch/v1?q=" + URLEncoder.encode(query, "utf-8") + "&safe=high&start=1&cx=" + CX_KEY + "&searchType=image&imgSize=medium&key=" + API_KEY;
            searchClient.get(url, handler);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.search_not_found, Toast.LENGTH_SHORT).show();
        }
    }


}
