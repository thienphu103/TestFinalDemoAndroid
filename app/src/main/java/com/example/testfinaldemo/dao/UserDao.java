package com.example.testfinaldemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testfinaldemo.database.DatabaseSQliteHelper;
import com.example.testfinaldemo.model.User;

import java.util.ArrayList;

public class UserDao {
    DatabaseSQliteHelper databaseSQliteHelper;
    ArrayList <User> userArrayList;
    public UserDao(Context context) {
        this.databaseSQliteHelper = new DatabaseSQliteHelper(context);
    }
    public ArrayList<User> getAllDataUser(){
         userArrayList=new ArrayList<>();
         SQLiteDatabase sqLiteDatabase = databaseSQliteHelper.getReadableDatabase();
         Cursor cursor=sqLiteDatabase.rawQuery("select * from USER",null);
         cursor.moveToFirst();
         while (!cursor.isAfterLast()){
             userArrayList.add(new User(cursor.getInt(0)+"",
                     cursor.getString(1),
                     cursor.getString(2),
                     cursor.getString(3),
                     cursor.getString(4)));
             cursor.moveToNext();
             Log.d("database",userArrayList.toString());
         }

        return userArrayList;
    }
    public void addDataUser(User user){
        SQLiteDatabase sqLiteDatabase = databaseSQliteHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("age",user.getAge());
        contentValues.put("address",user.getAddress());
        contentValues.put("image",user.getImage());
        long result= sqLiteDatabase.insert("USER",null,contentValues);

    }
    public void updateDataUser(User user){
        SQLiteDatabase sqLiteDatabase = databaseSQliteHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("age",user.getAge());
        contentValues.put("address",user.getAddress());
        contentValues.put("image",user.getImage());
        long result= sqLiteDatabase.update("USER",contentValues,"id = "+user.getId(),null);

    }
    public void deleteDataUser(String id){
        SQLiteDatabase sqLiteDatabase = databaseSQliteHelper.getWritableDatabase();
        long result= sqLiteDatabase.delete("USER","id = "+id,null);
    }
}
