package com.kmj.sunrinton19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class AddFragment extends Fragment {
    TextView myname;
    FloatingActionButton floatingActionButton;
    MainActivity mainActivity;
    ArrayList<String> subject;
    RecyclerView recyclerView;
    String myName;

    LinearLayoutManager layoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        mainActivity = (MainActivity) getActivity();
        SharedPreferences pref = mainActivity.getSharedPreferences("pref", MODE_PRIVATE);
        myName=pref.getString("myName", "");
        recyclerView=v.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        myname=v.findViewById(R.id.fadd_info);
        myname.setText(myName+"님 안녕하세요!");




        subject = new ArrayList<>();
        subject.add("수학");
        subject.add("영어");
        saveData();
        SubjectAdapter adapter=new SubjectAdapter(subject,mainActivity);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mainActivity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Intent intent=new Intent(mainActivity,ProblemActivity.class);
                intent.putExtra("subject",subject.get(position));
                startActivity(intent);
                Toast.makeText(mainActivity, subject.get(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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

    public void loadData() {
        Gson gson = new Gson();
        SharedPreferences pref = mainActivity.getSharedPreferences("pref", MODE_PRIVATE);
        String json = pref.getString("data", "");
        ArrayList<String> shareditems;
        shareditems = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
        }.getType());
        if (shareditems != null) {
            subject.addAll(shareditems);
        }
    }

}
