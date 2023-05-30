package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.atharvakale.facerecognition.R;
public class ManagerActivity extends AppCompatActivity {

    private LinearLayout themNhanVien, danhSachNhanVien, lichSu, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        themNhanVien = findViewById(R.id.ql_themnhanvien);
        danhSachNhanVien = findViewById(R.id.ql_ds_nhanvien);
        lichSu = findViewById(R.id.ql_lichsu);
        home = findViewById(R.id.ql_back_home);
        danhSachNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerActivity.this, RecyclerviewUserActivity.class);

                startActivity(intent);
            }
        });

        themNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });

        lichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, RecyclerviewAttendanceActivity.class);

                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, SelectActivity.class);

                startActivity(intent);
            }
        });
    }
}