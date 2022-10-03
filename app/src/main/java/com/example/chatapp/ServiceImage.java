package com.example.chatapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.chatapp.databinding.ActivitySettingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ServiceImage extends Service {

    ActivitySettingsBinding binding;
    FirebaseDatabase database;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)

    {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String status = binding.etStatus.getText().toString();
        String username = binding.etUserName.getText().toString();

        HashMap<String , Object> obj = new HashMap<>();
        obj.put("userName",username);
        obj.put("about",status);

        database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .updateChildren(obj);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
