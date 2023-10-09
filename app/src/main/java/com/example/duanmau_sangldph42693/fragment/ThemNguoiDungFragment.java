package com.example.duanmau_sangldph42693.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.dao.thuthuDao;
import com.example.duanmau_sangldph42693.model.thuthu;
import com.google.android.material.textfield.TextInputEditText;

public class ThemNguoiDungFragment extends Fragment {
    public ThemNguoiDungFragment() {
        // Required empty public constructor
    }

    thuthuDao ttDao;
    thuthu tt;
    TextInputEditText txt_HoTen_tao,txt_TenDN_tao,txt_MatKhau_tao,txt_XNMK_tao;
    Button btn_Tao, btn_Huy;
    String tenDN,hoTen,matKhau,XNMK;
    ImageButton an,hien,an1,hien1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);
        btn_Huy = view.findViewById(R.id.btn_Huy);
        btn_Tao = view.findViewById(R.id.btn_Tao);
        txt_HoTen_tao = view.findViewById(R.id.txt_HoTen_tao);
        txt_TenDN_tao = view.findViewById(R.id.txt_TenDN_tao);
        txt_MatKhau_tao = view.findViewById(R.id.txt_MatKhau_tao);
        txt_XNMK_tao = view.findViewById(R.id.txt_XNMK_tao);
        hien = view.findViewById(R.id.imgHien);
        hien1 = view.findViewById(R.id.imgHien1);
        an = view.findViewById(R.id.imgAn);
        an1 = view.findViewById(R.id.imgAn1);


        ttDao = new thuthuDao(getContext());

        an.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hien.setVisibility(View.VISIBLE);
                an.setVisibility(View.GONE);
                txt_MatKhau_tao.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        an1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hien1.setVisibility(View.VISIBLE);
                an1.setVisibility(View.GONE);
                txt_XNMK_tao.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        hien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hien.setVisibility(View.GONE);
                an.setVisibility(View.VISIBLE);
                txt_MatKhau_tao.setTransformationMethod(null);
            }
        });

        hien1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hien1.setVisibility(View.GONE);
                an1.setVisibility(View.VISIBLE);
                txt_XNMK_tao.setTransformationMethod(null);
            }
        });


        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        btn_Tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenDN = txt_TenDN_tao.getText().toString().trim();
                hoTen = txt_HoTen_tao.getText().toString().trim();
                matKhau = txt_MatKhau_tao.getText().toString().trim();
                XNMK = txt_XNMK_tao.getText().toString().trim();

                if (tenDN.isEmpty() || hoTen.isEmpty() || matKhau.isEmpty()){
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (!matKhau.equalsIgnoreCase(XNMK)) {
                    Toast.makeText(getContext(), "Mật khẩu chưa khớp", Toast.LENGTH_SHORT).show();
                } else if (ttDao.checkTK(tenDN)) {
                    Toast.makeText(getContext(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                }else {
                    tt = new thuthu(tenDN,hoTen,matKhau,"Thủ Thư");
                    ttDao.insert(tt);
                    reset();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void reset (){
        txt_TenDN_tao.setText("");
        txt_HoTen_tao.setText("");
        txt_MatKhau_tao.setText("");
        txt_XNMK_tao.setText("");
    }
}