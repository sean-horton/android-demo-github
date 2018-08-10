package com.shorton.androidgithub.ui;

import android.app.Application;

import com.shorton.androidgithub.backend.State;

import org.greenrobot.eventbus.EventBus;

public final class App extends Application {

    private static App INSTANCE;

    private State mState;
    private EventBus mEventBus;

    public synchronized static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;

        mEventBus = new EventBus();
        mState = new State(this, mEventBus);

        super.onCreate();
    }

    public State getState() {
        return mState;
    }
}
