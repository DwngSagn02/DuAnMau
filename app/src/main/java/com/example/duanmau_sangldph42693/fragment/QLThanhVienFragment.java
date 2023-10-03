package com.example.duanmau_sangldph42693.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.adapter.thanhvienAdapter;
import com.example.duanmau_sangldph42693.dao.thanhvienDao;
import com.example.duanmau_sangldph42693.model.thanhvien;
import com.example.duanmau_sangldph42693.model.thuthu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLThanhVienFragment extends Fragment {


    private RecyclerView rcv_tv;
    private FloatingActionButton fladd_tv;
    private thanhvienDao tvDao;
    private ArrayList<thanhvien> list = new ArrayList<>();
    private thanhvien tv;
    private thanhvienAdapter adapter;


    public QLThanhVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_thanh_vien, container, false);
        rcv_tv = view.findViewById(R.id.rcv_tv);
        fladd_tv = view.findViewById(R.id.fladd_tv);

        tvDao = new thanhvienDao(getContext());
        list = tvDao.selectAllTV();

        rcv_tv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new thanhvienAdapter(getContext(),list);
        rcv_tv.setAdapter(adapter);

        fladd_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv = new thanhvien();
                opendialog(tv);
            }
        });

        return view;
    }
    public void opendialog(thanhvien tv){
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_thanhvien,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edt_hoten_add = view.findViewById(R.id.edt_hoten_add);
        EditText edt_namsinh_add = view.findViewById(R.id.edt_namsinh_add);
        Button btn_Them_tv = view.findViewById(R.id.btn_Them_tv);

        btn_Them_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_hoten_add.getText().toString();
                String nam = edt_namsinh_add.getText().toString();

                if (ten.isEmpty() || nam.isEmpty()){
                    Toast.makeText(getContext(), "Không được bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                } else if ( ! isso(nam)) {
                    Toast.makeText(getContext(), "Năm sinh nhập sai", Toast.LENGTH_SHORT).show();
                } else {
                    tv.setHoten(ten);
                    tv.setNamsinh(Integer.parseInt(nam));
                    tvDao.addTV(tv.getHoten(), String.valueOf(tv.getNamsinh()));
                    list.clear();
                    list.addAll(tvDao.selectAllTV());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
    }
    public boolean isso(String so){
        return so.matches("[1-2]\\d\\d\\d");
    }
}