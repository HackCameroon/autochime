package com.autochime.autochimeapplication;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.autochime.autochimeapplication.fragments.AddContactFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show contact selector
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(AddContactFragment.newInstance(), null);
//        ft.commit();
    }
}
