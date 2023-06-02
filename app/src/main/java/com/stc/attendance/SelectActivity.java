package com.stc.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.atharvakale.facerecognition.R;

public class SelectActivity extends AppCompatActivity {

    private Button btnDay, btnMonth, btnYear, btnSarary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select);
        btnDay = findViewById(R.id.tk_btn_day);
        btnMonth = findViewById(R.id.tk_btn_month);

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectActivity.this, StatisticActivity.class);

                startActivity(intent);
            }
        });

        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, StatisticMonthActivity.class);

                startActivity(intent);
            }
        });

    }
}