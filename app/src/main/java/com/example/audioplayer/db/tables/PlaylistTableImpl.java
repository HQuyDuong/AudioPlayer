package com.example.audioplayer.db.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.audioplayer.db.entities.Playlist;

import java.util.List;

public class PlaylistTableImpl extends ContextTable<Playlist> implements PlaylistTable {
    public PlaylistTableImpl(Context context) {
        super(context);
    }

    @Override
    protected ContentValues convert(Playlist obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlaylistTable.COLUMN_ID_PLAYLIST, obj.getIdPlaylist());
        contentValues.put(PlaylistTable.COLUMN_NAME_PLAYLIST, obj.getNamePlaylist());
        return contentValues;
    }

    @Override
    protected String getName() {
        return PlaylistTable.TABLE_NAME;
    }

    @Override
    protected String getPrimaryColumn() {
        return PlaylistTable.COLUMN_ID_PLAYLIST;
    }

    @Override
    protected int getKey(Playlist object) {
        return object.getIdPlaylist();
    }

    @Override
    public Playlist searchPlaylist(String namePlaylist) {
        if(namePlaylist == ""){
            return null;
        }
        String groupBy = null;
        String having = null;
        String[] columns = null;
        String order = null;
        String whereClause = String.format("%s=?", PlaylistTable.COLUMN_NAME_PLAYLIST);
        String[] whereArgs = new String[]{
                namePlaylist
        };
        Cursor cursor = getDatabase().query(getName(), columns, whereClause, whereArgs, groupBy, having, order);
        Playlist obj = null;
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
    public List<Playlist> sapXepPlaylist(List<Playlist> playlistList) {
        return null;
    }

    @Override
    public Playlist extract(Cursor cursor) {
        int idPlaylistColumnIndex = cursor.getColumnIndex(PlaylistTable.COLUMN_ID_PLAYLIST);
        int namePlaylistColumnIndex = cursor.getColumnIndex(PlaylistTable.COLUMN_ID_PLAYLIST);
        Playlist playlist = new Playlist();

        playlist.setIdPlaylist(cursor.getInt(idPlaylistColumnIndex));
        playlist.setNamePlaylist(cursor.getString(namePlaylistColumnIndex));
        return playlist;
    }
}
