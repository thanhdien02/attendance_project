package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;

    private TextView textViewSignup;
    private List<TaiKhoan> mListUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_button);
        edtEmail = findViewById(R.id.enter_email);
        edtPassword = findViewById(R.id.enter_password);


        getListUser();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }
    private void clickLogin() {
        String strUsername = edtEmail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();


        if (mListUser == null || mListUser.isEmpty()) {
            return;
        }


        boolean isHasUser = false;
        TaiKhoan loginUser = new TaiKhoan(); // Tạo một biến để lưu thông tin user đăng nhập thành công
        for(TaiKhoan user:mListUser){
            if(strUsername.equals(user.getEmail()) && strPassword.equals(user.getPassword())){
                isHasUser = true;

                loginUser = user; // Lưu thông tin user đăng nhập thành công vào biến loginUser

                break;
            }
        }

        if(isHasUser){
            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show(); // Hiển thị thông báo về id đã lấy được

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(LoginActivity.this, "Email or Password invalid" + mListUser.get(0).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getListUser()
    {
        ApiService.apiService.getAllUsers().enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                mListUser = response.body();

                Log.e("List user: ", mListUser.size() + "");
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}