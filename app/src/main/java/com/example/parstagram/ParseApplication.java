package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // register the parse models
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Wt7Lh84fQ4bfHg7xFmYMjjtq4gjX1nH0wV4L9aAt")
                .clientKey("py3bNQ5BuujAIywfQ4Yl2JCBkOJOafGO0QOSHP3Z")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
