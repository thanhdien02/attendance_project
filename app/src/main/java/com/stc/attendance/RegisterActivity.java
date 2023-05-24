package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, sdt, hoTen, maNV, matKhau, xnMatKhau;
    private Button btndk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.enter_email);
        hoTen = findViewById(R.id.enter_name);
        sdt = findViewById(R.id.enter_sdt);
        maNV = findViewById(R.id.enter_manv);
        matKhau = findViewById(R.id.enter_password);
        xnMatKhau = findViewById(R.id.enter_cpassword);
        btndk = findViewById(R.id.btn_dk);


        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnclickRegister();
            }
        });
    }

    private void OnclickRegister(){
        String strEmail = email.getText().toString().trim();
        String strPassword = matKhau.getText().toString().trim();
        String cfstrPassword = xnMatKhau.getText().toString().trim();
        String sodienthoai = sdt.getText().toString().trim();
        String manhanvien = maNV.getText().toString().trim();
        String ten = hoTen.getText().toString().trim();

        if(strEmail == null && strPassword == null && cfstrPassword == null && sodienthoai == null && manhanvien == null)
        {
            Toast.makeText(this, "Các trường không được bỏ trống", Toast.LENGTH_SHORT).show();
            return;

        }

        if (!strPassword.equals(cfstrPassword))
        {
            Toast.makeText(this, "Không khẩu chưa trùng nhau", Toast.LENGTH_SHORT).show();
            return;
        }
        if(strPassword.length() < 6)
        {
            Toast.makeText(this, "Mật khẩu không được dưới 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setDienThoai(sodienthoai);
        taiKhoan.setEmail(strEmail);
        taiKhoan.setName(ten);
        taiKhoan.setMaNhanVien(manhanvien);
        taiKhoan.setPassword(strPassword);
        List<String> r = new ArrayList<>();
        r.add("ROLE_USER");
        taiKhoan.setRoles(r);

        ApiService.apiService.createUser(taiKhoan)
                .enqueue(new Callback<TaiKhoan>() {
                    @Override
                    public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                        TaiKhoan taiKhoan1 = response.body();
//                        Toast.makeText(getApplicationContext(), user1.getEmail() + " " + user1.getPassword(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), taiKhoan1.getName() + " \n" +
                                "Thêm thông tin nhận diện thành công", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<TaiKhoan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });
    }
}