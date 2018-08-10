package com.shorton.androidgithub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shorton.androidgithub.R;

public class UserDetailsFragment extends Fragment {

    public static UserDetailsFragment newInstance() {
        return new UserDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_details_fragment, container);

        // TODO

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
