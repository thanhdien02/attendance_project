package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.adapter.AttendanceAdapter;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.Luong;
import com.stc.attendance.model.TaiKhoan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaryCalculationActivity extends AppCompatActivity {

    String[] items =  {"Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
    String[] items1 =  {"Năm 2022","Năm 2023", "Năm 2024", "Năm 2025"};
    AutoCompleteTextView autoCompleteTxt, autoCompleteTxt1;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems1;
    private TextView maNV, tenNv, luong, soNgay, tongluong;
    private Button btnLuong;
    int soLuong;
    private List<ChamCong> mlistAtt = new ArrayList<>();
    TaiKhoan taiKhoan = new TaiKhoan();

    String m = "", y="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_calculation);

        maNV = findViewById(R.id.tl_manv);
        tenNv = findViewById(R.id.tl_ten);
        luong = findViewById(R.id.pf_luong);
        soNgay = findViewById(R.id.pf_ngayluong);
        btnLuong = findViewById(R.id.tl_btn_luong);
        tongluong = findViewById(R.id.pf_luongtong);

        Bundle bundleReceive = getIntent().getExtras();

        taiKhoan =  (TaiKhoan) bundleReceive.get("taikhoan");
        maNV.setText(taiKhoan.getMaNhanVien());
        tenNv.setText(taiKhoan.getName());
        luong.setText(taiKhoan.getLuong().toString());

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.activity_item_month,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt1 = findViewById(R.id.auto_complete_txt1);
        adapterItems1 = new ArrayAdapter<String>(this,R.layout.activity_item_month,items1);
        autoCompleteTxt1.setAdapter(adapterItems1);



        autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                switch (position)
                {
                    case 0:
                        y = "2022";
                        break;
                    case 1:
                        y = "2023";
                        break;
                    case 2:
                        y = "2024";
                        break;
                    case 3:
                        y = "2024";
                        break;
                    case 4:
                        y = "2025";
                        break;

                }
            }
        });
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (position)
                {
                    case 0:
                        m = "1";
                        break;
                    case 1:
                        m = "2";
                        break;
                    case 2:
                        m = "3";
                        break;
                    case 3:
                        m = "4";
                        break;
                    case 4:
                        m = "5";
                        break;
                    case 5:
                        m = "6";
                        break;
                    case 6:
                        m = "7";
                        break;
                    case 7:
                        m = "8";
                        break;
                    case 8:
                        m = "9";
                        break;
                    case 9:
                        m = "10";
                        break;
                    case 10:
                        m = "11";
                        break;
                    case 11:
                        m = "12";
                        break;

                }
            }
        });



        btnLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Luong luong = new Luong();

                luong.setMaNhanVien(taiKhoan.getMaNhanVien());
                luong.setThang(Integer.parseInt(m));
                luong.setTenNhanVien(taiKhoan.getName());
                luong.setNam(Integer.parseInt(y));

                luong.setTongLuong(taiKhoan.getLuong());

                ApiService.apiService.createSalary(luong)
                        .enqueue(new Callback<Luong>() {
                            @Override
                            public void onResponse(Call<Luong> call, Response<Luong> response) {
                                try {
                                    Luong luong1 = response.body();
                                    Intent intent = new Intent(SalaryCalculationActivity.this, PayrollActivity.class);

                                    Toast.makeText(SalaryCalculationActivity.this, luong1.getTenNhanVien() + " \n" +
                                            "Thêm thông tin thành công", Toast.LENGTH_SHORT).show();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("Luong", (Serializable) luong1);

                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }catch (Exception e)
                                {
                                    Toast.makeText(SalaryCalculationActivity.this, "Nhân viên này đã tính lương trong tháng rôi!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Luong> call, Throwable t) {
                                Toast.makeText(SalaryCalculationActivity.this, "Không thành công rồi !!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}