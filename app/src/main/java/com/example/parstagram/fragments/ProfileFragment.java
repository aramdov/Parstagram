package com.example.parstagram.fragments;


import android.util.Log;

import com.example.parstagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    // modify the query from PostsFragment (first make it protected so we can Override)
    @Override
    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);

        // update the query where username is equal to the one logged in. this is the only change
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        query.setLimit(20);
        // the timelien data is randomly ordered, but we want a chronological order in descending(by time)
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override //parmaeters are a list of all postobjects from database and a parsexception (recurring pattern)
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                for (Post post: posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }




}
