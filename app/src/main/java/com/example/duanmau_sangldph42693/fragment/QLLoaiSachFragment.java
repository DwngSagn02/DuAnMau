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
import com.example.duanmau_sangldph42693.adapter.loaisachAdapter;
import com.example.duanmau_sangldph42693.dao.loaisachDao;
import com.example.duanmau_sangldph42693.model.loaisach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLLoaiSachFragment extends Fragment {
    private RecyclerView rcv_loai;
    private FloatingActionButton fladd_loai;
    private loaisach ls;
    private ArrayList<loaisach> list = new ArrayList<>();
    private loaisachDao lsDao;
    private loaisachAdapter adapter;


    public QLLoaiSachFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_loai_sach, container, false);
        fladd_loai = view.findViewById(R.id.fladd_loai);
        rcv_loai = view.findViewById(R.id.rcv_loai);

        lsDao = new loaisachDao(getContext());
        list = lsDao.getListLoaiSach();

        rcv_loai.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new loaisachAdapter(getContext(),list);
        rcv_loai.setAdapter(adapter);

        fladd_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        return view;
    }
    public void opendialog(){
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_loaisach,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edt_tenloaisach_add = view.findViewById(R.id.edt_tenloaisach_add);
        Button btn_Them_loai = view.findViewById(R.id.btn_Them_loai);

        btn_Them_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edt_tenloaisach_add.getText().toString().trim();
                if (tenloai.isEmpty()){
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else {
                    lsDao = new loaisachDao(getContext());
                    if (! lsDao.addLoaiSach(tenloai)){
                        list.clear();
                        list.addAll(lsDao.getListLoaiSach());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}