package com.example.duanmau_sangldph42693.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.example.duanmau_sangldph42693.adapter.sachAdapter;
import com.example.duanmau_sangldph42693.dao.loaisachDao;
import com.example.duanmau_sangldph42693.dao.sachDao;
import com.example.duanmau_sangldph42693.model.loaisach;
import com.example.duanmau_sangldph42693.model.sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class QLSachFragment extends Fragment {
    private RecyclerView rcv_sach;
    private FloatingActionButton fladd_sach;
    private sach s;
    private sachDao sDao;
    private sachAdapter adapter;
    private ArrayList<sach> list = new ArrayList<>();
    private ArrayList<loaisach> listLoai = new ArrayList<loaisach>();
    private loaisachDao lsDao;
    private Button btn_tang,btn_giam,btn_reset;

    public QLSachFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_sach, container, false);
        fladd_sach = view.findViewById(R.id.fladd_sach);
        rcv_sach = view.findViewById(R.id.rcv_sach);
        btn_tang = view.findViewById(R.id.btn_tang);
        btn_giam = view.findViewById(R.id.btn_giam);
        btn_reset = view.findViewById(R.id.btn_reset);

        sDao = new sachDao(getContext());
        lsDao = new loaisachDao(getContext());
        list = sDao.selectAllSach();

        loadSach(list);

        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<sach> listTang = sDao.sapXepT();
                loadSach(listTang);
            }
        });

        btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<sach> listGiam = sDao.sapXepG();
                loadSach(listGiam);
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSach(list);
            }
        });
        fladd_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        return view;
    }
    public void opendialog(){
        LayoutInflater inflater = ((Activity)getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_sach,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edt_tensach_add = view.findViewById(R.id.edt_tensach_add);
        EditText edt_giathue_add = view.findViewById(R.id.edt_giathue_add);
        EditText edt_loaisach_add = view.findViewById(R.id.edt_loaisach_add);
        Button btn_Them_sach = view.findViewById(R.id.btn_Them_sach);

        List<String> dsach = new ArrayList<>();
        listLoai = lsDao.getListLoaiSach();

        for (loaisach ls : listLoai){
            String tenloai = ls.getTenloai();
            dsach.add(tenloai);
        }

        String[] dsachloai = dsach.toArray(new String[0]);
        edt_loaisach_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("Chọn loại sách");
                builder1.setItems(dsachloai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edt_loaisach_add.setText(dsachloai[which]);
                    }
                });
                Dialog dialog1 = builder1.create();
                dialog1.show();
            }
        });
        btn_Them_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edt_tensach_add.getText().toString().trim();
                String giathue = edt_giathue_add.getText().toString().trim();
                String loaisach = edt_loaisach_add.getText().toString().trim();


                if (tensach.isEmpty() || giathue.isEmpty() || loaisach.isEmpty()){
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (!checkso(giathue)) {
                    Toast.makeText(getContext(), "Giá thuế chưa đúng", Toast.LENGTH_SHORT).show();
                } else {
                    int maloai = 0;
                    for (loaisach ls : listLoai){
                        String check = ls.getTenloai();
                        if (loaisach.equalsIgnoreCase(check)){
                            maloai = ls.getMaloai();
                        }
                    }
                    int gia = Integer.parseInt(giathue);
                    if (sDao.addSach(maloai,tensach,gia)){
                        list.clear();
                        list = sDao.selectAllSach();
                        loadSach(list);
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean checkso(String so){
        return so.matches("\\d++");
    }

    private void loadSach(ArrayList<sach> list1){
        rcv_sach.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new sachAdapter(getContext(),list1);
        adapter.notifyDataSetChanged();
        rcv_sach.setAdapter(adapter);
    }
}