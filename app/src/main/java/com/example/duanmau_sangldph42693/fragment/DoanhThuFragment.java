package com.example.duanmau_sangldph42693.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.dao.thongkeDao;

import java.util.Calendar;

public class DoanhThuFragment extends Fragment {
    private EditText txt_tungay, txt_denngay;
    private Button btn_doanhthu,btn_denngay,btn_tungay;
    private TextView txt_doanhthu;
    private thongkeDao tkDao;
    Calendar calendar = Calendar.getInstance();
    public DoanhThuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        txt_tungay = view.findViewById(R.id.txt_tungay);
        txt_denngay = view.findViewById(R.id.txt_denngay);
        btn_doanhthu = view.findViewById(R.id.btn_doanhthu);
        btn_denngay = view.findViewById(R.id.btn_denngay);
        btn_tungay = view.findViewById(R.id.btn_tungay);
        txt_doanhthu = view.findViewById(R.id.txt_doanhthu);


        tkDao = new thongkeDao(getContext());

        txt_tungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tungay();
            }
        });

        btn_tungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tungay();
            }
        });

        txt_denngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denngay();
            }
        });
        btn_denngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denngay();
            }
        });

        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromDate = txt_tungay.getText().toString().trim();
                String toDate = txt_denngay.getText().toString().trim();

                if (fromDate.equals("") || toDate.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng chọn thời gian hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int doanhthu = tkDao.getDoanhThu(fromDate, toDate);
                txt_doanhthu.setText(doanhthu + "VNĐ");
            }
        });
        return view;
    }
    public void tungay(){
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txt_tungay.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    public void denngay(){
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txt_denngay.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}