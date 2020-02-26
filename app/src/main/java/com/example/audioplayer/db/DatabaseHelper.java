package com.example.audioplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "audio_player.db";
    static final int DATABASE_VERSION = 1;
    private static final Object LOCK = new Object();
    private static DatabaseHelper ourInstance = null;
    private SQLiteDatabase database;


    public static DatabaseHelper getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (LOCK) {
                if (ourInstance == null) {
                    ourInstance = new DatabaseHelper(context);
                }
            }
        }
        return ourInstance;
    }
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public SQLiteDatabase getDatabase() {
        if (database == null || !database.isOpen()) {
            synchronized (LOCK) {
                if (database == null || !database.isOpen()) {
                    database = getWritableDatabase();
                }
            }
        }
        return database;
    }
    private void createTable(SQLiteDatabase db, String tableName, String[] columns, String[] types) {
        String body = "";
        for (int i = 0; i < columns.length; ++i) {
            body += String.format("%s %s", columns[i], types[i] + ",");
    }
        if (!body.trim().isEmpty()) {
            body = body.substring(0, body.length() - 1);
        }
        String sql = String.format("create table %s(%s);", tableName, body);
        db.execSQL(sql);
    }

    private void clear(SQLiteDatabase db, String[] tables) {
        for (String table : tables) {
            db.execSQL("DROP TABLE IF EXISTS " + table);
        }
    }

    public void closeDB() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] columns = new String[]{SongsTable.COLUMN_ID, SongsTable.COLUMN_PATH , SongsTable.COLUMN_NAME};
        String[] types = new String[]{ SongsTable.COLUMN_ID_TYPE + " primary key", SongsTable.COLUMN_PATH_TYPE, SongsTable.COLUMN_NAME_TYPE};
        createTable(db, SongsTable.TABLE_NAME, columns, types);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " +
                oldVersion + " to " +
                newVersion + ", which will destroy all old data");

        String[] tables = {SongsTable.TABLE_NAME};
        clear(db, tables);

        onCreate(db);
    }
}
