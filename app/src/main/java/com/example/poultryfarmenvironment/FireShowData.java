package com.example.poultryfarmenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.poultryfarmenvironment.Model.User;
import com.example.poultryfarmenvironment.Model.UserAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FireShowData extends AppCompatActivity {


    private RecyclerView recyclerView;
    private UserAdapter mUserAdapter;
    private List<User> mDatalist;

    Button btnDelete;

    FirebaseDatabase database;
    DatabaseReference myRef;

    private ChildEventListener MyChildEventListener;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRef.removeEventListener(MyChildEventListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_show_data);

        btnDelete=findViewById(R.id.btnDelete);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("PoultryInformation");


        mDatalist=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter=new UserAdapter(this,mDatalist);
        recyclerView.setAdapter(mUserAdapter);


        MyChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user=snapshot.getValue(User.class);
            /*Log.d(TAG,"User Name :" + user.getUserName());
            Log.d(TAG,"User Name :" + user.getUserPassword());*/
                mDatalist.add(user);
                mUserAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        myRef.addChildEventListener(MyChildEventListener);





        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FirebaseDatabase.getInstance().getReference("PoultryInformation").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                  @SuppressLint("NotifyDataSetChanged")
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful()){
                     mDatalist.clear();
                     mUserAdapter.notifyDataSetChanged();
                     Toast.makeText(FireShowData.this, "History Clear successfully", Toast.LENGTH_SHORT).show();
                 }
                  }
              });
            }
        });
    }
}