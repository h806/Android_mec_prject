package com.h.mechanicalengineering.lessons;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.lessons.Cachedb;
import com.h.mechanicalengineering.utilse.AppController;
import com.h.mechanicalengineering.utilse.CacheModel;
import com.h.mechanicalengineering.utilse.Utils;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;



public class ShowActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    ImageView imgView;
    TextView textView;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

  //------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        findVeiws();

        toolbar = findViewById(R.id.course_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String nameID = intent.getStringExtra("nameID");

        sendStringRequest(nameID);

    }
   //-----------------------------------------------------------------------------------------------
    private void sendStringRequest(final String post) {
        final String url = "http://r6shop.net/android_test/newmech.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getApplicationContext(),response , Toast.LENGTH_LONG).show();

                    JSONArray JsonArray = new JSONArray(response);
                    for (int i = 0; i < 1; i++) {
                        JSONObject obj = JsonArray.getJSONObject(i);
                        String img = obj.getString("imageurl");
                        String nameText = obj.getString("idname");
                        Picasso.get().load(img).into(imgView);
                        textView.setText(nameText);
                        String video = obj.getString("videourl");
                        sendStringRequest2(video);

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

    private void sendStringRequest2(String video) {

        StringRequest MyStringRequest = new StringRequest(Request.Method.GET, video, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try
                    {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonVideo = jsonObject.getJSONObject("video");
                        Utils.play(webView,jsonVideo.getString("frame"));
                    }
                    catch (Exception e)
                    {
                       // Toast.makeText(getApplicationContext(),"no video data" , Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }


        }


                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.

        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "cant connect", Toast.LENGTH_LONG).show();

            }
        });
        AppController.getInstance().addToRequestQueue(MyStringRequest);
    }

  //------------------------------------------------------------------------------------------------
    public void findVeiws(){

        imgView = findViewById(R.id.imgshowinfo);
        textView = findViewById(R.id.txt2);
        webView = findViewById(R.id.webview);

    }
}
