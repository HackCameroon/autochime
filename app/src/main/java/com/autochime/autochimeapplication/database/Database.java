package com.autochime.autochimeapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.autochime.autochimeapplication.AutoChimeApplication;

/**
 * Created by amytsai on 8/28/16.
 */
public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AutoChime.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " DOUBLE PRECISION";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ViolenceRecording.RecordEntry.TABLE_NAME + " (" +
                    ViolenceRecording.RecordEntry._ID + " INTEGER PRIMARY KEY," +
                    ViolenceRecording.RecordEntry.COLUMN_NAME_LATITUDE+ DOUBLE_TYPE + COMMA_SEP +
                    ViolenceRecording.RecordEntry.COLUMN_NAME_LONGITUDE+ DOUBLE_TYPE + COMMA_SEP +
                    ViolenceRecording.RecordEntry.COLUMN_NAME_RECORDING_FILE_NAME + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ViolenceRecording.RecordEntry.TABLE_NAME;

    private static Database mInstance = null;
    private SQLiteDatabase db = null;

    public static Database getInstance() {
        if (mInstance == null) {
            mInstance = new Database(AutoChimeApplication.getAppContext());
        }
        return mInstance;
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveRecordingEntry(Double latitude, Double longitude, String file_name) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ViolenceRecording.RecordEntry.COLUMN_NAME_LONGITUDE, longitude);
        values.put(ViolenceRecording.RecordEntry.COLUMN_NAME_LATITUDE, latitude);
        values.put(ViolenceRecording.RecordEntry.COLUMN_NAME_RECORDING_FILE_NAME, file_name);

        // Insert the new row, returning the primary key value of the new row
        db.insert(ViolenceRecording.RecordEntry.TABLE_NAME, null, values);
    }

    public Cursor getRecordingEntries() {
        String[] projection = {
                ViolenceRecording.RecordEntry._ID,
                ViolenceRecording.RecordEntry.COLUMN_NAME_LATITUDE,
                ViolenceRecording.RecordEntry.COLUMN_NAME_LONGITUDE,
                ViolenceRecording.RecordEntry.COLUMN_NAME_RECORDING_FILE_NAME
        };

        Cursor c = db.query(
                ViolenceRecording.RecordEntry.TABLE_NAME, projection, null, null, null, null, null);
        return c;
    }
}
