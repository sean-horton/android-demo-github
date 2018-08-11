package com.shorton.androidgithub.backend.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * gson map of json from GitHub.
 *
 * @see <a href=https://developer.github.com/v3/search/#search-users">GitHub API</a>
 */
public class UserSearchQuery {

    @SerializedName("total_count")
    private int mTotalCount;

    @SerializedName("incomplete_results")
    private boolean mIncompleteResults;

    @SerializedName("items")
    private User[] mUsers;

    public int getTotalCount() {
        return mTotalCount;
    }

    public boolean isIncompleteResults() {
        return mIncompleteResults;
    }

    public List<User> getUsers() {
        return new ArrayList<>(Arrays.asList(mUsers));
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
