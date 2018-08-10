package com.shorton.androidgithub.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shorton.androidgithub.R;
import com.shorton.androidgithub.backend.data.User;

import java.util.List;

public class SearchListAdapter extends BaseAdapter {

    private List<User> mUsers;

    public void update(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mUsers == null) {
            return 0;
        }
        return mUsers.size();
    }

    @Override
    public User getItem(int i) {
        if (mUsers == null || mUsers.size() < i) {
            return null;
        }
        return mUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_user, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.user_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        User user = getItem(i);
        if (user != null) {
            viewHolder.name.setText(user.getLogin());
        }

        return view;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView name;
    }

}
