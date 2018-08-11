package com.shorton.androidgithub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shorton.androidgithub.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchListFragment searchListFragment = SearchListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, searchListFragment)
                .commit();
    }

}
