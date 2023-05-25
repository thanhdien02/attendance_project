package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.adapter.AttendanceAdapter;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.TaiKhoan;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewAttendanceActivity extends AppCompatActivity {

    private RecyclerView rcvAttendance;
    private AttendanceAdapter adapter;
    private List<ChamCong> mlistAtt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_attendance);


        rcvAttendance = findViewById(R.id.rcv_attendance);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAttendance.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAttendance.addItemDecoration(dividerItemDecoration);

        mlistAtt = new ArrayList<>();
        getListAttendance();

    }


    private void getListAttendance()
    {
        ApiService.apiService.getAllChamCongs().enqueue(new Callback<List<ChamCong>>() {
            @Override
            public void onResponse(Call<List<ChamCong>> call, Response<List<ChamCong>> response) {
                 mlistAtt = response.body();
                 adapter = new AttendanceAdapter(mlistAtt);
                 rcvAttendance.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChamCong>> call, Throwable t) {
                Toast.makeText(RecyclerviewAttendanceActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}