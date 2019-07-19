package com.kmj.sunrinton19;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fragment> fragments;
    ImageView iv1,iv2;
    FragmentUtils fragmentUtils;
    TextView bottom_tv1,bottom_tv2;
    AddFragment addFragment;
    RoomFragment roomFragment;
    ConstraintLayout ic1, ic2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ic1 = findViewById(R.id.main_bottom1);
        ic2 = findViewById(R.id.main_bottom2);
        iv1=findViewById(R.id.main_bottom_ic1);
        iv2=findViewById(R.id.main_bottom_ic2);
        bottom_tv1=findViewById(R.id.main_bottom_tv1);
        bottom_tv2=findViewById(R.id.main_bottom_tv2);

        addFragment = new AddFragment();
        roomFragment = new RoomFragment();
        fragments = new ArrayList<>();
        fragments.add(addFragment);
        fragments.add(roomFragment);

        fragmentUtils = new FragmentUtils(R.id.framelayout, fragments);
        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 0, new Bundle());


        ic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ic1", "asdf");
                bottom_tv1.setTextColor((Color.parseColor("#f59040")));
                bottom_tv2.setTextColor(Color.parseColor("#8e8e93"));
                iv1.setImageResource(R.drawable.ic_star);
                iv2.setImageResource(R.drawable.ic_bottom_ic2);
                fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 0, new Bundle());

            }
        });
        ic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ic2", "asdf");
                bottom_tv1.setTextColor(Color.parseColor("#8e8e93"));
                bottom_tv2.setTextColor((Color.parseColor("#f59040")));
                iv1.setImageResource(R.drawable.ic_nolightstart);
                iv2.setImageResource(R.drawable.ic_bottom2_ic2_light);
                fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 1, new Bundle());

            }
        });


    }
}
