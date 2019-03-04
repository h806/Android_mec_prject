package com.h.mechanicalengineering.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.lessons.Cachedb;
import com.h.mechanicalengineering.utilse.AppController;
import com.h.mechanicalengineering.utilse.CacheModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Show_Dictionary_Activity extends AppCompatActivity {

    ImageView imgView;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__dictionary_);

        toolbar = findViewById(R.id.d_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgView = findViewById(R.id.img_dictinary);
        Intent intent = getIntent();
        String img = intent.getStringExtra("nameID");
        setTitle(img);
        sendStringRequest(img);
    }

    //-----------------------------------------------------------------------------------------------
    private void sendStringRequest(final String post) {
        final String url = "http://r6shop.net/android_test/newmech.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getApplicationContext(),"response" , Toast.LENGTH_LONG).show();

                    JSONArray JsonArray = new JSONArray(response);
                    for (int i = 0; i < 1; i++) {
                        JSONObject obj = JsonArray.getJSONObject(i);
                        String img = obj.getString("imageurl");
                        String nameText = obj.getString("idname");
                        Picasso.get().load(img).into(imgView);

                        Cachedb cachedb = new Cachedb(getApplicationContext());
                        cachedb.inserturl(new CacheModel(nameText,img));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "JSON catch", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Cachedb cachedb = new Cachedb(getApplicationContext());
                String imgurl = cachedb.getimageurl(post);
                if (!imgurl.equals("")) {
                    Toast.makeText(getApplicationContext(), " image in cache", Toast.LENGTH_LONG).show();
                    Picasso.get().load(imgurl).into(imgView);
                }
                else
                    Toast.makeText(getApplicationContext(), "no image in cache", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("idname", post); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        AppController.getInstance().addToRequestQueue(MyStringRequest);
    }

    //-----------------------------------------------------------------------------------------------

}
