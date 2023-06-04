package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.atharvakale.facerecognition.R;

public class HomeActivity extends AppCompatActivity {

    LinearLayout Add_Emp, Att, Ls, thongke;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Add_Emp = findViewById(R.id.add_employee);

        Att = findViewById(R.id.h_ad);

        Ls = findViewById(R.id.h_ls);
        Add_Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });
        Att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AttendanceActivity.class);

                startActivity(intent);
            }
        });
        Ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RecyclerviewAttendanceActivity.class);

                startActivity(intent);
            }
        });
    }
}