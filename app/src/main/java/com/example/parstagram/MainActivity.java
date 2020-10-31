package com.example.parstagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parstagram.fragments.ComposeFragment;
import com.example.parstagram.fragments.PostsFragment;
import com.example.parstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Notes from week 1 summary
        // Setup UI to login, take a picture, submit that picture, properly save it into our Parse database
        // TO-DO: Implement Sign-up button, use the link and assign code block to a button https://guides.codepath.org/android/Building-Data-driven-Apps-with-Parse#user-signup
        // TO-DO: Implement log out: Setup and call a Logout method from parse and then had an intent take me back to the login screen.
        // Next week we work on retrieving the posts from the backend and displaying it in recyclerview

    // references to widgets in layout
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    public static final String TAG = "MainActivity";
    private File photoFile;
    public String photoFileName = "photo.jpg";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Menuitem is one of the 3 menu items we have defined, have to check which one it is and call respective fragment
                // import androidx fragment
                // make changes based on transactions and replace the container with a different fragment. click -> inflate fragment layout
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
//                        Toast.makeText(MainActivity.this, "Selected Action home", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_compose:
//                        Toast.makeText(MainActivity.this, "Selected action compose!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
//                        Toast.makeText(MainActivity.this, "Selected action Profile!", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }



    // implicit intent to open android camera app and take a picture
    // indicate to the application we launched where we will put the photo output (fileprovider wraps the photo file)
    // is there an application that can handle this intent(almost every phone should be ok). after this startactivityforRESULT(to return image)
    // URI = uniform resource identifier, its a string that identifies  a resource unambigiously. getPhotoFileURi is a helper function
    //

}