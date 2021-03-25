package com.example.xe_cong_nghe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_khach_hang, btn_tai_xe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tai_xe = (Button) findViewById(R.id.btn_tai_xe);
        btn_khach_hang = (Button) findViewById(R.id.btn_khach_hang);

        btn_khach_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_khach_hang = new Intent(MainActivity.this, DnKhachHangActivity.class);
                startActivity(intent_khach_hang);
                //finish();
                //return;
            }
        });

        btn_tai_xe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tai_xe = new Intent(MainActivity.this, DnTaiXeActivity.class);
                startActivity(intent_tai_xe);
                //finish();
                //return;
            }
        });
    }
}