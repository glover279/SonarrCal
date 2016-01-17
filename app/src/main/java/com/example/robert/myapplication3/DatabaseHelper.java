package com.example.robert.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Robert on 2016/01/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="cal.db";
    public static final String TABLE_NAME1="connect_table";
    public static final String COL_url="url";
    public static final String COL_api="api";


    public static final String TABLE_NAME="episodes_table";
    public static final String COL_1="airDateUtc";
    public static final String COL_2="series_name";
    public static final String COL_3="episode_title";
    public static final String COL_4="episode_number";
    public static final String COL_5="season_number";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String2="CREATE TABLE " + TABLE_NAME1 + "(" +
                COL_api + " TEXT PRIMARY KEY," +
                COL_url + " TEXT"+")";
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " TEXT PRIMARY KEY," +
                COL_2 + " TEXT," +
                COL_3 + " TEXT," +
                COL_4 + " INTEGER," +
                COL_5 + " INTEGER" +")";
       // db.execSQL(SQL_String2);
        db.execSQL(SQL_String);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
public void clearDB(String TABLE_NAME12){
    String clearDBstr="DELETE FROM"+TABLE_NAME12+" where 1=1";
    SQLiteDatabase db=this.getWritableDatabase();
    db.execSQL(clearDBstr);


}
    public boolean insertDataConn(String url, String api){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_url,url);
        contentValues.put(COL_api,api);
        long result= db.insert(TABLE_NAME1,null,contentValues);
        if(result==-1){return false;}
        else{return true;}
    }
}
