package com.h.mechanicalengineering.dictionary;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.adapters.DictionaryAdapter;
import com.h.mechanicalengineering.utilse.DictionaryModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class dictionaryActivity extends AppCompatActivity {
    Toolbar toolbar;
    private final String fileDictionary = "space.txt";
    private List<DictionaryModel> data;
    private DictionaryAdapter dictionaryAdapter;
    RecyclerView rvword;
    SearchView searchView;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        findViews();

        toolbar = findViewById(R.id.dictionary_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        rvword.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        data=new ArrayList<>();
        readFromAsset(getApplicationContext(), fileDictionary);
        dictionaryAdapter =new DictionaryAdapter();
        dictionaryAdapter.setData(data);
        rvword.setAdapter(dictionaryAdapter);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchWord(newText);

                return false;
            }
        });
    }
    private void readFromAsset(Context context,String fileName){

        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));

            String mLine;
            int i = 0;
            while ((mLine=reader.readLine())!=null){
                String[] lineData=mLine.split("-");
                data.add(new DictionaryModel(lineData[1]));
                i++;
            }
            reader.close();


        }catch (IOException e){
            e.printStackTrace();
        }}
    private void searchWord(String wordSearch){


        data.clear();
        readFromAsset(getApplicationContext(), fileDictionary);
        List<DictionaryModel> temp = new ArrayList<>();
        for (DictionaryModel dictionaryModel : data){
            if (dictionaryModel.getDefinition().contains(wordSearch)){

                temp.add(dictionaryModel);

            }
        }
        data=temp;
        dictionaryAdapter.setData(data);

    }


    public void findViews(){
        rvword = findViewById(R.id.recycler_id);
        searchView = findViewById(R.id.id_search_view);
    }
}
