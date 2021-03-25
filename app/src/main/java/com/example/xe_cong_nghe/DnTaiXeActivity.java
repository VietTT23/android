package com.example.xe_cong_nghe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DnTaiXeActivity extends AppCompatActivity {

    TextView txt_qmk_tx;
    Button btn_dk_tx, btn_dn_tx;
    EditText edt_email_tx, edt_mk_tx;
    FirebaseAuth fAuth;

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

        btn_dn_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email_tx.getText().toString().trim();
                String matkhau = edt_mk_tx.getText().toString().trim();
                fAuth.signInWithEmailAndPassword(email, matkhau).addOnCompleteListener(DnTaiXeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(DnTaiXeActivity.this, "Lỗi đăng nhập!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent_tai_xe = new Intent(DnTaiXeActivity.this, TaiXeActivity.class);
                            startActivity(intent_tai_xe);
                        }
                    }
                });


            }
        });
    }
}