package com.example.poultryfarmenvironment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HumidityScreen extends AppCompatActivity {

    TextView tv_temperature,tv_date,tv_time,tv_humidity;
    ImageView img;
    Button btn_back;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_screen);

        tv_temperature=findViewById(R.id.tv_temperature);
        img=findViewById(R.id.img);
        btn_back=findViewById(R.id.btn_back);
        tv_humidity=findViewById(R.id.tv_humidity);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);


        time=getTimeWithAmPm();
        date = getCurrentdate();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        tv_date.setText("Date: "+date);
        tv_time.setText("Time: "+time);

        myRef.child("DHT11").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Object humidityObj = snapshot.child("Humidity").getValue();
                    Object tempObj = snapshot.child("Temperature").getValue();

                    if (humidityObj != null && tempObj != null) {
                        String humidity = humidityObj.toString();
                        String temperature = tempObj.toString();

                        tv_humidity.setText(humidity + " %");
                        tv_temperature.setText(temperature + " °C");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error: " + error.getMessage());
            }
        });

//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                String value = snapshot.child("Humidity").getValue(String.class);
//                String temp = snapshot.child("Temperature").getValue(String.class);
//                tv_humidity.setText(value+" %");
//                tv_temperature.setText(temp+"C");
//                tv_temp_dateTime.setVisibility(View.VISIBLE);
//                tv_temp_dateTime.setText(time+" "+date);
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent back_Intent = new Intent(HumidityScreen.this,DashboardScreen.class);
                startActivity(back_Intent);
                finish();

            }
        });




    }

    private String getTimeWithAmPm()
    {
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    private String getCurrentdate()
    {
        return new SimpleDateFormat("dd/LLL/yyyy", Locale.getDefault()).format(new Date());
    }
}