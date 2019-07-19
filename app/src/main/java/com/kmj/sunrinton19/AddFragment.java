package com.kmj.sunrinton19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class AddFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    MainActivity mainActivity;
    ArrayList<String> subject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        mainActivity = (MainActivity) getActivity();
        subject=new ArrayList<>();
        subject.add("수학");
        subject.add("영어");
        saveData();
        floatingActionButton = v.findViewById(R.id.main_addbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            sendTakePhotoIntent();
                mainActivity.sendTakePhotoIntent();

            }
        });

        return v;
    }

//    private void sendTakePhotoIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(mainActivity.getPackageManager()) != null) {
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//            }
//
//
//            if (photoFile != null) {
//                photoUri = FileProvider.getUriForFile(mainActivity, "com.kmj.sunrinton19", photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "TEST_" + timeStamp + "_";
//        File storageDir = mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,      /* prefix */
//                ".jpg",         /* suffix */
//                storageDir          /* directory */
//        );
//        imageFilePath = image.getAbsolutePath();
//        return image;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.e("path", imageFilePath);
//    }
public void saveData() {
    SharedPreferences pref = mainActivity.getSharedPreferences("pref", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    String json = new Gson().toJson(subject);
    editor.putString("data", json);
    editor.commit();
}


}
