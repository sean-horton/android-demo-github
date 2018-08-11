package com.shorton.androidgithub.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.shorton.androidgithub.R;
import com.shorton.androidgithub.backend.State;
import com.shorton.androidgithub.backend.event.UserAvatarEvent;
import com.shorton.androidgithub.backend.event.UserListEvent;

import org.greenrobot.eventbus.Subscribe;

public class SearchListFragment extends Fragment {

    private static final String TAG = SearchListFragment.class.getName();

    private ListView mUserList;
    private EditText mEditText;
    private Button mButton;
    private SearchListAdapter mAdapter;
    private State mState;

    public static SearchListFragment newInstance() {
        return new SearchListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mState = App.getInstance().getState();

        View rootView = inflater.inflate(R.layout.search_list_fragment, container, false);

        mUserList = rootView.findViewById(R.id.user_list);
        mEditText = rootView.findViewById(R.id.search_edit_text);
        mButton = rootView.findViewById(R.id.button_go_user);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick();
            }
        });

        mAdapter = new SearchListAdapter(mState);
        mUserList.setAdapter(mAdapter);
        mUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mState.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mState.unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //////////////////////////
    // EventBus
    //////////////////////////
    @Subscribe
    public void onUserListEvent(final UserListEvent event) {
        if (event.isSuccess()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.update(event.getUsers());
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Unable to get users.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Subscribe
    public void onUserAvatarEvent(UserAvatarEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //////////////////////////
    // Helper Methods
    //////////////////////////
    private void runOnUiThread(Runnable runnable) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

    private void handleButtonClick() {
        String text = mEditText.getText().toString();
        mState.fetchUsers(text);
    }

    private void handleListClick() {

    }

}
