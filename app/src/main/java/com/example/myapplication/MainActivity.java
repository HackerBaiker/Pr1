package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText PersonEmailL, passwordL;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PersonEmailL = (EditText)findViewById(R.id.emailMain);
        passwordL = (EditText)findViewById(R.id.passwordMain);

        firebaseAuth = FirebaseAuth.getInstance();
       // FirebaseUser user = firebaseAuth.getCurrentUser(); //tikrina ar user yra prisijunges


        Button signingIn = (Button) findViewById(R.id.signButton);
        signingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        Button parkingLot = (Button) findViewById(R.id.goToParkingLot);
        parkingLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParkingLot.class);
                startActivity(intent);
            }
        });

        Button login = (Button)findViewById(R.id.loginB);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = PersonEmailL.getText().toString();
                String pw = passwordL.getText().toString();

                if(em.isEmpty() || pw.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
                }else{
                    validate(PersonEmailL.getText().toString(), passwordL.getText().toString());
                }

            }
        });

        Button testing = (Button) findViewById(R.id.testingB);
        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Credentials.class);
                startActivity(intent);
            }
        });
    }

    private void validate(String userName, String userPassword){

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ParkingLot.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}