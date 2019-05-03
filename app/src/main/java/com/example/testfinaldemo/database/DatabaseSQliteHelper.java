package com.example.testfinaldemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQliteHelper  extends SQLiteOpenHelper {

    public DatabaseSQliteHelper(Context context) {

        super(context, "database12", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists USER (" +
                "id integer primary key autoincrement," +
                "username text," +
                "age text," +
                "address text," +
                "image text)");
        ContentValues contentValues1=new ContentValues();
        contentValues1.put("username","Android");
        contentValues1.put("age","20");
        contentValues1.put("address","Google");
        contentValues1.put("image","android");
        long a= db.insert("USER",null,contentValues1);

        ContentValues contentValues2=new ContentValues();
        contentValues2.put("username","Android 8.0");
        contentValues2.put("age","20");
        contentValues2.put("address","Google");
        contentValues2.put("image","android");
        db.insert("USER",null,contentValues2);

        ContentValues contentValues3=new ContentValues();
        contentValues3.put("username","James");
        contentValues3.put("age","20");
        contentValues3.put("address","Google");
        contentValues3.put("image","people");
        db.insert("USER",null,contentValues3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
