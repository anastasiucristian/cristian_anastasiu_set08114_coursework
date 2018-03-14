package com.example.cristi.amigo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentThree extends Fragment {
    private TextView UserEmail;
    private TextView UserName;
    private TextView UserAge;
    private TextView RoutesDone;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    public FragmentThree() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);
        UserEmail = view.findViewById(R.id.tvUserEmail);
        UserName = view.findViewById(R.id.tvUserUsername);
        UserAge = view.findViewById(R.id.tvUserAge);
        RoutesDone = view.findViewById(R.id.tvUserRoutesDone);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();





        final DatabaseReference databaseReference;
        DatabaseReference ref = firebaseDatabase.getReference("users");
        databaseReference = ref.child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                UserAge.setText("Date of birth: " + userDetails.getUserDOB());
                UserName.setText("Name: " + userDetails.getUserName());
                UserEmail.setText("Email: " + userDetails.getUserEmail());
                RoutesDone.setText("Routes Done: " + userDetails.getRoutesDone());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }



}
