package com.example.avmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button button;
    TextView loginsignup;
    EditText email,password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    android.app.ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.loginbutton);
        email=findViewById(R.id.editTextTextloginEmailAddress);
        password=findViewById(R.id.editTextloginPassword);
        loginsignup=findViewById(R.id.loginsignup);
        loginsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();

                if((TextUtils.isEmpty(Email))){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Enter the email", Toast.LENGTH_SHORT).show();
                } else if ((TextUtils.isEmpty(Password))) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Enter the password", Toast.LENGTH_SHORT).show();

                } else if (!Email.matches(emailPattern)) {
                    progressDialog.dismiss();
                    email.setError("Give proper email address");
                    
                } else if (Password.length()<6) {
                    progressDialog.dismiss();
                    password.setError("More than six characters");
                    Toast.makeText(LoginActivity.this, "password needs to be longer than six characters", Toast.LENGTH_SHORT).show();
                    
                }else {
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                progressDialog.show();
                                try {
                                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }catch (Exception e){

                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                            else {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }


            }
        });
    }
}