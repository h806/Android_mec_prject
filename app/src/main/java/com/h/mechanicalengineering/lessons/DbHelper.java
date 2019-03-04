package com.h.mechanicalengineering.lessons;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.h.mechanicalengineering.utilse.DictionaryModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper implements Serializable {

    private Context mcontext;
    public static final String DATABASE_NAME = "dbcourse2.db";
    public static final int DATABASE_VERSION = 1;
    private String DATABASE_LOCATION = "";
    private String DATABASE_FOLL_PATH = "";
    private String DIR_LOCATION = "";
    private final String col_child = "child";


    public SQLiteDatabase mdb;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mcontext = context;


        DATABASE_LOCATION = "data/data/" + mcontext.getPackageName() + "/databases/";
        DIR_LOCATION = "data/data/" + mcontext.getPackageName() + "/databases";
        DATABASE_FOLL_PATH = DATABASE_LOCATION + DATABASE_NAME;

        try {
            File file = new File(DIR_LOCATION);
            if (!file.isDirectory())
                file.mkdirs();
        }catch (Exception e){}

        if (!isExistingDb()) {
            try {
                extractAssetToDatabaseDictionary(DATABASE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        mdb = SQLiteDatabase.openOrCreateDatabase(DATABASE_FOLL_PATH, null);
    }

    boolean isExistingDb() {

        File file = new File(DATABASE_FOLL_PATH);
        return file.exists();
    }


    public void extractAssetToDatabaseDictionary(String fileName)
            throws IOException {

        int length;
        InputStream sourceDatabase = this.mcontext.getAssets().open(fileName);
        File destinationPath = new File(DATABASE_FOLL_PATH);
        OutputStream destination = new FileOutputStream(destinationPath);

        byte[] buffer = new byte[4096];
        while ((length = sourceDatabase.read(buffer)) > 0) {
            destination.write(buffer, 0, length);
        }
        sourceDatabase.close();
        destination.flush();
        destination.close();


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public String[] dicword(String title) {

        //        create list of database and return to main2Activity for set on collapse recycler

            String query = "select * from coursetable where parent = \"" + title + "\" ";

            Cursor cursor = mdb.rawQuery(query, null);

            ArrayList<String> wordterms = new ArrayList<>();

            if (cursor.moveToFirst())
                do {
                    String word = cursor.getString(cursor.getColumnIndexOrThrow(col_child));
                    wordterms.add(word);
                } while (cursor.moveToNext());

            cursor.close();

            String[] getword = new String[wordterms.size()];
            getword = wordterms.toArray(getword);

            return getword;
    }


    public ArrayList<DictionaryModel> searchdic(){

        //create list of database and return to main2Activity
        String query = "select * from coursetable";


        Cursor cursor = mdb.rawQuery(query, null);
        ArrayList<DictionaryModel> data = new ArrayList<>();
        if (cursor.moveToFirst())
            do {
                String word = cursor.getString(cursor.getColumnIndexOrThrow(col_child));
                data.add(new DictionaryModel(word));
            } while (cursor.moveToNext());

        cursor.close();



       return data;

    }
}


