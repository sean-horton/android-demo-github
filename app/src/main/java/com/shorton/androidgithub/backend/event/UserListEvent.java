package com.shorton.androidgithub.backend.event;

import com.shorton.androidgithub.backend.data.User;

import java.util.List;

/**
 * Indicates the User search list should be updated with new users.
 */
public class UserListEvent {

    private final boolean mSuccess;
    private final List<User> mUsers;

    public UserListEvent(boolean success, List<User> users) {
        mSuccess = success;
        mUsers = users;
    }

    /**
     * Indicates if the github search quarry was successful.
     *
     * @return {@code true} if successful, false otherwise
     */
    public boolean isSuccess() {
        return mSuccess;
    }

    /**
     * The new list of users to be displayed
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return mUsers;
    }

}
