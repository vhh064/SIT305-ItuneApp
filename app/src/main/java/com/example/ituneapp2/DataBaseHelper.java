package com.example.ituneapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "iTubeDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_PLAYLIST = "Playlists";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VIDEO_URL = "videoUrl";
    private static final String COLUMN_USER_ID = "userId"; // Add userId column

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " + TABLE_PLAYLIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_ID + " TEXT," // Add userId column
                + COLUMN_VIDEO_URL + " TEXT" + ")";
        db.execSQL(CREATE_PLAYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST);
        onCreate(db);
    }

    public void addVideoToPlaylist(String videoUrl, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_URL, videoUrl);
        values.put(COLUMN_USER_ID, userId);
        db.insert(TABLE_PLAYLIST, null, values);
        db.close();
    }
}
