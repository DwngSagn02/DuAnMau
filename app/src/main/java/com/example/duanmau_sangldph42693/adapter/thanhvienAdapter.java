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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.dao.thanhvienDao;
import com.example.duanmau_sangldph42693.model.thanhvien;

import java.util.ArrayList;

public class thanhvienAdapter extends RecyclerView.Adapter<thanhvienAdapter.viewholder> {
    private final Context context;
    private final ArrayList<thanhvien> list;
    thanhvienDao tvDao;

    //1. tạo một interface để xử lý sự kiện long click
    public interface OnItemLongClickListener {
        void onItemLongClick(View view,int position);
    }

    public thanhvienAdapter(Context context, ArrayList<thanhvien> list) {
        this.context = context;
        this.list = list;
        tvDao = new thanhvienDao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_thanhvien,null);

        // 5.  Khai báo và truyền đối tượng OnItemLongClickListener vào ViewHolder
        viewholder vholder = new viewholder(view);
        vholder.setLongClickListener(vholder.listener);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        if (position >= 0 && position < list.size()){
            thanhvien tv = list.get(position);
            String setcolor = "";

            holder.txtMaTV.setText(String.valueOf(tv.getMatv()));
            holder.txtTenTV.setText(tv.getHoten());
            holder.txtNamSinh.setText(String.valueOf(tv.getNamsinh()));

            if (holder.getAdapterPosition() %2 == 0){
                setcolor = "#000000";
            }else {
                setcolor = "#4CAF50";
            }

            holder.txtTenTV.setTextColor(Color.parseColor(setcolor));
            holder.txtNamSinh.setTextColor(Color.parseColor(setcolor));

            holder.btndelete_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Cảnh báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                    builder.setNegativeButton("Không",null);
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (tvDao.deleteTV(tv.getMatv())){
                                list.remove(tv);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(context, "Không thể xóa thành viên này", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            });

            // 6. longClickListener là một đối tượng triển khai interface OnItemLongClickListener, và nó được truyền vào Adapter. Khi sự kiện long click xảy ra trong Adapter, nó sẽ thông báo về cho longClickListener để xử lý tương ứng.
            holder.setLongClickListener(new OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, int position) {
                    opendialog(tv);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{

        TextView txtMaTV, txtTenTV, txtNamSinh;
        ImageView btndelete_TV;

        private OnItemLongClickListener listener;// 2. Khai báo OnItemLongClickListener listener
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            btndelete_TV = itemView.findViewById(R.id.btndelete_TV);


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
        public void setLongClickListener(OnItemLongClickListener longListener) {
            listener = longListener;
        }
    }
    public void opendialog(thanhvien tv){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_thanhvien,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView MaTV_update = view.findViewById(R.id.MaTV_update);
        EditText edt_hoten_ud = view.findViewById(R.id.edt_hoten_ud);
        EditText edt_namsinh_ud = view.findViewById(R.id.edt_namsinh_ud);
        Button btnupdate_tv = view.findViewById(R.id.btnupdate_tv);

        MaTV_update.setText(String.valueOf(tv.getMatv()));
        edt_hoten_ud.setText(tv.getHoten());
        edt_namsinh_ud.setText(String.valueOf(tv.getNamsinh()));

        btnupdate_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ma = Integer.parseInt(MaTV_update.getText().toString());
                String ten = edt_hoten_ud.getText().toString();
                String nam = edt_namsinh_ud.getText().toString();

                if (ten.isEmpty() || nam.isEmpty()){
                    Toast.makeText(context, "Không được bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                } else if ( ! isso(nam)) {
                    Toast.makeText(context, "Năm sinh nhập sai", Toast.LENGTH_SHORT).show();
                } else {
                    String mess = tvDao.updateTV(ma, ten, nam);
                    tvDao.updateTV(ma, ten, nam);
                    list.clear();
                    list.addAll(tvDao.selectAllTV());
                    notifyDataSetChanged();
                    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }
    public boolean isso(String so){
        return so.matches("[1-2]\\d\\d\\d");
    }
}
