package com.cs48.MusicQ;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //pass is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        progressDialog.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Main2Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(Main2Activity.this, "Couldn't register", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {

        if (view == buttonRegister) {
            Toast.makeText(Main2Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            registerUser;
            startActivity(new Intent(Main2Activity.this, MainActivity.class));
            
        }
        if (view == textViewSignin) {
            startActivity(new Intent(Main2Activity.this, SignIn.class));
        }
    }
}
