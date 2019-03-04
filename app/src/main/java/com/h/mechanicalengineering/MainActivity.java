package com.h.mechanicalengineering;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.h.mechanicalengineering.ads.Employ_Activity;
import com.h.mechanicalengineering.dictionary.dictionaryActivity;
import com.h.mechanicalengineering.lessons.LessonsActivity;
import com.h.mechanicalengineering.news.newsActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ImageView imgNews, imgcourse, imgdictionari, imgEmploye;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        findNews();
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }




    public void click(View v){
        int id =v.getId();
        Intent i = null;

        switch (id) {

            case R.id.course:
            i=new Intent(MainActivity.this,LessonsActivity.class);
            break;
            case R.id.dictionary:
                i=new Intent(MainActivity.this,dictionaryActivity.class);
                break;
            case R.id.news:
                i=new Intent(MainActivity.this,newsActivity.class);
                break;
            case R.id.employ:
                i=new Intent(MainActivity.this,Employ_Activity.class);
                break;
        }

        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.settings:
                Toast.makeText(this,"www",Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                shareApplication();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void shareApplication() {
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("*/*");

        File originalApk = new File(filePath);

        try {
            File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return ;
            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            startActivity(Intent.createChooser(intent, "اشتراک گذاری با"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void findNews(){
        imgcourse      =  findViewById(R.id.course);
        imgdictionari  = findViewById(R.id.dictionary);
        imgEmploye     = findViewById(R.id.employ);
        imgNews        = findViewById(R.id.news);
        drawerLayout   =   findViewById(R.id.drawer_layout);
        navigationView =   findViewById(R.id.navigation_view);


    }
}

