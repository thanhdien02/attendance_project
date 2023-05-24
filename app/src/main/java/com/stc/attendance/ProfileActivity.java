package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.model.ChamCong;

import java.io.Serializable;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtMa, txtTen, txtNgay;

    private Button btnExit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtMa = findViewById(R.id.txtMaNV);
        txtTen = findViewById(R.id.txttenNV);
        txtNgay = findViewById(R.id.pf_ngay);

        btnExit = findViewById(R.id.btn_exit);

        Bundle bundleReceive = getIntent().getExtras();

        ChamCong chamCong =  (ChamCong) bundleReceive.get("Info");

        txtMa.setText(chamCong.getMaNhanVien());
        txtTen.setText(chamCong.getTen());
        txtNgay.setText(chamCong.getNgayChamCong().toString());
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}