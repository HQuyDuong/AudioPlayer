package com.example.audioplayer.db;

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

    String TBL_SONGS = "tbl_songs";

    boolean update(T obj);
    long insert(T obj);
    boolean delete(String id);
    T get(String id);
    List<T> findAll();
}
