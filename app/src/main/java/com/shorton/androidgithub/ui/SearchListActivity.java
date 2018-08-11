package com.shorton.androidgithub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shorton.androidgithub.R;

public class SearchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        SearchListFragment searchListFragment = SearchListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, searchListFragment)
                .commit();
    }

}
