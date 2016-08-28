package com.autochime.autochimeapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.autochime.autochimeapplication.adapter.RecordingAdapter;
import com.autochime.autochimeapplication.database.Database;
import com.autochime.autochimeapplication.database.ViolenceRecording;

public class HistoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView mHistoryList;
    RecordingAdapter mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mHistoryList = (ListView) findViewById(R.id.history_list);
        mHistoryList.setOnItemClickListener(this);
        mCursor = new RecordingAdapter(
                this,
                Database.getInstance().getRecordingEntries());
        mHistoryList.setAdapter(mCursor);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.history_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View item, int position, long rowID) {
        RecordingAdapter cursor = ((RecordingAdapter)parent.getAdapter());
        SQLiteCursor c = (SQLiteCursor) cursor.getItem(position);
        Log.d("LOOOK________", c.getString(
                c.getColumnIndex(ViolenceRecording.RecordEntry.COLUMN_NAME_RECORDING_FILE_NAME)));
        AudioRecorder.instance().StartPlayback(c.getString(
                c.getColumnIndex(ViolenceRecording.RecordEntry.COLUMN_NAME_RECORDING_FILE_NAME)));
    }
}
