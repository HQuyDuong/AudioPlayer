package com.example.audioplayer.db.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.audioplayer.db.entities.Song;

import java.util.List;

public class SongTableImpl extends ContextTable<Song> implements SongTable {
    public SongTableImpl(Context context) {
        super(context);
    }

    @Override
    protected ContentValues convert(Song obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SongTable.COLUMN_ID_SONG, obj.getIdSong());
        contentValues.put(SongTable.COLUMN_NAME_SONG, obj.getNameSong());
        contentValues.put(SongTable.COLUMN_PATH_SONG, obj.getPathSong());
        return contentValues;
    }

    @Override
    protected String getName() {
        return SongTable.TABLE_NAME;
    }

    @Override
    protected String getPrimaryColumn() {
        return SongTable.COLUMN_ID_SONG;
    }

    @Override
    protected int getKey(Song object) {
        return object.getIdSong();
    }

    @Override
    public Song searchSong(String nameSong) {
        if(nameSong == ""){
            return null;
        }
        String groupBy = null;
        String having = null;
        String[] columns = null;
        String order = null;
        String whereClause = String.format("%s=?", SongTable.COLUMN_NAME_SONG);
        String[] whereArgs = new String[]{
                nameSong
        };
        Cursor cursor = getDatabase().query(getName(), columns, whereClause, whereArgs, groupBy, having, order);
        Song obj = null;
        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    obj = extract(cursor);
                }
            } finally {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return obj;
    }

    @Override
    public List<Song> sapXep(List<Song> songList) {
        return null;
    }

    @Override
    public Song extract(Cursor cursor) {
        int idSongColumnIndex = cursor.getColumnIndex(SongTable.COLUMN_ID_SONG);
        int nameSongColumnIndex = cursor.getColumnIndex(SongTable.COLUMN_NAME_SONG);
        int pathSongColumnIndex = cursor.getColumnIndex(SongTable.COLUMN_PATH_SONG);
        Song song = new Song();

        song.setIdSong(cursor.getInt(idSongColumnIndex));
        song.setNameSong(cursor.getString(nameSongColumnIndex));
        song.setPathSong(cursor.getString(pathSongColumnIndex));
        return song;
    }
}
