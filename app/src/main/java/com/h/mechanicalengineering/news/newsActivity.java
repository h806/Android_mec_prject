package com.h.mechanicalengineering.news;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.adapters.AdsAdapter;
import com.h.mechanicalengineering.adapters.NewsAdapter;
import com.h.mechanicalengineering.ads.Employ_Activity;
import com.h.mechanicalengineering.utilse.AppController;
import com.h.mechanicalengineering.utilse.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class newsActivity extends AppCompatActivity {

    List<NewsModel> productList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recycler_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        loadProducts();

    }

    private void loadProducts() {

         final String URL_PRODUCTS = "http://r6shop.net/android_test/news.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new NewsModel(
                                        product.getInt("id"),
                                        product.getString("title"),
                                        product.getString("subtitle"),
                                        product.getString("image_news"),
                                        product.getString("description"),
                                        product.getString("video")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            final NewsAdapter adapter = new NewsAdapter(recyclerView,productList,newsActivity.this);
                            recyclerView.setAdapter(adapter);


                            adapter.setOnLoadMoreListener(new NewsAdapter.OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    if (productList.size() <= 20) {
                                        productList.add(null);
                                        adapter.notifyItemInserted(productList.size() - 1);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                productList.remove(productList.size() - 1);
                                                adapter.notifyItemRemoved(productList.size());
                                                int index = productList.size();
                                                int end = index + 1;

                                                adapter.notifyDataSetChanged();
                                                adapter.setLoaded();
                                            }
                                        }, 2000);
                                    } else {
                                        Toast.makeText(newsActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            AppController.getInstance().getRequestQueue().getCache().clear();


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
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}