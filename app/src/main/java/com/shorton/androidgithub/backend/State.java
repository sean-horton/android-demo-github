package com.shorton.androidgithub.backend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.shorton.androidgithub.backend.data.User;
import com.shorton.androidgithub.backend.data.UserSearchQuery;
import com.shorton.androidgithub.backend.event.UserAvatarEvent;
import com.shorton.androidgithub.backend.event.UserListEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * State object to hold our application state in. As applications grow this component should
 * consist of smaller individual components, but for demo purposes it will all be here.
 */
public class State {

    private static final String TAG = State.class.getName();

    private static final String BASE_URL = "https://api.github.com";
    private static final String PATH_SEARCH_USERS = "/search/users";

    private final EventBus mEventBus;
    private final OkHttpClient mHttp;
    private final Map<String, Bitmap> mBitmapMap;

    public State(EventBus eventBus) {
        mEventBus = eventBus;
        mHttp = new OkHttpClient();
        mBitmapMap = new HashMap<>();
    }

    /**
     * Register a object to the event bus
     *
     * @param listener the object to listen to events
     */
    public void register(Object listener) {
        mEventBus.register(listener);
    }

    /**
     * Unregister an object from the event bus
     *
     * @param listener the object to remove from the event bus
     */
    public void unregister(Object listener) {
        mEventBus.unregister(listener);
    }

    /**
     * Search git hub for users.
     * <h3>Notify Events:</h3>
     * <ul>
     * <li>UserListEvent</li>
     * </ul>
     *
     * @param searchTerm the github user search term
     */
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

    /**
     * Get the Avatar image for a specified user.
     * <h3>Notify Events:</h3>
     * <ul>
     * <li>UserAvatarEvent: Indicating a avatar has been received</li>
     * </ul>
     *
     * @param user the user to get the avatar for
     * @return a cached bitmap of the users avatar or {@code null} if we do not have the image.
     */
    public synchronized Bitmap fetchImage(final User user) {
        Bitmap bitmap = mBitmapMap.get(user.getAvatarUrl());
        if (bitmap == null) {
            final Request request = new Request.Builder()
                    .url(user.getAvatarUrl())
                    .get()
                    .build();

            mHttp.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() >= 200 && response.code() <= 299 && response.body() != null) {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        addBitmap(user.getAvatarUrl(), bitmap);
                        mEventBus.post(new UserAvatarEvent());
                    }
                }
            });
        }
        return bitmap;
    }

    /**
     *
     * @param url
     * @param bitmap
     */
    private synchronized void addBitmap(String url, Bitmap bitmap) {
        mBitmapMap.put(url, bitmap);
    }
}
