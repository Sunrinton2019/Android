package com.kmj.sunrinton19;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    String imageurl="";
    int star=0;
    Spinner spinner;
    ImageView star1,star2,star3,star4,star5;
    EditText input;
    boolean ischeck=false;
    Switch mSwitch;
    String subjectname="";
    ArrayList<String> subject;
    ImageView imageView;
    TextView addbtn;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        subject=new ArrayList<>();
        imageView=findViewById(R.id.add_imageView);
        input=findViewById(R.id.add_input);
        mSwitch=findViewById(R.id.switch1);
        star1=findViewById(R.id.add_star1);
        star2=findViewById(R.id.add_star2);
        star3=findViewById(R.id.add_star3);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ischeck=isChecked;
            }
        });
        addbtn=findViewById(R.id.add_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                intent.putExtra("ImageUrl",imageurl);
                intent.putExtra("star",star);
                intent.putExtra("name",input.getText().toString());
                intent.putExtra("subject",subjectname);
                if(ischeck){
                    setAlarm(input.getText().toString());
                }
                setResult(1234,intent);
                finish();
            }
        });
        star4=findViewById(R.id.add_star4);
        star5=findViewById(R.id.add_star5);
        spinner=findViewById(R.id.add_spinner);
        loadData();
        adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,subject);
        spinner.setAdapter(adapter);
        subjectname=subject.get(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectname=subject.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star=1;
                star1.setImageResource(R.drawable.ic_star);
                star2.setImageResource(R.drawable.ic_nolightstart);
                star3.setImageResource(R.drawable.ic_nolightstart);
                star4.setImageResource(R.drawable.ic_nolightstart);
                star5.setImageResource(R.drawable.ic_nolightstart);
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star=2;
                star1.setImageResource(R.drawable.ic_star);
                star2.setImageResource(R.drawable.ic_star);
                star3.setImageResource(R.drawable.ic_nolightstart);
                star4.setImageResource(R.drawable.ic_nolightstart);
                star5.setImageResource(R.drawable.ic_nolightstart);
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star=3;
                star1.setImageResource(R.drawable.ic_star);
                star2.setImageResource(R.drawable.ic_star);
                star3.setImageResource(R.drawable.ic_star);
                star4.setImageResource(R.drawable.ic_nolightstart);
                star5.setImageResource(R.drawable.ic_nolightstart);
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star=4;
                star1.setImageResource(R.drawable.ic_star);
                star2.setImageResource(R.drawable.ic_star);
                star3.setImageResource(R.drawable.ic_star);
                star4.setImageResource(R.drawable.ic_star);
                star5.setImageResource(R.drawable.ic_nolightstart);
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star=5;
                star1.setImageResource(R.drawable.ic_star);
                star2.setImageResource(R.drawable.ic_star);
                star3.setImageResource(R.drawable.ic_star);
                star4.setImageResource(R.drawable.ic_star);
                star5.setImageResource(R.drawable.ic_star);
            }
        });


        Intent intent=getIntent();
        imageurl=intent.getStringExtra("url");
        Log.e("url",imageurl);
        Uri uri = Uri.parse(imageurl);
        Glide.with(this)
                .load(imageurl)
                .into(imageView);


    }
    public void loadData() {
        Gson gson = new Gson();
        SharedPreferences pref =getSharedPreferences("pref", MODE_PRIVATE);
        String json = pref.getString("data", "");
        ArrayList<String> shareditems;
        shareditems = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
        }.getType());
        if (shareditems != null) {
            subject.addAll(shareditems);
        }
    }

    void setAlarm(String name){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(AddActivity.this,MyService.class);
        intent.putExtra("name",name);

        PendingIntent pendingIntent = PendingIntent.getService(this, 1000,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, 5000, pendingIntent);
    }
}
