package com.autochime.autochimeapplication.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.autochime.autochimeapplication.R;
import com.autochime.autochimeapplication.database.ViolenceRecording;

public class RecordingAdapter extends CursorAdapter {
    String mRecordFileName;

    public RecordingAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_history_cell, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView latitudeView = (TextView) view.findViewById(R.id.history_cell_latitude);
        TextView longitudeView = (TextView) view.findViewById(R.id.history_cell_longitude);

        double lon = cursor.getDouble(cursor.getColumnIndexOrThrow(
                ViolenceRecording.RecordEntry.COLUMN_NAME_LONGITUDE));
        double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(
                ViolenceRecording.RecordEntry.COLUMN_NAME_LATITUDE));
        mRecordFileName = cursor.getString(cursor.getColumnIndexOrThrow(
                ViolenceRecording.RecordEntry.COLUMN_NAME_RECORDING_FILE_NAME));

        latitudeView.setText(String.valueOf(lat));
        longitudeView.setText(String.valueOf(lon));
    }

    public String getRecordFileName() {
        return mRecordFileName;
    }
}
