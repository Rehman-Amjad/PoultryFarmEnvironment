package com.example.poultryfarmenvironment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TemperatureScreen extends AppCompatActivity {

    TextView tv_temperature,tv_temp_dateTime;
    ImageView img;

    Button btn_back;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_screen);

        tv_temperature=findViewById(R.id.tv_temperature);
        tv_temp_dateTime=findViewById(R.id.tv_temp_dateTime);
        img=findViewById(R.id.img);
        btn_back=findViewById(R.id.btn_back);

        tv_temp_dateTime.setVisibility(View.INVISIBLE);

        time=getTimeWithAmPm();
        date = getCurrentdate();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("PoultryData");







        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String value = snapshot.child("WTemperature").getValue(String.class);
                String fireImage = snapshot.child("img").getValue(String.class);
                tv_temperature.setText(value+" C");
                tv_temp_dateTime.setVisibility(View.VISIBLE);
                tv_temp_dateTime.setText(time+" "+date);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                imageBytes = Base64.decode(fireImage, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                img.setImageBitmap(decodedImage);

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
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent back_Intent = new Intent(TemperatureScreen.this,DashboardScreen.class);
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