package com.shorton.androidgithub.ui;

import android.app.Application;

import com.shorton.androidgithub.backend.State;

import org.greenrobot.eventbus.EventBus;

/**
 * Extend Android Application. Using this to store our {@link State} object.
 */
public final class App extends Application {

    private static App INSTANCE;

    private State mState;
    private EventBus mEventBus;

    /**
     * Get this instance.
     *
     * @return our {@link App} instance
     */
    public synchronized static App getInstance() {
        return INSTANCE;
    }

    /**
     * Get our state object
     *
     * @return our {@link State} object
     */
    public State getState() {
        return mState;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;

        mEventBus = new EventBus();
        mState = new State(this, mEventBus);

        super.onCreate();
    }

}
