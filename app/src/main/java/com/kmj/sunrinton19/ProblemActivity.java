package com.kmj.sunrinton19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ProblemActivity extends AppCompatActivity {
    ArrayList<ProblemModel> problems;
    ArrayList<ProblemModel> newlist;
    TextView textView;
    String subject="";
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        textView=findViewById(R.id.problem_info);
        recyclerView=findViewById(R.id.problemrecycler);

        Intent intent=getIntent();
        subject=intent.getStringExtra("subject");
        textView.setText(subject);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        problems = new ArrayList<>();
        loadData();
        newlist=new ArrayList<>();
        for(int i=0; i<problems.size(); i++){


            if(subject.equals(problems.get(i).getSubject())){
                Log.e("asdffdas",subject+":"+ problems.get(i));
                newlist.add(problems.get(i));
            }
        }
        ProblemAdapter adapter=new ProblemAdapter(newlist,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }

    public void loadData() {
        Gson gson = new Gson();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String json = pref.getString("problems", "");

        ArrayList<ProblemModel> shareditems;

        Log.e("asdf", json);
        shareditems = gson.fromJson(json, new TypeToken<ArrayList<ProblemModel>>() {
        }.getType());
        if (shareditems != null) {
            problems.addAll(shareditems);
        }
    }
}
