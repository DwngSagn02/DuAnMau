package com.example.duanmau_sangldph42693.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.DangNhap;
import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.dao.thuthuDao;
import com.example.duanmau_sangldph42693.model.thuthu;
import com.google.android.material.textfield.TextInputEditText;

public class DoiMkFragment extends Fragment {


    public DoiMkFragment() {
        // Required empty public constructor
    }

    thuthuDao ttDao;

    thuthu tt;

    TextInputEditText txt_MatKhau_Cu,txt_MatKhau_Moi,txt_XNMK_Moi;
    Button btn_Doi, btn_Huy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_doi_mk, container, false);
        txt_MatKhau_Cu = view.findViewById(R.id.txt_MatKhau_Cu);
        txt_MatKhau_Moi = view.findViewById(R.id.txt_MatKhau_Moi);
        txt_XNMK_Moi = view.findViewById(R.id.txt_XNMK_Moi);
        btn_Doi = view.findViewById(R.id.btn_Doi);
        btn_Huy = view.findViewById(R.id.btn_Huy);

        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        btn_Doi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                String mkCu = txt_MatKhau_Cu.getText().toString().trim();
                String mkMoi = txt_MatKhau_Moi.getText().toString().trim();
                String XNMK = txt_XNMK_Moi.getText().toString().trim();
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
                String matt = preferences.getString("USERNAME","");


                if (mkCu.isEmpty() || mkMoi.isEmpty() || XNMK.isEmpty()){
                    msg ="Vui lòng nhập đầy đủ để đổi mật khẩu";
                } else if (!mkMoi.equalsIgnoreCase(XNMK)) {
                    msg ="Mật khẩu mới và xác nhận chưa khớp";
                } else {
                    ttDao = new thuthuDao(getContext());
                    int check = ttDao.updatePass(matt,mkCu,mkMoi);

                    if (check == 1){
                        msg = "Đổi mật khẩu thành công mời đăng nhập lại để sử dụng";
                        reset();
                        startActivity(new Intent(getContext(), DangNhap.class));
                    } else if (check == 0) {
                        msg ="Mật khẩu cũ chưa chính xác";
                    } else{
                        msg = "Đổi mật khẩu thất bại";
                    }
                }
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void reset(){
        txt_MatKhau_Cu.setText("");
        txt_MatKhau_Moi.setText("");
        txt_XNMK_Moi.setText("");
    }

}