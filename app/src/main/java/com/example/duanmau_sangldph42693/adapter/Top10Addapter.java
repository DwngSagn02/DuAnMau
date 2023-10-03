package com.example.duanmau_sangldph42693.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.model.Top;
import com.example.duanmau_sangldph42693.model.sach;

import java.util.ArrayList;

public class Top10Addapter extends RecyclerView.Adapter<Top10Addapter.viewHolder>{
    private Context context;
    private ArrayList<Top> list = new ArrayList<>();

    public Top10Addapter(Context context, ArrayList<Top> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_top10, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Top s = list.get(position);

        holder.txtTenSach.setText("Tên Sách: " + s.getTensach());
        holder.txtSoLuongMuon.setText("Số Lượng Mượn: " + s.getSoluong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView  txtTenSach, txtSoLuongMuon;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuongMuon = itemView.findViewById(R.id.txtSoLuongMuon);
        }
    }
}

