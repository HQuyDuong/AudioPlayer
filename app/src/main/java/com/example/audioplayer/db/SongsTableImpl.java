package com.example.audioplayer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SongsTableImpl extends ContextTable<Songs> implements SongsTable{
    public SongsTableImpl(Context context) {
        super(context);
    }

    @Override
    protected String getPrimaryColumn() {
        return SongsTable.COLUMN_ID;
    }

    @Override
    protected ContentValues convert(Songs obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SongsTable.COLUMN_NAME, obj.name);
        contentValues.put(SongsTable.COLUMN_PATH, obj.path);
        return contentValues;
    }

    @Override
    protected Songs extract(Cursor cursor) {
        int pathColumnIndex = cursor.getColumnIndex(SongsTable.COLUMN_PATH);
        int nameColumnIndex = cursor.getColumnIndex(SongsTable.COLUMN_NAME);
        int idColumnIndex = cursor.getColumnIndex(SongsTable.COLUMN_ID);
        Songs songs = new Songs();
        songs.path = cursor.getString(pathColumnIndex);
        songs.id = cursor.getInt(idColumnIndex);
        songs.name = cursor.getString(nameColumnIndex);
        return songs;
    }

    @Override
    protected String getName() {
        return SongsTable.TABLE_NAME;
    }

    @Override
    protected String getKey(Songs object) {
        return String.valueOf(object.id);
    }
}
