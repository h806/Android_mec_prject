package com.h.mechanicalengineering.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.h.mechanicalengineering.utilse.AppController;
import com.h.mechanicalengineering.utilse.Utils;
import org.json.JSONObject;

public class NewsShowActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    ImageView imgView;
    TextView title , subTitle ;
     JustifiedTextView description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_show);

        findViews();
        toolbar = (Toolbar) findViewById(R.id.course_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
       String s =  intent.getStringExtra("subtitle");
       Toast.makeText(this,s , Toast.LENGTH_SHORT).show();
        subTitle.setText(intent.getStringExtra("subtitle"));
        description.setText(intent.getStringExtra("desc"));
        String video = intent.getStringExtra("video");

        sendStringRequest2(video);

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


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "cant connect", Toast.LENGTH_LONG).show();

            }
        });
        AppController.getInstance().addToRequestQueue(MyStringRequest);
    }

    //------------------------------------------------------------------------------------------------
    public  void  findViews(){

        imgView = findViewById(R.id.news_img);
        title = findViewById(R.id.news_title);
        webView = findViewById(R.id.news_webview);
        subTitle = findViewById(R.id.news_subtitle);
        description = findViewById(R.id.news_description);
        }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

