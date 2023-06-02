package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.adapter.AttendanceAdapter;
import com.stc.attendance.api.ApiService;
import com.stc.attendance.model.ChamCong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticMonthActivity extends AppCompatActivity {

    String[] items =  {"Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;


    private RecyclerView rcvThongKeThang;
    private AttendanceAdapter adapter;
    private List<ChamCong> mlistAtt = new ArrayList<>();

    private TextView txtThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_month);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);


        rcvThongKeThang = findViewById(R.id.tk_thang);
        txtThang = findViewById(R.id.tk_tongthang);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvThongKeThang.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvThongKeThang.addItemDecoration(dividerItemDecoration);

        mlistAtt = new ArrayList<>();


        //
        adapterItems = new ArrayAdapter<String>(this,R.layout.activity_item_month,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
                String m = "";
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
                getListAttendance("1", m, "2023");
            }
        });

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
                    Toast.makeText(StatisticMonthActivity.this,  arr[0].toString() + "  - " + arr[1].toString(), Toast.LENGTH_SHORT).show();
                    String nam = arr[2].substring(0, 4);
                    if(m1 == m2)
                    {
                        ls.add(mlistAtt.get(i));
                    }
                }
                int tong = ls.size();
                txtThang.setText("Tổng: " + String.valueOf(tong));
                adapter = new AttendanceAdapter(ls);
                rcvThongKeThang.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChamCong>> call, Throwable t) {
                Toast.makeText(StatisticMonthActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}