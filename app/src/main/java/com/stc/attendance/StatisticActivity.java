package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.adapter.AttendanceAdapter;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.ChamCong;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticActivity extends AppCompatActivity {

    private RecyclerView rcvThongKe;
    private AttendanceAdapter adapter;
    private List<ChamCong> mlistAtt = new ArrayList<>();


    private TextView textView, tongNgay;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        rcvThongKe = findViewById(R.id.tk_ngay);
        tongNgay =findViewById(R.id.tk_tongngay);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvThongKe.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvThongKe.addItemDecoration(dividerItemDecoration);

        mlistAtt = new ArrayList<>();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker(); // Open date picker dialog

            }
        });
    }

    private void openDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                //Showing the picked value in the textView
                textView.setText(String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day));
                String y = String.valueOf(year);
                String d = String.valueOf(day);
                String m = String.valueOf(month + 1);

                getListAttendance(d, m, y);

            }
        }, 2023, 04, 30);

        datePickerDialog.show();
    }

    private void getListAttendance(String day, String month, String year)
    {
        ApiService.apiService.getAllChamCongs().enqueue(new Callback<List<ChamCong>>() {
            @Override
            public void onResponse(Call<List<ChamCong>> call, Response<List<ChamCong>> response) {
                mlistAtt = response.body();
                List<ChamCong> ls = new ArrayList<>();
                for (int i = 0; i < mlistAtt.size(); i++)
                {
                    String ngay = mlistAtt.get(i).getNgayChamCong();
                    int m1 = Integer.parseInt(month);
                    String[] arr = ngay.split("/");
                    int m2 = Integer.parseInt(arr[1]);
                    Toast.makeText(StatisticActivity.this,  arr[0].toString() + "  - " + arr[1].toString(), Toast.LENGTH_SHORT).show();
                    String nam = arr[2].substring(0, 4);

                    if(Integer.parseInt(arr[0]) == Integer.parseInt(day) && m1 == m2 && Integer.parseInt(nam) == Integer.parseInt(year))
                    {
                        ls.add(mlistAtt.get(i));
                    }
                }
                int tong = ls.size();
                tongNgay.setText("Tổng: " + String.valueOf(tong));
                adapter = new AttendanceAdapter(ls);
                rcvThongKe.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChamCong>> call, Throwable t) {
                Toast.makeText(StatisticActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}