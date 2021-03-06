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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DkKhachHangActivity extends AppCompatActivity {

    TextView txt_dnkh;
    EditText edt_email_dkkh, edt_ten_dkkh, edt_sdt_dkkh, edt_mk_dkkh, edt_xnmk_dkkh;
    Button btn_dkkh;
    ProgressBar loading;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dk_khach_hang);

        edt_xnmk_dkkh = (EditText) findViewById(R.id.edt_xnmk_dkkh);
        edt_mk_dkkh = (EditText) findViewById(R.id.edt_mk_dkkh);
        edt_sdt_dkkh = (EditText) findViewById(R.id.edt_sdt_dkkh);
        edt_ten_dkkh = (EditText) findViewById(R.id.edt_ten_dkkh);
        edt_email_dkkh = (EditText) findViewById(R.id.edt_email_dkkh);
        btn_dkkh = (Button) findViewById(R.id.btn_dkkh);
        txt_dnkh = (TextView) findViewById(R.id.txt_dnkh);
        loading = (ProgressBar) findViewById(R.id.loading);

        fAuth = FirebaseAuth.getInstance();

        txt_dnkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_dn_kh = new Intent(DkKhachHangActivity.this, DnKhachHangActivity.class);
                startActivity(intent_dn_kh);
            }
        });

        btn_dkkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoten = edt_ten_dkkh.getText().toString();
                String sdt = edt_sdt_dkkh.getText().toString();
                String email = edt_email_dkkh.getText().toString().trim();
                String matkhau = edt_mk_dkkh.getText().toString().trim();
                String xnmatkhau = edt_xnmk_dkkh.getText().toString().trim();

                if(TextUtils.isEmpty(hoten)){
                    edt_ten_dkkh.setError("B???n ch??a nh???p h??? t??n");
                    return;
                }

                if(TextUtils.isEmpty(sdt)){
                    edt_sdt_dkkh.setError("B???n ch??a nh???p S??T");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    edt_email_dkkh.setError("B???n ch??a nh???p email");
                    return;
                }

                if(TextUtils.isEmpty(matkhau)){
                    edt_mk_dkkh.setError("B???n ch??a nh???p m???t kh???u");
                    return;
                }

                if(TextUtils.isEmpty(xnmatkhau)){
                    edt_xnmk_dkkh.setError("B???n ch??a x??c nh???n m???t kh???u");
                    return;
                }

                if(!matkhau.equals(xnmatkhau)){
                    edt_xnmk_dkkh.setError("M???t kh???u kh??ng tr??ng kh???p");
                    return;
                }

                loading.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, matkhau).addOnCompleteListener(DkKhachHangActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(DkKhachHangActivity.this, "L???i ????ng k??!", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.INVISIBLE);
                        }else {
                            String user_id = fAuth.getCurrentUser().getUid();
                            DatabaseReference add_user_db = FirebaseDatabase.getInstance().getReference().child("user").child("khach_hang").child(user_id);
                            UserAcc user = new UserAcc(hoten, email, sdt ,matkhau);
                            add_user_db.setValue(user);
                            Intent intent_dn_kh = new Intent(DkKhachHangActivity.this, DnKhachHangActivity.class);
                            startActivity(intent_dn_kh);
                            Toast.makeText(DkKhachHangActivity.this, "????ng k?? th??nh c??ng! B???n c?? th??? ????ng nh???p ngay b??y gi???", Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}