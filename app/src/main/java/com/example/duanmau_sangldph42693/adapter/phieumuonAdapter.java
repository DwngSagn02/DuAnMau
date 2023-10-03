package com.example.duanmau_sangldph42693.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_sangldph42693.R;
import com.example.duanmau_sangldph42693.dao.phieumuonDao;
import com.example.duanmau_sangldph42693.dao.sachDao;
import com.example.duanmau_sangldph42693.dao.thanhvienDao;
import com.example.duanmau_sangldph42693.dao.thuthuDao;
import com.example.duanmau_sangldph42693.model.phieumuon;
import com.example.duanmau_sangldph42693.model.sach;
import com.example.duanmau_sangldph42693.model.thanhvien;
import com.example.duanmau_sangldph42693.model.thuthu;

import java.util.ArrayList;

public class phieumuonAdapter extends RecyclerView.Adapter<phieumuonAdapter.viewholder> {
    private final Context context;
    private final ArrayList<phieumuon> list;
    phieumuonDao pmDao;
    String statusColor = "";

    public phieumuonAdapter(Context context, ArrayList<phieumuon> list) {
        this.context = context;
        this.list = list;
        pmDao = new phieumuonDao(context);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_phieumuon,null);

        viewholder vholder = new viewholder(view);
        vholder.setOnLongClick(vholder.listener);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        if (position >= 0 && position < list.size()){
            phieumuon pm = list.get(position);

            if (pm.getTrangthai() == 0)
                statusColor = "#F44336";
            else
                statusColor = "#2C90DF";

            holder.txtMaPM.setText("Mã Phiếu : "+pm.getMapm());
            holder.txtTenTV_Pm.setText("Thành Viên : "+pm.getTentv());
            holder.txtTenSach.setText("Tên Sách : "+pm.getTensach());
            holder.txtTienThue.setText("Tiền Thuê : "+pm.getTienthue());
            holder.txtNgayMuon.setText("Ngày Mượn : "+pm.getNgay());

            // set color
            holder.txtMaPM.setTextColor(Color.parseColor(statusColor));
            holder.txtTenTV_Pm.setTextColor(Color.parseColor(statusColor));
            holder.txtTenSach.setTextColor(Color.parseColor(statusColor));
            holder.txtTienThue.setTextColor(Color.parseColor(statusColor));
            holder.txtNgayMuon.setTextColor(Color.parseColor(statusColor));


            if (pm.getTrangthai() == 1){
                holder.chktrangthai.setChecked(true);
            }else {
                holder.chktrangthai.setChecked(false);
            }

            holder.chktrangthai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pm.getTrangthai() == 0){
                        statusColor = "#F44336";
                        pm.setTrangthai(1);
                        pmDao.status(pm.getMapm(),1);
                        Toast.makeText(context, "Đã thay đổi sang trạng thái chưa trả sách.", Toast.LENGTH_SHORT).show();
                    }else {
                        statusColor = "#2C90DF";
                        pm.setTrangthai(0);
                        pmDao.status(pm.getMapm(),0);
                        Toast.makeText(context, "Đã thay đổi sang trạng thái đã trả sách.", Toast.LENGTH_SHORT).show();
                    }
                    holder.txtMaPM.setTextColor(Color.parseColor(statusColor));
                    holder.txtTenTV_Pm.setTextColor(Color.parseColor(statusColor));
                    holder.txtTenSach.setTextColor(Color.parseColor(statusColor));
                    holder.txtTienThue.setTextColor(Color.parseColor(statusColor));
                    holder.txtNgayMuon.setTextColor(Color.parseColor(statusColor));
                    notifyDataSetChanged();
                }
            });

            holder.btn_deletePM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Cảnh báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
                    builder.setNegativeButton("Không",null);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (pmDao.deletePhieuMuon(pm.getMapm())){
                                list.clear();
                                list.addAll(pmDao.getListPhieuMuon());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                }
            });

            holder.setOnLongClick(new OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, int position) {
                    Toast.makeText(context, "Tích dấu V vào nếu thành viên đã trả sách", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{
        TextView txtMaPM,txtTenTV_Pm,txtTenSach,txtTienThue,txtNgayMuon;
        CheckBox chktrangthai;
        ImageView btn_deletePM;

        private OnItemLongClickListener listener;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtMaPM = itemView.findViewById(R.id.txtMaPM);
            txtTenTV_Pm = itemView.findViewById(R.id.txtTenTV_Pm);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtTienThue = itemView.findViewById(R.id.txtTienThue);
            txtNgayMuon = itemView.findViewById(R.id.txtNgayMuon);
            chktrangthai = itemView.findViewById(R.id.chktrangthai);
            btn_deletePM = itemView.findViewById(R.id.btn_deletePM);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(itemView,position);
                            return  true;
                        }
                    }
                    return false;
                }
            });
        }

        public void setOnLongClick(OnItemLongClickListener longClickListener){
            listener = longClickListener;
        }
    }
}
