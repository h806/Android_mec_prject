package com.h.mechanicalengineering.lessons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.adapters.SearchAdapter;
import com.h.mechanicalengineering.adapters.LessonsAdapter;
import com.h.mechanicalengineering.utilse.DictionaryModel;
import com.h.mechanicalengineering.utilse.LessonsModel;

import java.util.ArrayList;
import java.util.List;


public class LessonsActivity extends AppCompatActivity {

    Toolbar toolbar;
    private LessonsAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<GroupSaver> groupSavers;
    SearchAdapter searchAdapter;
    DbHelper dbHelper;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        toolbar = (Toolbar) findViewById(R.id.course_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DbHelper(this);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_id);


        groupSavers = new ArrayList<>();

        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LessonsAdapter(this, groupSavers);
        recyclerView.setAdapter(adapter);


        final SearchView searchView = findViewById(R.id.id_search_view2);

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


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);

    }


    private void setData() {

        //get child from dbHelper and add it with title in collapse recyclerView

        for (int i = 0; i <= 2; i++) {
            String[] diction = {"انتقال حرارت", "توانایی ماشینکاری", "استاتیک"};
            String[] terms = dbHelper.dicword(diction[i]);


            ArrayList<LessonsModel> iphones = new ArrayList<>();

            for (int in = 0; in < terms.length; in++) {
                iphones.add(new LessonsModel(terms[in]));
            }

            groupSavers.add(new GroupSaver(diction[i], iphones));

        }

    }

    public void searches(String wordsearch) {

        //get data from dbHelper and search it

        searchAdapter = new SearchAdapter();

        recyclerView.setAdapter(searchAdapter);
        ArrayList<DictionaryModel> temp = new ArrayList<>();

        List<DictionaryModel> data = dbHelper.searchdic();
        for (DictionaryModel dictionaryModel : data) {
            if (dictionaryModel.getDefinition().contains(wordsearch)) {

                temp.add(dictionaryModel);

            }
        }
        data = temp;
        searchAdapter.setData(data);

    }

}