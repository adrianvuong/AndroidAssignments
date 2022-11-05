package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Messages.db";
    private static final String TABLE_NAME= "myTable";
    private static final int VERSION_NUM=1;

    private static final String KEY_ID = "id";
    private static final String KEY_MESSAGE = "message";

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+ "("+KEY_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_MESSAGE+" TEXT NOT NULL);";

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



}
