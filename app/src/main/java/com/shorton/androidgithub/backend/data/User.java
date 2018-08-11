package com.shorton.androidgithub.backend.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * gson map of json from GitHub.
 *
 * @see <a href=https://developer.github.com/v3/search/#search-users">GitHub API</a>
 */
public class User {

    @SerializedName("login")
    private String mLogin;

    @SerializedName("id")
    private int mId;

    @SerializedName("node_id")
    private String mNodeId;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @SerializedName("gravatar_id")
    private String mGravatarId;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("html_url")
    private String mHtmlUrl;

    @SerializedName("followers_url")
    private String mFollowersUrl;

    @SerializedName("subscriptions_url")
    private String mSubscriptionsUrl;

    @SerializedName("organizations_url")
    private String mOrganizationsUrl;

    @SerializedName("repos_url")
    private String mReposUrl;

    @SerializedName("received_events_url")
    private String mReceivedEventsUrl;

    @SerializedName("type")
    private String mType;

    @SerializedName("score")
    private String mScore;

    public String getLogin() {
        return mLogin;
    }

    public int getId() {
        return mId;
    }

    public String getNodeId() {
        return mNodeId;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getGravatarId() {
        return mGravatarId;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public String getSubscriptionsUrl() {
        return mSubscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return mOrganizationsUrl;
    }

    public String getReposUrl() {
        return mReposUrl;
    }

    public String getReceivedEventsUrl() {
        return mReceivedEventsUrl;
    }

    public String getType() {
        return mType;
    }

    public String getScore() {
        return mScore;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
