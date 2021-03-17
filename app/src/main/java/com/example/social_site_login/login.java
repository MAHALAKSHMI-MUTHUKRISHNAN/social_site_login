package com.example.social_site_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class login extends AppCompatActivity {
    Button signout;
    GoogleSignInClient mGoogleSignInClient;
    TextView name,email,id;
    ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        //id = findViewById(R.id.id);
        pic = findViewById(R.id.pic);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Button signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            };
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            // String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            //String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            name.setText(personName);
            email.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);

        }
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(login.this,"signout",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}