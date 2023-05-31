package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.adapter.AttendanceAdapter;
import com.stc.attendance.adapter.UserApdater;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.TaiKhoan;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewUserActivity extends AppCompatActivity {

    private RecyclerView rcvUser;
    private UserApdater adapter;
    private List<TaiKhoan> mlistUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_user);

        rcvUser = findViewById(R.id.rcv_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(dividerItemDecoration);

        mlistUser = new ArrayList<>();
        getListUsers();
    }

    private void getListUsers()
    {
        ApiService.apiService.getAllUsers().enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                mlistUser = response.body();
                adapter = new UserApdater(mlistUser);

                rcvUser.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {
                Toast.makeText(RecyclerviewUserActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}