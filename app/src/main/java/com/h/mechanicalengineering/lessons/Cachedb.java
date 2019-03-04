package com.h.mechanicalengineering.lessons;

import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

import com.h.mechanicalengineering.utilse.CacheModel;

import java.util.ArrayList;

public class Cachedb extends SQLiteOpenHelper
{
    //Database Name
    private static final String DATABASE_NAME = "Cache_db";
    //Table Name
    private static final String TABLE_NAME = "Cache_tbl";
    //tblNotes's Fields
    private static final String COL_NAME = "name";
    private static final String COL_IMAGEURL = "imageurl";
    private static final int VERSION = 1;

    public Cachedb(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                " " + COL_NAME + " TEXT NOT NULL UNIQUE ," +
                " " + COL_IMAGEURL + " TEXT NOT NULL " +
                ");";

        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void inserturl(CacheModel cache)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, cache.getName());
        contentValues.put(COL_IMAGEURL, cache.getImageurl());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public String getimageurl(String imgname)
    {
        String imgurl="";

        String selectQuery = "SELECT "+COL_IMAGEURL+" FROM "+TABLE_NAME+" WHERE "+COL_NAME+" = '"+imgname+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                  imgurl = cursor.getString(0);
//                Note note = new Note();
//                note.setId(Integer.parseInt(cursor.getString(0)));
//                note.setTitle(cursor.getString(1));
//                note.setText(cursor.getString(2));
//
//
//                NoteList.add(note);
            } while (cursor.moveToNext());


        }
        return imgurl;
    }


//    public int updateNote(int id, String title, String text)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_NAME, title);
//        values.put(COL_TEXT, text);
//
//        return db.update(TABLE_NAME, values, COL_ID + " = ?", new String[]{id + ""});
//    }
//
//    public void deleteNote(int id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{id + ""});
//        db.close();
//    }

}