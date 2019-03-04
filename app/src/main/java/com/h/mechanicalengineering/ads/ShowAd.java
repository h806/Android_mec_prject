package com.h.mechanicalengineering.ads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.utilse.AdsModel;

import java.util.ArrayList;

public class ShowAd extends AppCompatActivity {


    TextView title, subtitle, city, phone, email, date , description , degree;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ad);

        findViews();


    Intent intent = getIntent();

    String mtitle = intent.getStringExtra("title");
    String msubtitle = intent.getStringExtra("subtitle");
    String mphone = intent.getStringExtra("phone");
    String memail = intent.getStringExtra("email");
    String mcity = intent.getStringExtra("city");
    String mdate = intent.getStringExtra("expire_date");
    Toast.makeText(getApplicationContext(),mdate,Toast.LENGTH_SHORT).show();
    String mdescribtion = intent.getStringExtra("describtion");
    String mdegree  = intent.getStringExtra("degree");

    title.setText(mtitle);
    subtitle.setText(msubtitle);
    phone.setText(mphone);
    email.setText(memail);
    city.setText(mcity);
    date.setText(mdate);
    description.setText(mdescribtion);
    degree.setText(mdegree);




}


    public void findViews() {

        title = findViewById(R.id.txt_title);
        subtitle = findViewById(R.id.txt_subtitle);
        phone = findViewById(R.id.txt_phone);
        email = findViewById(R.id.txt_email);
        city = findViewById(R.id.txt_city);
        date = findViewById(R.id.txt_date);
        description = findViewById(R.id.txt_description);
        degree = findViewById(R.id.txt_degree);
        img = findViewById(R.id.img_ads);
    }

}