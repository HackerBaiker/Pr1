package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParkingLot extends AppCompatActivity {

    private Button pl1;
    private Button pl2;

    private  Firebase mRootRef;
    private  FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_lot);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://userdata-da93a.firebaseio.com/ParkingLot");

        Button logout = (Button) findViewById(R.id.logoutB);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    mFirebaseAuth.signOut();
                    Toast.makeText(ParkingLot.this, "Logout is successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ParkingLot.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ParkingLot.this, "You are not currently logged in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pl1 = (Button) findViewById(R.id.pl1);
        pl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1==1){
                    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                    if (mFirebaseUser != null) {
                        // User is signed in.

                        Firebase childRef = mRootRef.child("Pirm33a");
                        childRef.setValue("12333");
                        //validate();
                        Toast.makeText(ParkingLot.this, "Prisijungta?", Toast.LENGTH_SHORT).show();
                    } else {
                        // No user is signed in.
                        Toast.makeText(ParkingLot.this, "Neprisijungta?", Toast.LENGTH_SHORT).show();
                    }
                }

            };
        });


        pl2 = (Button) findViewById(R.id.pl2);
        pl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ParkingLot");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(ParkingLot.this, "Pasileidzia", Toast.LENGTH_SHORT).show();
                        if(snapshot.exists()){
                            String m = snapshot.child("Antra").child("12346").getValue(String.class);
                            Toast.makeText(ParkingLot.this, m, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Toast.makeText(ParkingLot.this, "O cia?", Toast.LENGTH_SHORT).show();
//
//                        String a = snapshot.getValue().toString();
//                        String b = snapshot.getChildren().toString();
//
//                        Toast.makeText(ParkingLot.this, a, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(ParkingLot.this, b, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });

    }


    private void validate(){

      DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ParkingLot");
        Toast.makeText(ParkingLot.this, "asd", Toast.LENGTH_SHORT).show();

        reference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              Toast.makeText(ParkingLot.this, "ccc", Toast.LENGTH_SHORT).show();

              String a = snapshot.getValue().toString();
          String b = snapshot.getChildren().toString();

              Toast.makeText(ParkingLot.this, a, Toast.LENGTH_SHORT).show();
              Toast.makeText(ParkingLot.this, b, Toast.LENGTH_SHORT).show();

          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });
    };


}