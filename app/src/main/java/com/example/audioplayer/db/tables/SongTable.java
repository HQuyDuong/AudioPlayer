package com.example.audioplayer.db.tables;

import com.example.audioplayer.db.entities.Song;

import java.util.List;

public interface SongTable extends Table<Song> {
    String TABLE_NAME = Table.TBL_SONG;

    String COLUMN_ID_SONG = "id_song";
    String COLUMN_NAME_SONG = "name_song";
    String COLUMN_PATH_SONG = "path_song";

    String COLUMN_ID_SONG_TYPE = ColumnType.INTEGER.name();
    String COLUMN_NAME_SONG_TYPE = ColumnType.TEXT.name();
    String COLUMN_PATH_SONG_TYPE = ColumnType.TEXT.name();

    Song searchSong(String nameSong);
    List<Song> sapXep(List<Song> songList);
}
