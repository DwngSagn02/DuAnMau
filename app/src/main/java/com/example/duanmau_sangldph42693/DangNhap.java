package com.example.duanmau_sangldph42693;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.dao.thuthuDao;
import com.example.duanmau_sangldph42693.model.thuthu;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    private TextInputEditText txt_TenDN, txt_MatKhau;
    Button btn_DangNhap,btn_Huy;
    thuthuDao ttDao ;
    CheckBox chk_NhoMK;
    String strUser,strPass, strLevel, strName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btn_DangNhap = findViewById(R.id.btn_DangNhap);
        btn_Huy = findViewById(R.id.btn_Huy);
        txt_TenDN = findViewById(R.id.txt_TenDN);
        txt_MatKhau = findViewById(R.id.txt_MatKhau);
        chk_NhoMK = findViewById(R.id.chk_NhoMK);
        ttDao = new thuthuDao(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean rem = pref.getBoolean("REMEMBER",false);


        txt_TenDN.setText(user);
        txt_MatKhau.setText(pass);
        chk_NhoMK.setChecked(rem);

        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_TenDN.setText("");
                txt_MatKhau.setText("");
            }
        });
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }
    public void rememberUser(String n, String l, String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            // xoa trang thai luu truoc do
            edit.clear();
        }else {
            edit.putString("name",n);
            edit.putString("level",l);
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        // luu lai toan bo du lieu
        edit.commit();
    }
    public void checkLogin(){
        strUser = txt_TenDN.getText().toString();
        strPass = txt_MatKhau.getText().toString();
        strLevel = ttDao.getLevel(strUser);
        strName = ttDao.getName(strUser);

        if (strUser.trim().isEmpty() || strPass.trim().isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            if (ttDao.checkLogin(strUser,strPass)){
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strName,strLevel,strUser,strPass,chk_NhoMK.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}