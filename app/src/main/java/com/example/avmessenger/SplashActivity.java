package com.example.avmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    TextView name,own1,own2;
    Animation topanim,bottomanim;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_splash);
        logo=findViewById(R.id.logoimage);
        name=findViewById(R.id.logoname);
        own1=findViewById(R.id.ownone);
        own2=findViewById(R.id.owntwo);


       // if(auth.getCurrentUser()==null) {
          //  Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            //startActivity(intent);
        //}else {
          //  Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            //startActivity(intent);
        //}



        topanim= AnimationUtils.loadAnimation(this,R.anim.topanimation);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.bototmanimation);

        logo.setAnimation(topanim);
        name.setAnimation(bottomanim);
        own1.setAnimation(bottomanim);
        own2.setAnimation(bottomanim);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth.getCurrentUser() == null) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent =new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        },4000);
    }
}