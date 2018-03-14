package com.example.cristi.amigo;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class FragmentOne extends Fragment {

    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private int a = 0;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        listView = view.findViewById(R.id.lvList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("rides");



        Query query = firebaseDatabase.getReference("rides");
        FirebaseListOptions<RideDetails> options = new FirebaseListOptions.Builder<RideDetails>()
                .setLayout(R.layout.route_layout)//Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                .setQuery(query, RideDetails.class)
                .setLifecycleOwner(this)
                .build();

        FirebaseListAdapter<RideDetails> adapter = new FirebaseListAdapter<RideDetails>(options) {
            @Override
            protected void populateView(View v, RideDetails model, int position) {
                TextView tvMessage = v.findViewById(R.id.tvStartPoint);
                tvMessage.setText("From: " + model.getStartPoint());
                tvMessage = v.findViewById(R.id.tvEndPoint);
                tvMessage.setText("To: " + model.getEndPoint());
                tvMessage = v.findViewById(R.id.tvSeatsLeft);
                if(model.getSeats().equals("1")){
                    tvMessage.setText(model.getSeats() + " seat left");
                }else{
                    tvMessage.setText(model.getSeats() + " seats left");
                }

                tvMessage = v.findViewById(R.id.tvDateTime);
                tvMessage.setText("On: " + model.getRideDate() + " At: " + model.getRideTime());
                tvMessage = v.findViewById(R.id.tvDriverName);
                DatabaseReference userRef = firebaseDatabase.getReference().child("users").child(model.getUserID());
                final TextView finalTvMessage = tvMessage;
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                        finalTvMessage.setText(userDetails.getUserName());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        listView.setAdapter(adapter);




        return view;
    }


}
