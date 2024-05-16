package com.example.ituneapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PlaylistController {
    private DataBaseHelper dataBaseHelper;
    private static final String TABLE_NAME = "Playlists";
    private static final String COLUMN_VIDEO_URL = "videoUrl";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "userId"; // Add userId column

    public PlaylistController(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public long newListItem(ListItem listItem) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_URL, listItem.getUrl());
        values.put(COLUMN_USER_ID, listItem.getIdUser());
        return db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<ListItem> getPlayList(String userId) {
        ArrayList<ListItem> listItems = new ArrayList<>();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String[] columns = {COLUMN_VIDEO_URL, COLUMN_ID, COLUMN_USER_ID};
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {userId};
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VIDEO_URL));
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String userIdFromDB = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_ID));
                ListItem listItem = new ListItem(url, id, userIdFromDB);
                listItems.add(listItem);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return listItems;
    }
}
