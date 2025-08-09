package com.example.poultryfarmenvironment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class FanScreen extends AppCompatActivity {

    ImageView img_Hum_temp,img_Hum_fan,img_back;
    Button hum_temp_on,hum_temp_off,hum_fan_on,hum_fan_off;
    TextView tv_hum_temp,tv_hum_fan;

    GifImageView gif_one,gif_two;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String time,date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_screen);

        img_Hum_temp=findViewById(R.id.img_Hum_temp);
        img_Hum_fan=findViewById(R.id.img_Hum_fan);
        hum_temp_on=findViewById(R.id.hum_temp_on);
        hum_temp_off=findViewById(R.id.hum_temp_off);
        hum_fan_on=findViewById(R.id.hum_fan_on);
        hum_fan_off=findViewById(R.id.hum_fan_off);
        tv_hum_temp=findViewById(R.id.tv_hum_temp);
        tv_hum_fan=findViewById(R.id.tv_hum_fan);

        gif_one=findViewById(R.id.gif_one);
        gif_two=findViewById(R.id.gif_two);
        img_back=findViewById(R.id.img_back);



        time=getTimeWithAmPm();
        date = getCurrentdate();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Fan");

        gif_one.setVisibility(View.INVISIBLE);
        gif_two.setVisibility(View.INVISIBLE);



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back_Intent = new Intent(FanScreen.this,DashboardScreen.class);
                startActivity(back_Intent);
                finish();

            }
        });


       hum_temp_off.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myRef.addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                       myRef.child("FanTemperature").setValue("0");
                       tv_hum_temp.setText("Fan OFF");

                       gif_one.setVisibility(View.INVISIBLE);
                       img_Hum_temp.setVisibility(View.VISIBLE);

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
           }
       });

        hum_temp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                        myRef.child("FanTemperature").setValue("1");
                        tv_hum_temp.setText("Fan ON");
                        gif_one.setVisibility(View.VISIBLE);
                        img_Hum_temp.setVisibility(View.INVISIBLE);

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
            }
        });


        hum_fan_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                        myRef.child("HumidityFan").setValue("1");
                        tv_hum_fan.setText("Fan ON");
                        gif_two.setVisibility(View.VISIBLE);
                        img_Hum_fan.setVisibility(View.INVISIBLE);



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
            }
        });

        hum_fan_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                        myRef.child("HumidityFan").setValue("0");
                        tv_hum_fan.setText("Fan Off");

                        img_Hum_fan.setVisibility(View.VISIBLE);
                        gif_two.setVisibility(View.INVISIBLE);

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