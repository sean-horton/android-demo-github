package com.shorton.androidgithub.backend;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shorton.androidgithub.backend.data.User;
import com.shorton.androidgithub.backend.data.UserSearchQuery;
import com.shorton.androidgithub.backend.event.UserListEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class State {

    private static final String TAG = State.class.getName();
    private static final String BASE_URL = "https://api.github.com";
    private static final String PATH_SEARCH_USERS = "/search/users";

    private final Context mContext;
    private final EventBus mEventBus;
    private final OkHttpClient mHttp;

    public State(Context context, EventBus eventBus) {
        mContext = context;
        mEventBus = eventBus;
        mHttp = new OkHttpClient();
    }

    public void register(Object listener) {
        mEventBus.register(listener);
    }

    public void unregister(Object listener) {
        mEventBus.unregister(listener);
    }

    public void fetchUsers(String searchTerm) {
        StringBuilder url = new StringBuilder()
                .append(BASE_URL)
                .append(PATH_SEARCH_USERS)
                .append("?")
                .append("q")
                .append("=")
                .append(searchTerm);

        final Request request = new Request.Builder()
                .url(url.toString())
                .get()
                .build();

        mHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.getMessage());
                mEventBus.post(new UserListEvent(false, new ArrayList<User>()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "Response back: " + response.code());
                if (response.code() >= 200 && response.code() <= 299 && response.body() != null) {
                    UserSearchQuery searchQuery
                            = new Gson().fromJson(response.body().charStream(), UserSearchQuery.class);
                    response.close();
                    Log.d(TAG, searchQuery.toString());
                    mEventBus.post(new UserListEvent(true, searchQuery.getUsers()));
                } else {
                    mEventBus.post(new UserListEvent(false, new ArrayList<User>()));
                }
            }
        });
    }

}