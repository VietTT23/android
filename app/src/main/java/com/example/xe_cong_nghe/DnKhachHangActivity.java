package com.example.xe_cong_nghe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DnKhachHangActivity extends AppCompatActivity {

    TextView txt_qmk_kh;
    Button btn_dk_kh, btn_dn_kh;
    EditText edt_email_kh, edt_mk_kh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn_khach_hang);

        txt_qmk_kh = (TextView) findViewById(R.id.txt_qmk_kh);
        edt_email_kh = (EditText) findViewById(R.id.edt_email_kh);
        edt_mk_kh = (EditText) findViewById(R.id.edt_mk_kh);
        btn_dn_kh = (Button) findViewById(R.id.btn_dn_kh);
        btn_dk_kh = (Button) findViewById(R.id.btn_dk_kh);


        btn_dk_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dk_khach_hang = new Intent(DnKhachHangActivity.this, DkKhachHangActivity.class);
                startActivity(intent_dk_khach_hang);
            }
        });
    }
}