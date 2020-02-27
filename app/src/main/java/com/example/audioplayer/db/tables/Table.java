package com.example.audioplayer.db.tables;

import android.database.Cursor;

import java.util.List;

public interface Table<T> {
    enum ColumnType {
        INTEGER,
        TEXT,
        FLOAT,
        LONG,
        REAL
    }

    String TBL_SONG = "tbl_song";
    String TBL_PLAYLIST = "tbl_playlist";

    long insert(T obj);

    T get(String id);

    List<T> findAll();

    T extract(Cursor cursor);

    boolean delete(long id);
    boolean delete(String id);

    boolean update(T obj);

    void clear();
}
