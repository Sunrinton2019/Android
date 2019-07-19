package com.kmj.sunrinton19;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AddActivity extends AppCompatActivity {
    String imageurl="";
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        imageView=findViewById(R.id.add_imageView);
        Intent intent=getIntent();
        imageurl=intent.getStringExtra("url");
        Log.e("url",imageurl);
        Uri uri = Uri.parse(imageurl);
        Glide.with(this)
                .load(imageurl)
                .into(imageView);


    }
}
