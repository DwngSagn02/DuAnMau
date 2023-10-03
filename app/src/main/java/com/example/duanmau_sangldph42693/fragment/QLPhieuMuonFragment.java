package com.example.duanmau_sangldph42693.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.adapter.phieumuonAdapter;
import com.example.duanmau_sangldph42693.dao.phieumuonDao;
import com.example.duanmau_sangldph42693.dao.sachDao;
import com.example.duanmau_sangldph42693.dao.thanhvienDao;
import com.example.duanmau_sangldph42693.model.phieumuon;
import com.example.duanmau_sangldph42693.model.sach;
import com.example.duanmau_sangldph42693.model.thanhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class QLPhieuMuonFragment extends Fragment {
    private RecyclerView rcv_pm;
    private FloatingActionButton fladd_pm;
    private phieumuon pm;
    private phieumuonDao pmDao;
    private phieumuonAdapter adapter;
    private ArrayList<phieumuon> list = new ArrayList<>();
    Dialog dialog;

    public QLPhieuMuonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_phieu_muon, container, false);
        rcv_pm = view.findViewById(R.id.rcv_pm);
        fladd_pm = view.findViewById(R.id.fladd_pm);
        pmDao = new phieumuonDao(getContext());
        list = pmDao.getListPhieuMuon();
        rcv_pm.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new phieumuonAdapter(getContext(),list);
        rcv_pm.setAdapter(adapter);
        fladd_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        return view;
    }
    public void opendialog(){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_phieumuon);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Spinner spnThanhVien = dialog.findViewById(R.id.spnThanhVien);
        getThanhVien(spnThanhVien);

        Spinner spnSach = dialog.findViewById(R.id.spnSach);
        getSach(spnSach);

        if (spnSach.getCount() <= 0) {
            Toast.makeText(getContext(), "Chưa có sách nào", Toast.LENGTH_SHORT).show();
            return;
        }

        if (spnThanhVien.getCount() <= 0) {
            Toast.makeText(getContext(), "Chưa có thành viên nào", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.findViewById(R.id.btnadd_pm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // lấy mã thành viên
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                // Mã sách
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");


                int tien = (int) hsSach.get("giathue");

                addPhieuMuon(matv, masach, tien);
            }
        });
        dialog.findViewById(R.id.btnHuy_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    private void getThanhVien(Spinner spnSpinner) {
        thanhvienDao tvDao = new thanhvienDao(getContext());
        ArrayList<thanhvien> list = tvDao.selectAllTV();
        ArrayList<HashMap<String, Object>> maps = new ArrayList<>();

        for (thanhvien tv : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten", tv.getHoten());
            maps.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), maps, android.R.layout.simple_list_item_1, new String[]{"hoten"}, new int[]{android.R.id.text1});
        spnSpinner.setAdapter(simpleAdapter);
    }

    private void getSach(Spinner spnSpinner) {
        sachDao sDao = new sachDao(getContext());
        ArrayList<sach> list = sDao.selectAllSach();
        ArrayList<HashMap<String, Object>> maps = new ArrayList<>();

        for (sach s : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", s.getMasach());
            hs.put("tensach", s.getTensach());
            hs.put("giathue", s.getGiathue());
            maps.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), maps, android.R.layout.simple_list_item_1, new String[]{"tensach"}, new int[]{android.R.id.text1});
        spnSpinner.setAdapter(simpleAdapter);
    }

    private void addPhieuMuon(int matv, int masach, int tien) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        String ngay = format.format(date);

        phieumuon pm = new phieumuon(matv, masach, 0, tien, ngay);

        boolean check = pmDao.addPhieuMuon(pm);

        if (check) {
            list.clear();
            list.addAll(pmDao.getListPhieuMuon());
            adapter.notifyDataSetChanged();
            dialog.dismiss();
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }


}