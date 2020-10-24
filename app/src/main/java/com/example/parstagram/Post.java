package com.example.parstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

// Parse class name should match with the entity name in the parse dashboard
@ParseClassName("Post")
public class Post extends ParseObject {

    // Define getters and setters with key names in parse dashboard for each attribute
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";

    // getstring is a method defined on the parseobject class. looks at it and pulls out hte attribute with name (KEY_DESCRIPTION)
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    // put method is also defined on the parse object
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    // getting back a parse file for the image attribute (file data type)
    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }


}
