package com.example.audioplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.audioplayer.db.entities.Playlist;
import com.example.audioplayer.db.entities.Song;
import com.example.audioplayer.db.tables.PlaylistTable;
import com.example.audioplayer.db.tables.SongTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "audio_player.db";
    static final int DATABASE_VERSION = 1;
    private static DatabaseHelper ourInstance = null;
    private SQLiteDatabase database;

    public static DatabaseHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DatabaseHelper(context);
        }
        return ourInstance;
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

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] columns = new String[]{SongTable.COLUMN_ID_SONG, SongTable.COLUMN_NAME_SONG, SongTable.COLUMN_PATH_SONG};
        String[] types = new String[]{SongTable.COLUMN_ID_SONG_TYPE + " primary key", SongTable.COLUMN_NAME_SONG_TYPE, SongTable.COLUMN_PATH_SONG_TYPE};
        createTable(db, SongTable.TABLE_NAME, columns, types);
        String[] columns2 = new String[]{PlaylistTable.COLUMN_ID_PLAYLIST, PlaylistTable.COLUMN_NAME_PLAYLIST};
        String[] types2 = new String[]{PlaylistTable.COLUMN_ID_PLAYLIST_TYPE + " primary key", PlaylistTable.COLUMN_NAME_PLAYLIST_TYPE};
        createTable(db, PlaylistTable.TABLE_NAME, columns2, types2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " +
                oldVersion + " to " +
                newVersion + ", which will destroy all old data");

        String[] tableSong = {SongTable.TABLE_NAME};
        String[] tablePlaylist = {PlaylistTable.TABLE_NAME};
        clear(db, tableSong);
        clear(db, tablePlaylist);

        onCreate(db);
    }


    public SQLiteDatabase getDatabase() {
        if (database == null || !database.isOpen()) {
            database = getWritableDatabase();
        }
        return database;
    }

    public void closeDB() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
