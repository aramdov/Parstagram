package com.example.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    // tag for logs
    public static final String TAG = "LoginActivity";

    // create variables for the layout elements
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    // override the oncreate method. Why oncreate allows for clicklistener but outside this class it doesn't?. Weird auto-filling android errors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // use correct layout file

        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        // classic viewfinders
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                // retrieve the username and password from the edittext elements
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i(TAG, "Signup was successful");
                            loginUser(username, password);
                        } else {
                            Log.i(TAG, "Signup was not successful");
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });
    }





    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        // TO-DO: Navigate to main activity if the user signed in properly (reference week 5 lecture notes for advice on how to do this)
        // defaut run is on main ui thread, logininbackground runs this on the background threads so user can continue to do tasks
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if (e != null) {
                    Log.e(TAG,  "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with Login!", Toast.LENGTH_SHORT).show();
                    return;
                }

                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // context parameter is this, this is referring to this activity and activity is the instance of a context
    //
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
