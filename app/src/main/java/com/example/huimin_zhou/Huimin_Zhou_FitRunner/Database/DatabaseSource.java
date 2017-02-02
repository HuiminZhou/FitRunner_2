package com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Lucidity on 17/1/29.
 */

public class DatabaseSource {
    private String[] allColumns = {
            MySQLOpenHelper.ID, MySQLOpenHelper.INPUT_TYPE, MySQLOpenHelper.ACTIVITY_TYPE,
            MySQLOpenHelper.DATE, MySQLOpenHelper.TIME, MySQLOpenHelper.DURATION,
            MySQLOpenHelper.DISTANCE, MySQLOpenHelper.CALORIES, MySQLOpenHelper.HEART_RATE};

    private SQLiteDatabase mDatabase;
    private MySQLOpenHelper mSQLOpenHelper;
    private Context context;
    public DatabaseSource(Context context) {
        mSQLOpenHelper = new MySQLOpenHelper(context);
        this.context = context;
    }

    public void open() throws SQLException {
        if (mSQLOpenHelper == null) {
            mSQLOpenHelper = new MySQLOpenHelper(context);
        }
        mDatabase = mSQLOpenHelper.getReadableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public void insertEntry(ExerciseEntry entry) {
        ContentValues values = new ContentValues();

        values.put(MySQLOpenHelper.INPUT_TYPE,      entry.getInputType());
        values.put(MySQLOpenHelper.ACTIVITY_TYPE,   entry.getActivityType());
        values.put(MySQLOpenHelper.DATE,            entry.getDate());
        values.put(MySQLOpenHelper.TIME,            entry.getTime());
        values.put(MySQLOpenHelper.DURATION,        entry.getDuration());
        values.put(MySQLOpenHelper.DISTANCE,        entry.getDistance());
        values.put(MySQLOpenHelper.CALORIES,        entry.getCalories());
        values.put(MySQLOpenHelper.HEART_RATE,      entry.getHeart());

        mDatabase.insert(MySQLOpenHelper.TABLE_NAME, null, values);
    }

    // query all the columns
    public ArrayList<ExerciseEntry> queryAll() {
        ArrayList<ExerciseEntry> arrayList = new ArrayList<ExerciseEntry>();
        Cursor cursor = mDatabase.query(
                MySQLOpenHelper.TABLE_NAME,
                allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExerciseEntry entry = cursorToEntry(cursor);
            arrayList.add(entry);
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ExerciseEntry queryOne(long ID) {
        Cursor cursor = mDatabase.query(
                MySQLOpenHelper.TABLE_NAME,
                allColumns,
                MySQLOpenHelper.ID + "=?",
                new String[] {String.valueOf(ID)}, null, null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            return cursorToEntry(cursor);
        } else {
            return null;
        }
    }

    public void delete(ExerciseEntry entry) {
        mDatabase.delete(MySQLOpenHelper.TABLE_NAME,
                MySQLOpenHelper.ID + " = " + entry.getId(), null);
    }

    public void deleteAll() {
        mDatabase.delete(MySQLOpenHelper.TABLE_NAME, null, null);
    }

    private ExerciseEntry cursorToEntry(Cursor cursor) {
        ExerciseEntry entry = new ExerciseEntry();
        entry.setId(cursor.getLong(0));
        entry.setInputType(cursor.getString(1));
        entry.setActivityType(cursor.getString(2));
        entry.setDate(cursor.getString(3));
        entry.setTime(cursor.getString(4));
        entry.setDuration(cursor.getInt(5));
        entry.setDistance(cursor.getInt(6));
        entry.setCalories(cursor.getInt(7));
        entry.setHeart(cursor.getInt(8));
        return entry;
    }
}
