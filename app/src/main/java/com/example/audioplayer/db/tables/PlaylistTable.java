package com.example.audioplayer.db.tables;

import com.example.audioplayer.db.entities.Playlist;

import java.util.List;

public interface PlaylistTable extends Table<Playlist> {
    String TABLE_NAME = Table.TBL_PLAYLIST;

    String COLUMN_ID_PLAYLIST = "id_playlist";
    String COLUMN_NAME_PLAYLIST = "name_playlist";

    String COLUMN_ID_PLAYLIST_TYPE = ColumnType.INTEGER.name();
    String COLUMN_NAME_PLAYLIST_TYPE = ColumnType.TEXT.name();

    Playlist searchPlaylist(String name);
    List<Playlist> sapXepPlaylist(List<Playlist> playlistList);
}
