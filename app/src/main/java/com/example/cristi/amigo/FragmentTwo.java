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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentTwo extends Fragment {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private TextView startPoint, endPoint, date, time, seats;
    private Button add;
    private int newRoutes;
    private String initialName = "Anastasiu Cristian", initialDOB = "22/09/1997", initialEmail= "cristi@mail.com", initialRoutes = "0";

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        startPoint = view.findViewById(R.id.etAddFrom);
        endPoint = view.findViewById(R.id.etAddTo);
        date = view.findViewById(R.id.etAddDate);
        time = view.findViewById(R.id.etAddTime);
        seats = view.findViewById(R.id.etAddSeats);
        add = view.findViewById(R.id.btnAddCreate);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startPoint.getText().toString().isEmpty() || endPoint.getText().toString().isEmpty() || date.getText().toString().isEmpty() || time.getText().toString().isEmpty() || seats.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Invalid details",Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference databaseReference;
                    RideDetails rideDetails = new RideDetails(firebaseAuth.getUid(),startPoint.getText().toString(),endPoint.getText().toString(),date.getText().toString(),time.getText().toString(),seats.getText().toString());
                    databaseReference = firebaseDatabase.getReference("rides");
                    databaseReference.push().setValue(rideDetails);
                    startPoint.setText("");
                    endPoint.setText("");
                    date.setText("");
                    time.setText("");
                    seats.setText("");
                    Toast.makeText(getActivity(),"Ride created",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }


}
