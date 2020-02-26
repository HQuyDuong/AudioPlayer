package com.example.audioplayer.db;

public interface SongsTable extends Table<Songs> {
    String TABLE_NAME = Table.TBL_SONGS;
    String COLUMN_PATH = "path";
    String COLUMN_NAME = "name";
    String COLUMN_ID = "id";

    String COLUMN_PATH_TYPE = Table.ColumnType.TEXT.name();
    String COLUMN_NAME_TYPE = Table.ColumnType.TEXT.name();
    String COLUMN_ID_TYPE = ColumnType.INTEGER.name();

}
