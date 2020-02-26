package com.example.audioplayer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class ContextTable<T> implements Table<T> {
    protected Context context;
    public ContextTable(Context context) {
        this.context = context;
    }
    protected SQLiteDatabase getDatabase() {
        return DatabaseHelper.getInstance(context).getDatabase();
    }
    @Override
    public long insert(T obj) {
        ContentValues contentValues = convert(obj);
        if (isAutoincrement()) {
            contentValues.remove(getPrimaryColumn());
        }
        return getDatabase().insert(getName(), null, contentValues);
    }

    @Override
    public T get(String id) {
        if(id == null){
            return null;
        }
        String groupBy = null;
        String having = null;
        String[] columns = null;
        String order = null;
        String whereClause = String.format("%s=?", getPrimaryColumn());
        String[] whereArgs = new String[]{
                id
        };
        Cursor cursor = getDatabase().query(getName(), columns, whereClause, whereArgs, groupBy, having, order);
        T obj = null;
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
    public List<T> findAll() {
        Cursor cursor = getDatabase().rawQuery("select * from " + getName(), null);
        ArrayList<T> result = new ArrayList<>();
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        result.add(extract(cursor));
                        cursor.moveToNext();
                    }
                }
            } finally {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }

        return result;
    }
    @Override
    public boolean delete(String id) {
        String whereClause = String.format("%s=?", getPrimaryColumn());
        String[] whereArgs = new String[]{
                id
        };
        int numOfDeletedRow = getDatabase().delete(getName(), whereClause, whereArgs);
        return numOfDeletedRow > 0;
    }
    @Override
    public boolean update(T obj) {
        String whereClause = String.format("%s=?", getPrimaryColumn());
        String[] whereArgs = new String[]{
                String.valueOf(getKey(obj))
        };
        ContentValues contentValues = convert(obj);
        contentValues.remove(getPrimaryColumn());
        int numOfUpdatedRow = getDatabase().update(getName(), contentValues, whereClause, whereArgs);
        return numOfUpdatedRow > 0;
    }

    protected boolean isAutoincrement() {
        return true;
    }
    protected abstract String getKey(T object);
    protected abstract String getPrimaryColumn();
    protected abstract ContentValues convert(T obj);
    protected abstract String getName();
    protected abstract T extract(Cursor cursor);
}
