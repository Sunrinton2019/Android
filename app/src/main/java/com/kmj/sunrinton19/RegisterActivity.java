package com.kmj.sunrinton19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText input;
    TextView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input = findViewById(R.id.register_input);
        btn = findViewById(R.id.register_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=input.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력하시고 눌러주세요", Toast.LENGTH_SHORT).show();
                } else {
                    savePreferences(name);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }, 1000);
                }
            }
        });
    }

    private void savePreferences(String name) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("myName",name);
        editor.commit();
    }
}
