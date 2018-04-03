package kta999.entertainment.panamamonk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Profile.getCurrentProfile() != null) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
        callbackManager = CallbackManager.Factory.create();

        //checkLogin();
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }


                    @Override
                    public void onCancel () {
                        //Log.d(TAG, "Login attempt cancelled.");
                    }

                    @Override
                    public void onError (FacebookException e){
                        e.printStackTrace();
                        //Log.d(TAG, "Login attempt failed.");
                    }
                }
        );


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }



}
