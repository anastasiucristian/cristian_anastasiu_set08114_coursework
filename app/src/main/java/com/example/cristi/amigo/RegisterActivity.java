package com.example.cristi.amigo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class RegisterActivity extends Activity {

    private Button registerButton;
    private TextView userName, userEmail, userPassword, userConfirmPassword, userDOB;
    private TextView cancelButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setup();



        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if(validate()){
                    String usrEmail = userEmail.getText().toString().trim();
                    String usrPassword = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(usrEmail,usrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                userData();
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    String usrName = userName.getText().toString();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

    }


    @SuppressLint("WrongViewCast")
    private void setup(){
        userName = findViewById(R.id.etUserName);
        userEmail = findViewById(R.id.etEmail);
        userPassword = findViewById(R.id.etUserPassword);
        userConfirmPassword = findViewById(R.id.etPasswordConfirm);
        registerButton = findViewById(R.id.btnRegisterConfirm);
        cancelButton = findViewById(R.id.tvRegisterCancel);
        userDOB = findViewById(R.id.etUserDOB);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }


    private boolean validate(){
        String usrName = userName.getText().toString();
        String usrEmail = userEmail.getText().toString();
        String usrPassword = userPassword.getText().toString();
        String usrPasswordConfirm = userConfirmPassword.getText().toString();

        if(usrEmail.isEmpty() || usrName.isEmpty() || usrPassword.isEmpty() || usrPasswordConfirm.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    private void userData(){
        DatabaseReference databaseReference;
        UserDetails userDetails = new UserDetails(userName.getText().toString(),userDOB.getText().toString(),userEmail.getText().toString(),"0");
        DatabaseReference ref = firebaseDatabase.getReference("users");
        databaseReference = ref.child(firebaseAuth.getUid());
        databaseReference.setValue(userDetails);


    }


}
