package com.example.poultryfarmenvironment.Model;

import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultryfarmenvironment.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{


    private Context context;
    private List<User> mDatalist;

    public UserAdapter(Context context, List<User> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);


        return new MyViewHolder(myview);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user=mDatalist.get(position);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(user.getImg(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        holder.imageView.setImageBitmap(decodedImage);

        holder.tvId.setText("Record No: " + user.getId());
        holder.tv_temp.setText("Temperature: " + user.getTemperature()+" C");
        holder.tv_humidity.setText("Humidity: " + user.getHumidity() + " %");
        holder.tv_weight.setText("Bird's Weight: " + user.getBirdweight() + "gm");
        holder.tv_ultrasonic.setText("Ultrasonic: " + user.getUltrasonic() + "%");
        holder.tv_DateTime.setText("Date & Time: " + user.getDateTime());

        

    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvId,tv_temp,tv_humidity,tv_DateTime,tv_weight,tv_ultrasonic;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.card_image);
            tvId=itemView.findViewById(R.id.tvId);
            tv_temp=itemView.findViewById(R.id.tv_temp);
            tv_humidity=itemView.findViewById(R.id.tv_humidity);
            tv_DateTime=itemView.findViewById(R.id.tv_DateTime);
            tv_weight=itemView.findViewById(R.id.tv_weight);
            tv_ultrasonic=itemView.findViewById(R.id.tv_ultrasonic);




        }
    }

}
