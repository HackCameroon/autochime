package com.autochime.autochimeapplication.database;

import android.provider.BaseColumns;

/**
 * Created by amytsai on 8/28/16.
 */
public final class ViolenceRecording {
    private ViolenceRecording() {}

    /* Inner class that defines the table contents */
    public static class RecordEntry implements BaseColumns {
        public static final String TABLE_NAME = "violence_recording";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_RECORDING_FILE_NAME = "recording_file_name";
    }
}
