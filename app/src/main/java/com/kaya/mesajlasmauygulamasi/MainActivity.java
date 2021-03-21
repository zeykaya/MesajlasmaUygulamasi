package com.kaya.mesajlasmauygulamasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> list;
    String username;
    RecyclerView userRecyclerView;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        listele();
    }
    public void tanimla(){
        username=getIntent().getExtras().getString("kadi");
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        list=new ArrayList<>();
        userRecyclerView=(RecyclerView)findViewById(R.id.userRecyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);
        userRecyclerView.setLayoutManager(layoutManager);
        userAdapter=new UserAdapter(MainActivity.this,list,MainActivity.this,username);
        userRecyclerView.setAdapter(userAdapter);
    }
    public void listele(){
        reference.child("Kullanıcılar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                if (!dataSnapshot.getKey().equals(username)){
                    list.add(dataSnapshot.getKey());
                    Log.i("Kullanıcı:",dataSnapshot.getKey());
                    userAdapter.notifyDataSetChanged();//liste güncellendikçe adapter güncellenecek.
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}