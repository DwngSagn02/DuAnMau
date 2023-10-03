package com.example.duanmau_sangldph42693.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.dao.loaisachDao;
import com.example.duanmau_sangldph42693.model.loaisach;

import java.util.ArrayList;

public class loaisachAdapter extends RecyclerView.Adapter<loaisachAdapter.viewholder> {
    private final Context context;
    private final ArrayList<loaisach> list;
    private loaisachDao lsDao;

    //1. tạo một interface để xử lý sự kiện long click
    private interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    public loaisachAdapter(Context context, ArrayList<loaisach> list) {
        this.context = context;
        this.list = list;
        lsDao = new loaisachDao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_loaisach,null);

        // 5.  Khai báo và truyền đối tượng OnItemLongClickListener vào ViewHolder
        loaisachAdapter.viewholder vholder = new loaisachAdapter.viewholder(view);
        vholder.setLongClickListener(vholder.listener);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        if (position >= 0 && position < list.size()){

            loaisach ls = list.get(position);
            String setcolor = "";

            holder.txtMaLoai.setText(String.valueOf(ls.getMaloai()));
            holder.txtTenLoai.setText(ls.getTenloai());

            if (holder.getAdapterPosition() %2 == 0){
                setcolor = "#000000";
            }else {
                setcolor = "#4CAF50";
            }

            holder.txtMaLoai.setTextColor(Color.parseColor(setcolor));
            holder.txtTenLoai.setTextColor(Color.parseColor(setcolor));

            holder.btn_deletePM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Cảnh báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                    builder.setNegativeButton("Không",null);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (lsDao.removeTypeSach(ls.getMaloai())==1){
                                list.clear();
                                list.addAll(lsDao.getListLoaiSach());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else
                                Toast.makeText(context, "Không thể xóa", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            });

            holder.setLongClickListener(new OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, int position) {
                    opendialog(ls);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{

        TextView txtMaLoai,txtTenLoai;
        ImageView btn_deletePM;

        // 2. Khai báo OnItemLongClickListener listener
        private OnItemLongClickListener listener;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            btn_deletePM = itemView.findViewById(R.id.btn_deletePM);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);

            // 3. thiết lập setOnLongClickListener cho itemView.
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(itemView,position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        // 4. Thêm phương thức setLongClickListener để truyền đối tượng OnItemLongClickListener từ Adapter vào ViewHolder
        public void setLongClickListener(OnItemLongClickListener longClickListener){
            listener = longClickListener;
        }
    }

    public void opendialog(loaisach ls){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_loaisach,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView MaLoai_update = view.findViewById(R.id.MaLoai_update);
        EditText edt_tenloaisach_ud = view.findViewById(R.id.edt_tenloaisach_ud);
        Button btnupdate_loai = view.findViewById(R.id.btnupdate_loai);

        MaLoai_update.setText(String.valueOf(ls.getMaloai()));
        edt_tenloaisach_ud.setText(ls.getTenloai());

        btnupdate_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maloai = Integer.parseInt(MaLoai_update.getText().toString());
                String tenloai = edt_tenloaisach_ud.getText().toString().trim();
                if (tenloai.isEmpty()){
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (lsDao.updateLoaiSach(maloai,tenloai)){
                        list.clear();
                        list.addAll(lsDao.getListLoaiSach());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
