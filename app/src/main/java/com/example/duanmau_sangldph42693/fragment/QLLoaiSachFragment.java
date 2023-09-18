package com.example.duanmau_sangldph42693.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_sangldph42693.R;


public class QLLoaiSachFragment extends Fragment {

    public QLLoaiSachFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_rcv_loaisach, container, false);
        return view;
    }
}