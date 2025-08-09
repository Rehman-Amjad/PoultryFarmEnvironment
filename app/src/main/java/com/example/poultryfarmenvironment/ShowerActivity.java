package com.example.poultryfarmenvironment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShowerActivity extends AppCompatActivity {

    ImageView img_back,bulb_off_image,bulb_on_image;
    TextView tv_message;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String time,date;
    Button btn_on,btn_off;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shower);

        img_back = findViewById(R.id.img_back);
        bulb_off_image = findViewById(R.id.bulb_off_image);
        bulb_on_image = findViewById(R.id.bulb_on_image);
        tv_message = findViewById(R.id.tv_message);
        btn_on = findViewById(R.id.btn_on);
        btn_off = findViewById(R.id.btn_off);

        img_back.setOnClickListener(v -> {
            onBackPressed();
        });

        time=getTimeWithAmPm();
        date = getCurrentdate();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Fan");


        getData();

        btn_on.setOnClickListener(v -> {
            saveData("1","Shower is On");
        });

        btn_off.setOnClickListener(v -> {
            saveData("0","Shower is off");
        });

    }

    private void getData()
    {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String value = snapshot.child("Shower").getValue(String.class);

                    assert value != null;
                    if (value.equals("0"))
                    {
                        tv_message.setText("Shower is Off");
                        bulb_off_image.setVisibility(View.VISIBLE);
                        bulb_on_image.setVisibility(View.GONE);
                    }else if (value.equals("1"))
                    {
                        tv_message.setText("Shower is On");
                        bulb_off_image.setVisibility(View.GONE);
                        bulb_on_image.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void saveData(String value,String message)
    {
        database.getReference().child("Fan").child("Shower").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(ShowerActivity.this, message, Toast.LENGTH_SHORT).show();
                    getData();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ShowerActivity.this, "error", Toast.LENGTH_SHORT).show();
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