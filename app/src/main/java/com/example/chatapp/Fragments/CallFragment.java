package com.example.chatapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chatapp.ChatDetailActivity;
import com.example.chatapp.Models.User;
import com.example.chatapp.databinding.FragmentCallBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentCallBinding binding;
    ArrayList<User> list = new ArrayList<>();
    FirebaseDatabase database;
    FirebaseUser user;
    Context context;
    ProgressDialog progressDialog=null;
    final String[] user_id = {null};



    public CallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallFragment newInstance(String param1, String param2) {
        CallFragment fragment = new CallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        database = FirebaseDatabase.getInstance();
        binding = FragmentCallBinding.inflate(inflater, container, false);

        binding.stRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                 database.getReference().child("Searching").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot)
                     {
                         if (!snapshot.hasChildren())
                         {

                                 database.getReference().child("Searching").child(FirebaseAuth.getInstance().getUid()).setValue(FirebaseAuth.getInstance().getUid());
                                 progressDialog = new ProgressDialog(getActivity());
                                 progressDialog.setTitle("Random");
                                 progressDialog.setMessage(("Waiting for Someone To Join"));
                                 progressDialog.show();

                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });

                database.getReference().child("Searching").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                       if (snapshot.hasChildren())
                       {
                           for (DataSnapshot dataSnapshot: snapshot.getChildren())
                           {
                               user_id[0] = dataSnapshot.getValue(String.class);

                               break;
                           }

                           if (user_id[0] != FirebaseAuth.getInstance().getUid())
                           {
                               if(progressDialog!=null)
                                progressDialog.dismiss();

                               database.getReference().child("Searching").child(user_id[0]).setValue(FirebaseAuth.getInstance().getUid());




                               Intent intent = new Intent(getActivity(), ChatDetailActivity.class);
                               intent.putExtra("userId", user_id[0]);
                               intent.putExtra("profilePic", "null");
                               intent.putExtra("userName", "Random User");
                               getActivity().startActivity(intent);




                           }


                       }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });







            }
        });



        return binding.getRoot();
    }



}