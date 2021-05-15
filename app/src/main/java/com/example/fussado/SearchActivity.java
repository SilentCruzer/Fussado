package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fussado.Adapter.SearchAdapter;
import com.example.fussado.Adapter.genreAdapter;
import com.example.fussado.Model.Movie;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private String BASE_URL;
    public String INTENT_KEY;

    EditText search_edit_text;
    ImageButton search_button;
    ProgressBar loading_indicator;
    TextView error_message;

    RecyclerView mRecyclerView;

    private ArrayList<Movie> searchMovies;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        searchMovies = new ArrayList<Movie>();
        mRequestQueue = Volley.newRequestQueue(this);


        setBaseUrl();
        search_edit_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    search();
                    return true;
                }
                return false;
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovies.clear();
                search();
            }
        });
    }

    private void parseMovieJson(String key){
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String name = "", id = "", image = "", rating = "", year = "", overview = "", type ="";

                try {
                    JSONArray items = response.getJSONArray("results");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject jsonObject1 = items.getJSONObject(i);
                        type ="Movie";
                        id = jsonObject1.getString("id");
                        name = jsonObject1.getString("title");
                        image = "https://image.tmdb.org/t/p/w500" + jsonObject1.getString("poster_path");
                        rating = jsonObject1.getString("vote_average");
                        year = jsonObject1.getString("release_date");
                        overview = jsonObject1.getString("overview");

                        if(jsonObject1.getString("poster_path").equals("null")){
                            image = "https://i.imgur.com/S8ZaF5u.png";
                        }

                        searchMovies.add( new Movie(id,name,image, rating, year, overview, type));

                    }

                    PutDataIntoRecyclerView(searchMovies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
    });
        mRequestQueue.add(request);

    }

    private void parseTvJson(String key){
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String name = "", id = "", image = "", rating = "", year = "", overview = "",type ="";

                try {
                    JSONArray items = response.getJSONArray("results");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject jsonObject1 = items.getJSONObject(i);
                        type ="TV_Show";
                        id = jsonObject1.getString("id");
                        name = jsonObject1.getString("name");
                        image = "https://image.tmdb.org/t/p/w500" + jsonObject1.getString("poster_path");
                        rating = jsonObject1.getString("vote_average");
                        year = "Language : "+jsonObject1.getString("original_language");
                        overview = jsonObject1.getString("overview");

                        if(jsonObject1.getString("poster_path").equals("null")){
                            image = "https://i.imgur.com/S8ZaF5u.png";
                        }

                        searchMovies.add( new Movie(id,name,image, rating, year, overview, type));
                    }
                    PutDataIntoRecyclerView(searchMovies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }

    private void parseGameJson(String key){
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String name = "", id = "", image = "", rating = "", year = "", overview = "",type ="";

                try {
                    JSONArray items = response.getJSONArray("results");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject jsonObject1 = items.getJSONObject(i);
                        type ="Game";
                        id = jsonObject1.getString("slug");
                        name = jsonObject1.getString("name");
                        image = jsonObject1.getString("background_image");
                        rating = jsonObject1.getString("rating");
                        year = "Released : "+jsonObject1.getString("released");
                        overview = jsonObject1.getString("playtime");

                        if(jsonObject1.getString("background_image").equals("null")){
                            image = "https://i.imgur.com/S8ZaF5u.png";
                        }

                        searchMovies.add( new Movie(id,name,image, rating, year, overview, type));
                    }
                    PutDataIntoRecyclerView(searchMovies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }

    private void parseBookJson(String key){
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String name = "", id = "", image = "", rating = "", year = "", overview = "", type ="";

                try {
                    JSONArray items = response.getJSONArray("items");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject jsonObject1 = items.getJSONObject(i);
                        JSONObject volumeInfo = jsonObject1.getJSONObject("volumeInfo");
                        try {

                            type ="Book";
                            name = volumeInfo.getString("title");
                            image = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
                            StringBuffer str = new StringBuffer(image);
                            str.insert(4, "s");
                            image = str.toString();
                            rating = volumeInfo.getString("publishedDate");
                            JSONArray authors = volumeInfo.getJSONArray("authors");

                            if (authors.length() == 1) {
                                year = authors.getString(0);
                            } else {
                                year = authors.getString(0) + "|" + authors.getString(1);
                            }
                            //overview = volumeInfo.getString("description");

                            if (volumeInfo.getJSONObject("imageLinks").getString("thumbnail").equals("null")) {
                                image = "https://i.imgur.com/S8ZaF5u.png";
                            }

                        }catch (Exception e){

                        }
                        Log.e("asdf2", "onResponse: " + name+" "+image+" "+rating+year);

                        searchMovies.add( new Movie(id,name,image, rating, year, overview, type));
                    }
                    PutDataIntoRecyclerView(searchMovies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }

    private boolean Read_network_state(Context context)
    {    boolean is_connected;
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected=info!=null&&info.isConnectedOrConnecting();
        return is_connected;
    }

    private void search(){
        String search_query = search_edit_text.getText().toString();
        boolean is_connected = Read_network_state(this);

        if(!is_connected) {
            error_message.setText("Failed to load data");
            mRecyclerView.setVisibility(View.INVISIBLE);
            error_message.setVisibility(View.VISIBLE);
        }

        if(search_query.equals("")) {
            Toast.makeText(this,"Please enter your query",Toast.LENGTH_SHORT).show();
        }
        String final_query=search_query.replace(" ","+");
        Uri uri = Uri.parse(BASE_URL+final_query);
        Uri.Builder builder = uri.buildUpon();

        if(INTENT_KEY.equals("movie")){
            parseMovieJson(builder.toString());
        }if(INTENT_KEY.equals("tv")){
            parseTvJson(builder.toString());
        }if(INTENT_KEY.equals("book")){
            parseBookJson(builder.toString());
        }if(INTENT_KEY.equals("game")){
            parseGameJson(builder.toString());
        }


    }
    private void PutDataIntoRecyclerView(List<Movie> searchList){
        SearchAdapter searchAdapter = new SearchAdapter(this, searchList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(searchAdapter);
    }

    private void setBaseUrl(){
        Bundle extras = getIntent().getExtras();
        INTENT_KEY = extras.getString("Key");
        if(INTENT_KEY.equals("movie")){
            BASE_URL = "";
        }
        if(INTENT_KEY.equals("tv")){
            BASE_URL = "";
        }
        if(INTENT_KEY.equals("book")){
            BASE_URL = "";
        }
        if(INTENT_KEY.equals("game")){
            BASE_URL = "";
        }
    }

    private void initViews(){
        search_edit_text = findViewById(R.id.search_box);
        search_button = findViewById(R.id.search_button);
        loading_indicator = findViewById(R.id.loading_indicator);
        error_message = findViewById(R.id.message_display);

    }
}