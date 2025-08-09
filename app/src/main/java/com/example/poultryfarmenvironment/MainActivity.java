package com.example.poultryfarmenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TextView textView,textView1;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        //textView1=findViewById(R.id.textView1);
        img1=findViewById(R.id.img1);

        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.myanimation);
        textView.setAnimation(myanim);
        //textView1.setAnimation(myanim);
        img1.setAnimation(myanim);


        Thread mythread=new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(MainActivity.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mythread.start();
    }
}