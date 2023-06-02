package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.adapter.UserApdater;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.Luong;
import com.stc.attendance.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayrollActivity extends AppCompatActivity {

    private TextView txtMa, txtTen, txtThang, txtSoNgayLam, txtTongLuong;

    private List<Luong> mlistLuong = new ArrayList<>();
    private Button btnBack;
    Luong l = new Luong();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);

        txtMa = findViewById(R.id.bl_manv);
        txtTen = findViewById(R.id.bl_tennv);
        txtThang = findViewById(R.id.bl_thang);
        txtSoNgayLam = findViewById(R.id.bl_songaylam);
        txtTongLuong = findViewById(R.id.bl_tongluong);

        btnBack = findViewById(R.id.bl_back);

        Bundle bundleReceive = getIntent().getExtras();


        l =  (Luong) bundleReceive.get("Luong");
        txtTen.setText(l.getTenNhanVien());
        txtMa.setText(l.getMaNhanVien());
        txtThang.setText("Lương của tháng: " + String.valueOf(l.getThang()));
        getSetLuong();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayrollActivity.this, ManagerActivity.class);

                startActivity(intent);
            }
        });
    }

    private void getSetLuong()
    {
        ApiService.apiService.getAllSalary().enqueue(new Callback<List<Luong>>() {
            @Override
            public void onResponse(Call<List<Luong>> call, Response<List<Luong>> response) {
                mlistLuong = response.body();

                for (int i = 0; i < mlistLuong.size(); i++)
                {
                    if(l.getMaNhanVien().equals(mlistLuong.get(i).getMaNhanVien()) && l.getThang() == mlistLuong.get(i).getThang())
                    {
                        txtTongLuong.setText("Lương tháng của NV: "+mlistLuong.get(i).getTongLuong().toString());
                        txtSoNgayLam.setText("Số ngày làm của tháng: "+String.valueOf(mlistLuong.get(i).getSoNgayLamTrongThang()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Luong>> call, Throwable t) {
                Toast.makeText(PayrollActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}