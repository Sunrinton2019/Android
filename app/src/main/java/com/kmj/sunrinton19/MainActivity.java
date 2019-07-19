package com.kmj.sunrinton19;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fragment> fragments;
    ImageView iv1, iv2;
    FragmentUtils fragmentUtils;
    TextView bottom_tv1, bottom_tv2;
    AddFragment addFragment;
    RoomFragment roomFragment;
    ConstraintLayout ic1, ic2;

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    public static String imageFilePath;
    private Uri photoUri;

    private float aceVal;
    private float aceLast;
    private float shake;
    private int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ic1 = findViewById(R.id.main_bottom1);
        ic2 = findViewById(R.id.main_bottom2);
        iv1 = findViewById(R.id.main_bottom_ic1);
        iv2 = findViewById(R.id.main_bottom_ic2);
        bottom_tv1 = findViewById(R.id.main_bottom_tv1);
        bottom_tv2 = findViewById(R.id.main_bottom_tv2);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        300);
            }
        }


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

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    public String sendTakePhotoIntent() {
        File photoFile = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                Log.e("pacc", getPackageName());
                Log.e("photo", photoFile.getPath());

                photoUri = FileProvider.getUriForFile(this, "com.kmj.sunrinton19.provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
        return photoFile.getPath();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_IMAGE_CAPTURE){
            Intent intent=new Intent(MainActivity.this,AddActivity.class);
            intent.putExtra("url",imageFilePath);
            startActivityForResult(intent,321);

        }
        Log.e("result",String.valueOf(resultCode));
        if (resultCode==1234){
            int star=data.getIntExtra("start",0);
            String name=data.getStringExtra("name");
            String subject=data.getStringExtra("subject");
            String ImageUrl=data.getStringExtra("ImageUrl");
            Log.e("result",name);

        }
    }
}
