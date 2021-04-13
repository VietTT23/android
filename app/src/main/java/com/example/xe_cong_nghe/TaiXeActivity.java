package com.example.xe_cong_nghe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TaiXeActivity extends AppCompatActivity {

    Button btn_ls_tx, btn_tt_tx, btn_map_tx, btn_dang_xuat_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_xe);

        btn_ls_tx = (Button) findViewById(R.id.btn_ls_tx);
        btn_tt_tx = (Button) findViewById(R.id.btn_tt_tx);
        btn_map_tx = (Button) findViewById(R.id.btn_map_tx);
        btn_dang_xuat_tx = (Button) findViewById(R.id.btn_dang_xuat_tx);

        btn_dang_xuat_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(TaiXeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });

        btn_tt_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaiXeActivity.this, TtTaiXeActivity.class);
                startActivity(i);
                return;
            }
        });

        btn_map_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaiXeActivity.this, MapsTaiXeActivity.class);
                startActivity(i);
                return;
            }
        });
    }
}