package com.example.xe_cong_nghe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TtKhachHangActivity extends AppCompatActivity {

    EditText name_kh, phone_kh;
    Button confirm_kh, back_kh;
    //ImageView profileImage_kh;
    FirebaseAuth fAuth;
    DatabaseReference kh_db;

    String userID;
    String name;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt_khach_hang);

        name_kh = (EditText) findViewById(R.id.name_kh);
        phone_kh = (EditText) findViewById(R.id.phone_kh);
        //profileImage_kh = (ImageView) findViewById(R.id.profileImage_kh);
        confirm_kh = (Button) findViewById(R.id.confirm_kh);
        back_kh = (Button) findViewById(R.id.back_kh);

        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        kh_db = FirebaseDatabase.getInstance().getReference().child("user").child("khach_hang").child(userID);

        getUserInfo();

        confirm_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        back_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }

    private void saveUserInformation() {
        name = name_kh.getText().toString();
        phone = phone_kh.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("hoten", name);
        userInfo.put("sdt", phone);

        kh_db.updateChildren(userInfo);
    }

    private void getUserInfo() {
        kh_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("hoten")!=null){
                        name = map.get("hoten").toString();
                        name_kh.setText(name);
                    }
                    if(map.get("sdt")!=null){
                        phone = map.get("sdt").toString();
                        phone_kh.setText(phone);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}