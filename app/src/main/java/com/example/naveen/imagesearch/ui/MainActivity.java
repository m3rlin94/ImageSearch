package com.example.naveen.imagesearch.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naveen.imagesearch.R;
import com.example.naveen.imagesearch.adapter.ConvertJsonToList;
import com.example.naveen.imagesearch.adapter.RvAdapter;
import com.example.naveen.imagesearch.adapter.SearchImages;
import com.example.naveen.imagesearch.model.ImageObject;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView getStringView;
    private Button searchButton;
    private RecyclerView displayRv;
    private RvAdapter adapter;
    private List<ImageObject> imgResult;
    private SearchImages search;
    private String enteredString;
    private int startPage = 1;
    private TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    private void initialize() {
        getStringView = (AutoCompleteTextView) findViewById(R.id.getString);
        searchButton = (Button) findViewById(R.id.searchBtn);
        display = (TextView) findViewById(R.id.textView);
        displayRv = (RecyclerView) findViewById(R.id.displayRecyclerView);
        imgResult = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        displayRv.setLayoutManager(layoutManager);
        displayRv.setItemAnimator(new DefaultItemAnimator());

        adapter = new RvAdapter(displayRv, imgResult, this);
        displayRv.setAdapter(adapter);
    }

    public void searchBtnClick(View view) {
        enteredString = getStringView.getText().toString().trim();
        String displayMsg = "Search results for ";
        display.setText(String.format("%s%s", displayMsg, enteredString));
        imageSearch(1);
    }

    private void imageSearch(int page) {

        if (checkConnection()) {
            search = new SearchImages();
            startPage = page;
            if (startPage == 1) {
                imgResult.clear();
            }

            if (!enteredString.equals("")) {
                search.runQuery(enteredString, startPage, this, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //super.onSuccess(statusCode, headers, response);
                        try {
                            JSONArray results;
                            if (response != null) {
                                results = response.getJSONArray("items");
                                Log.d("test", results.toString());
                                imgResult.addAll(ConvertJsonToList.fromJsonArray(results));
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), R.string.invalid_data, Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(getApplicationContext(), R.string.service_unavailable, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, R.string.invalid_string, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
