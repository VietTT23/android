package com.example.xe_cong_nghe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DkTaiXeActivity extends AppCompatActivity {

    TextView txt_dntx;
    EditText edt_email_dktx, edt_ten_dktx, edt_sdt_dktx, edt_mk_dktx, edt_xnmk_dktx;
    Button btn_dktx;
    ProgressBar loading;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dk_tai_xe);

        edt_xnmk_dktx = (EditText) findViewById(R.id.edt_xnmk_dktx);
        edt_mk_dktx = (EditText) findViewById(R.id.edt_mk_dktx);
        edt_sdt_dktx = (EditText) findViewById(R.id.edt_sdt_dktx);
        edt_ten_dktx = (EditText) findViewById(R.id.edt_ten_dktx);
        edt_email_dktx = (EditText) findViewById(R.id.edt_email_dktx);
        btn_dktx = (Button) findViewById(R.id.btn_dktx);
        txt_dntx = (TextView) findViewById(R.id.txt_dntx);
        loading = (ProgressBar) findViewById(R.id.loading);

        fAuth = FirebaseAuth.getInstance();

        txt_dntx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dn_tx = new Intent(DkTaiXeActivity.this, DnTaiXeActivity.class);
                startActivity(intent_dn_tx);
            }
        });

        btn_dktx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //extract the data from the form

                String hoten = edt_ten_dktx.getText().toString();
                String email = edt_email_dktx.getText().toString().trim();
                String sdt = edt_sdt_dktx.getText().toString();
                String matkhau = edt_mk_dktx.getText().toString().trim();
                String xnmatkhau = edt_xnmk_dktx.getText().toString().trim();

                if(TextUtils.isEmpty(hoten)){
                    edt_ten_dktx.setError("Bạn chưa nhập họ tên");
                    return;
                }

                if(TextUtils.isEmpty(sdt)){
                    edt_sdt_dktx.setError("Bạn chưa nhập SĐT");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    edt_email_dktx.setError("Bạn chưa nhập email");
                    return;
                }

                if(TextUtils.isEmpty(matkhau)){
                    edt_mk_dktx.setError("Bạn chưa nhập mật khẩu");
                    return;
                }

                if(TextUtils.isEmpty(xnmatkhau)){
                    edt_xnmk_dktx.setError("Bạn chưa xác nhận mật khẩu");
                }

                if(!matkhau.equals(xnmatkhau)){
                    edt_xnmk_dktx.setError("Mật khẩu không trùng khớp");
                    return;
                }

                loading.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, matkhau).addOnCompleteListener(DkTaiXeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(DkTaiXeActivity.this, "Lỗi đăng kí!", Toast.LENGTH_SHORT).show();
                        }else {
                            String user_id = fAuth.getCurrentUser().getUid();
                            UserAcc userAcc = new UserAcc(hoten, email, sdt ,matkhau);
                            DatabaseReference add_user_db = FirebaseDatabase.getInstance().getReference().child("user").child("tai_xe").child(user_id);
                            add_user_db.setValue(userAcc);
                            Intent intent_dn_tx = new Intent(DkTaiXeActivity.this, DnKhachHangActivity.class);
                            startActivity(intent_dn_tx);
                            Toast.makeText(DkTaiXeActivity.this, "Đăng kí thành công! Bạn có thể đăng nhập ngay bây giờ!", Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}