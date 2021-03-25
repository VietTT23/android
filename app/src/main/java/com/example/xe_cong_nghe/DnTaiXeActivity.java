package com.example.xe_cong_nghe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DnTaiXeActivity extends AppCompatActivity {

    TextView txt_qmk_tx;
    Button btn_dk_tx, btn_dn_tx;
    EditText edt_email_tx, edt_mk_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn_tai_xe);

        txt_qmk_tx = (TextView) findViewById(R.id.txt_qmk_tx);
        edt_email_tx = (EditText) findViewById(R.id.edt_email_tx);
        edt_mk_tx = (EditText) findViewById(R.id.edt_mk_tx);
        btn_dn_tx = (Button) findViewById(R.id.btn_dn_tx);
        btn_dk_tx = (Button) findViewById(R.id.btn_dk_tx);


        btn_dk_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dk_tai_xe = new Intent(DnTaiXeActivity.this, DkTaiXeActivity.class);
                startActivity(intent_dk_tai_xe);
            }
        });
    }
}