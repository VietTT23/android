package com.example.xe_cong_nghe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class KhachHangActivity extends AppCompatActivity {

    Button btn_ls_kh, btn_tt_kh, btn_map_kh, btn_dang_xuat_kh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        //btn_ls_kh = (Button) findViewById(R.id.btn_ls_kh);
        btn_tt_kh = (Button) findViewById(R.id.btn_tt_kh);
        btn_map_kh = (Button) findViewById(R.id.btn_map_kh);
        btn_dang_xuat_kh = (Button) findViewById(R.id.btn_dang_xuat_kh);

        btn_dang_xuat_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(KhachHangActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });

        btn_tt_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KhachHangActivity.this, TtKhachHangActivity.class);
                startActivity(i);
                return;
            }
        });

        btn_map_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KhachHangActivity.this, MapsKhachHangActivity2.class);
                startActivity(i);
                return;
            }
        });
    }
}