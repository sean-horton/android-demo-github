package com.shorton.androidgithub.backend.event;

import com.shorton.androidgithub.backend.data.User;

import java.util.List;

public class UserListEvent {

    private final boolean mSuccess;
    private final List<User> mUsers;

    public UserListEvent(boolean success, List<User> users) {
        mSuccess = success;
        mUsers = users;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public List<User> getUsers() {
        return mUsers;
    }

}
