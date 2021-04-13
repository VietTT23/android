package com.example.xe_cong_nghe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class TtTaiXeActivity extends AppCompatActivity {

    EditText name_tx, phone_tx;
    Button confirm_tx, back_tx;
    //ImageView profileImage_tx;
    FirebaseAuth fAuth;
    DatabaseReference tx_db;

    String userID;
    String name;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt_tai_xe);

        name_tx = (EditText) findViewById(R.id.name_tx);
        phone_tx = (EditText) findViewById(R.id.phone_tx);
        //profileImage_kh = (ImageView) findViewById(R.id.profileImage_kh);
        confirm_tx = (Button) findViewById(R.id.confirm_tx);
        back_tx = (Button) findViewById(R.id.back_tx);

        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        tx_db = FirebaseDatabase.getInstance().getReference().child("user").child("tai_xe").child(userID);

        getUserInfo();

        confirm_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        back_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }

    private void getUserInfo() {
        tx_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("hoten")!=null){
                        name = map.get("hoten").toString();
                        name_tx.setText(name);
                    }
                    if(map.get("sdt")!=null){
                        phone = map.get("sdt").toString();
                        phone_tx.setText(phone);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void saveUserInformation() {
        name = name_tx.getText().toString();
        phone = phone_tx.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("hoten", name);
        userInfo.put("sdt", phone);

        tx_db.updateChildren(userInfo);
    }
}