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
import com.example.duanmau_sangldph42693.dao.sachDao;
import com.example.duanmau_sangldph42693.model.loaisach;
import com.example.duanmau_sangldph42693.model.sach;

import java.util.ArrayList;
import java.util.List;

public class sachAdapter extends RecyclerView.Adapter<sachAdapter.viewholder> {
    private final Context context;
    private final ArrayList<sach> list;
    private sachDao sDao;
    private loaisachDao lsDao;
    private ArrayList<loaisach> listLoai;

    private interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    public sachAdapter(Context context, ArrayList<sach> list) {
        this.context = context;
        this.list = list;
        sDao = new sachDao(context);
        lsDao = new loaisachDao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_sach,null);

        sachAdapter.viewholder vholder = new sachAdapter.viewholder(view);
        vholder.setOnLongClickListener(vholder.listener);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        if (position >= 0 && position < list.size()){
            sach s = list.get(position);
            String setcolor = "";

            holder.txtMaSach.setText(String.valueOf(s.getMasach()));
            holder.txtTenSach.setText(s.getTensach());
            holder.txtTienThue.setText(String.valueOf(s.getGiathue()));
            holder.txtLoaiSach.setText(lsDao.getTenLoai(s.getMaloai()));

            if (holder.getAdapterPosition() %2 == 0){
                setcolor = "#000000";
            }else {
                setcolor = "#4CAF50";
            }

            holder.txtMaSach.setTextColor(Color.parseColor(setcolor));
            holder.txtTenSach.setTextColor(Color.parseColor(setcolor));
            holder.txtTienThue.setTextColor(Color.parseColor(setcolor));
            holder.txtLoaiSach.setTextColor(Color.parseColor(setcolor));

            holder.btn_deleteSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Cảnh báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                    builder.setNegativeButton("Không",null);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sDao.deleteSach(s.getMasach());
                            list.clear();
                            list.addAll(sDao.selectAllSach());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            });

            holder.setOnLongClickListener(new OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, int position) {
                    opendialog(s);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{
        TextView txtMaSach,txtTenSach,txtTienThue,txtLoaiSach;
        ImageView btn_deleteSach;

        private OnItemLongClickListener listener;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtTienThue = itemView.findViewById(R.id.txtTienThue);
            txtLoaiSach = itemView.findViewById(R.id.txtLoaiSach);
            btn_deleteSach = itemView.findViewById(R.id.btn_deleteSach);

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
        public void setOnLongClickListener(OnItemLongClickListener longClickListener){
            listener = longClickListener;
        }
    }

    public void opendialog(sach s){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sach,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView MaSach_update = view.findViewById(R.id.MaSach_update);
        EditText edt_tensach_ud = view.findViewById(R.id.edt_tensach_ud);
        EditText edt_giathue_ud = view.findViewById(R.id.edt_giathue_ud);
        EditText edt_loaisach_ud = view.findViewById(R.id.edt_loaisach_ud);
        Button btnupdate_sach = view.findViewById(R.id.btnupdate_sach);

        MaSach_update.setText(String.valueOf(s.getMasach()));
        edt_tensach_ud.setText(s.getTensach());
        edt_giathue_ud.setText(String.valueOf(s.getGiathue()));
        edt_loaisach_ud.setText(lsDao.getTenLoai(s.getMaloai()));

        // tạo list để lấy tên loại sách
        List<String> dsach = new ArrayList<>();
        listLoai = lsDao.getListLoaiSach();
        for (loaisach ls: listLoai) {
            String tenloai = ls.getTenloai();
            dsach.add(tenloai);
        }
        // chuyển từ list về mảng chuỗi
        String[] dsachloai = dsach.toArray(new String[0]);

        // bắt sự kiện cho edt_loaisach_ud khi người dùng tích chọn loại sách
        edt_loaisach_ud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn loại sách");
                builder1.setItems(dsachloai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edt_loaisach_ud.setText(dsachloai[which]);
                    }
                });
                builder1.show();
            }
        });

        btnupdate_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masach = Integer.parseInt(MaSach_update.getText().toString());
                String ten = edt_tensach_ud.getText().toString().trim();
                String giathue = edt_giathue_ud.getText().toString().trim();
                String loai = edt_loaisach_ud.getText().toString().trim();

                if (ten.isEmpty() || giathue.isEmpty() || loai.isEmpty()){
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (! checkso(giathue)) {
                    Toast.makeText(context, "Giá thuê chưa đúng!!!", Toast.LENGTH_SHORT).show();
                } else {
                    int gia = Integer.parseInt(giathue);

                    // lấy ra mã loại mà người dùng chọn
                    int maloai = 0;
                    for (loaisach ls : listLoai) {
                        String check = edt_loaisach_ud.getText().toString().trim();
                        if (loai.equalsIgnoreCase(check)){
                            maloai = ls.getMaloai();
                        }
                    }
                    if (sDao.updateSach(masach,maloai,ten,gia)){
                        list.clear();
                        list.addAll(sDao.selectAllSach());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean checkso(String so){
        return so.matches("\\d++");
    }
}
