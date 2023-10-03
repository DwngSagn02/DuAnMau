package com.example.duanmau_sangldph42693.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.adapter.Top10Addapter;
import com.example.duanmau_sangldph42693.dao.thongkeDao;
import com.example.duanmau_sangldph42693.model.Top;
import com.example.duanmau_sangldph42693.model.sach;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {

    RecyclerView recyclerView;

    public Top10Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_top10, container, false);

        recyclerView = view.findViewById(R.id.recyclerTop10);

        thongkeDao thongKeDAO = new thongkeDao(getContext());

        ArrayList<Top> list = (ArrayList<Top>) thongKeDAO.getTop10();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        Top10Addapter addapter = new Top10Addapter(getContext(), list);
        recyclerView.setAdapter(addapter);

        return view;
    }
}