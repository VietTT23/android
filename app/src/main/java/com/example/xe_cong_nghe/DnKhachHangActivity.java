package com.example.xe_cong_nghe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DnKhachHangActivity extends AppCompatActivity {

    TextView txt_qmk_kh;
    Button btn_dk_kh, btn_dn_kh;
    EditText edt_email_kh, edt_mk_kh;
    FirebaseAuth fAuth;


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

        btn_dn_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email_kh.getText().toString();
                String matkhau = edt_mk_kh.getText().toString();
                fAuth.signInWithEmailAndPassword(email, matkhau).addOnCompleteListener(DnKhachHangActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(DnKhachHangActivity.this, "Lỗi đăng nhập!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent_khach_hang = new Intent(DnKhachHangActivity.this, KhachHangActivity.class);
                            startActivity(intent_khach_hang);
                        }
                    }
                });


            }
        });
    }
}