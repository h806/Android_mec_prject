package com.h.mechanicalengineering.ads;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.adapters.AdsAdapter;
import com.h.mechanicalengineering.utilse.AdsModel;
import com.h.mechanicalengineering.utilse.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;



public class Employ_Activity extends AppCompatActivity {

    ArrayList<String> city = new ArrayList<>();
    SpinnerDialog citySpinner;
    android.support.v7.widget.SearchView searchView;
    ImageView imgCity;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ArrayList<AdsModel> adsList = new ArrayList<>();
    private AdsAdapter adsAdapter;


    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_);

        Toolbar toolbar = findViewById(R.id.employ_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        findViews();
        load_ads("");
        load_cats();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled (RecyclerView recyclerView,int dx, int dy)
            {
                if (dy > 0 || dy < 0 && fab.isShown()) {
                    fab.hide();
                }

            }


            @Override
            public void onScrollStateChanged (RecyclerView recyclerView,int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });




        citySpinner = new SpinnerDialog(Employ_Activity.this, city, "شهر خود را انتخاب کنید", R.style.DialogAnimations_SmileWindow, "بستن");
        citySpinner.bindOnSpinerListener(new OnSpinerItemClick() {

            @Override
            public void onClick(String items, int position) {
                adsList.clear();
                load_ads(items);
                Toast.makeText(Employ_Activity.this, items, Toast.LENGTH_SHORT).show();
            }
        });




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searches(newText);

                return false;
            }
        });


    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.locationIcon:
                city.clear();
                load_cats();
                citySpinner.showSpinerDialog();
                break;
        }

    }


    private void load_cats() {
        final String url = "http://r6shop.net/android_test/ads/get_cat.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jObj = new JSONObject(response);
                    JSONArray JsonArray = jObj.getJSONArray("cat");

                    for (int i = 0; i < JsonArray.length(); i++) {
                        JSONObject obj = JsonArray.getJSONObject(i);
                        String nameText = obj.getString("name");

                        city.add(nameText);
                    }


                } catch (Exception e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "JSONcatch", Toast.LENGTH_LONG).show();
                }

                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() {
            //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "erorr in volly", Toast.LENGTH_SHORT).show();
                Log.d(String.valueOf(error), error.toString());

            }
        });
        AppController.getInstance().addToRequestQueue(MyStringRequest);
    }


    private void load_ads( final String cat) {

        final String URL_PRODUCTS = "http://r6shop.net/android_test/ads/get_data.php";
        AppController.getInstance().getRequestQueue().getCache().clear();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                          //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            //converting the string to json array object
                            JSONObject jObj = new JSONObject(response);
                            JSONArray array = jObj.getJSONArray("ads");

                            for (int i = 0; i < array.length(); i++) {
                                //getting ads object from json array
                                JSONObject ads = array.getJSONObject(i);
                                int date_year = ads.getInt("year");
                                int date_month = ads.getInt("month");
                                int  date_day = ads.getInt("day");

                                JalaliCalendar.gDate miladi = new JalaliCalendar.gDate(date_year,date_month,date_day);
                                JalaliCalendar.gDate jalalidate = JalaliCalendar.MiladiToJalali(miladi);


                                //adding the ads to ads list
                                adsList.add(new AdsModel(
                                        ads.getInt("id"),
                                        ads.getString("title"),
                                        ads.getString("subtitle"),
                                        ads.getString("img"),
                                        ads.getString("description"),
                                        ads.getString("degree"),
                                        jalalidate.toString(),
                                        ads.getString("phone"),
                                        ads.getString("email"),
                                        ads.getString("city")

                                        //ads.getBoolean("verify")


                                ));
                            }
                            //creating adapter object and setting it to recyclerview

                  //----------------------------------------------------------------------------------------------------


                            adsAdapter = new AdsAdapter(recyclerView, adsList,Employ_Activity.this);
                            recyclerView.setAdapter(adsAdapter);


//                            adsAdapter.setOnLoadMoreListener(new AdsAdapter.OnLoadMoreListener() {
//                                @Override
//                                public void onLoadMore() {
//                                    if (adsList.size() <= 20) {
//                                        adsList.add(null);
//                                        adsAdapter.notifyItemInserted(adsList.size() - 1);
//                                        new Handler().postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                adsList.remove(adsList.size() - 1);
//                                                adsAdapter.notifyItemRemoved(adsList.size());
//                                                int index = adsList.size();
//                                                int end = index + 1;
//
//                                                adsAdapter.notifyDataSetChanged();
//                                                adsAdapter.setLoaded();
//                                            }
//                                        }, 2000);
//                                    } else {
//                                        Toast.makeText(Employ_Activity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });

                            AppController.getInstance().getRequestQueue().getCache().clear();

                            //--------------------------------------------------------------------------------------------------------


                        } catch (JSONException e) {

                            Toast.makeText(getApplicationContext(), "error catch", Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "error response", Toast.LENGTH_LONG).show();

                    }
                })

        {
            protected Map<String, String> getParams() {
            Map<String, String> MyData = new HashMap<String, String>();
            MyData.put("cat" , cat);//Add the data you'd like to send to the server.
            return MyData;
        }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }


    public void searches(String wordsearch) {



        ArrayList<AdsModel> temp = new ArrayList<>();
        for (AdsModel adsModel : adsList) {
            if (adsModel.getTitle().contains(wordsearch) || adsModel.getSubtitle().contains(wordsearch)) {

                temp.add(adsModel);

            }
        }
     //   adapter.setData(temp);
        AdsAdapter adapter = new AdsAdapter(recyclerView,temp,Employ_Activity.this );
        recyclerView.setAdapter(adapter);
    }


    public void findViews(){


        imgCity = findViewById(R.id.locationIcon);
        searchView = findViewById(R.id.search_t);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fab);


    }
}
