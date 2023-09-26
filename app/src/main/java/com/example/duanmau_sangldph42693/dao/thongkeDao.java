package com.example.duanmau_sangldph42693.dao;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sangldph42693.database.DBHelper;
import com.example.duanmau_sangldph42693.model.Top;
import com.example.duanmau_sangldph42693.model.sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class thongkeDao {
    DBHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    public thongkeDao(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Top> getTop10() {
//        ArrayList<sach> list = new ArrayList<>();
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        Cursor cursor = database.rawQuery("SELECT pm.masach, book.tensach, COUNT(pm.masach) FROM phieumuon pm, sach book WHERE pm.masach = book.masach GROUP BY pm.masach, book.tensach ORDER BY COUNT(pm.masach) DESC LIMIT 10", null);
//
//        if (cursor.getCount() != 0) {
//            cursor.moveToFirst();
//
//            do {
//                list.add(new sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
//            } while (cursor.moveToNext());
//        }
//
//        return list;
        String sqlTop = "select masach, count(masach) as soluong from phieumuon group by masach order by soluong desc limit 10";
        List<Top> list = new ArrayList<>();
        sachDao sDao = new sachDao(context);
        Cursor cursor = db.rawQuery(sqlTop,null);
        while (cursor.moveToNext()){
            Top top = new Top();
            sach s = sDao.getID(cursor.getString(cursor.getColumnIndex("masach")));
            top.setTensach(s.getTensach());
            top.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(top);
        }
        return list;

    }

    public int getDoanhThu(String fromDate, String toDate) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(tienthue) FROM phieumuon WHERE substr(ngay, 7) || substr(ngay, 4, 2) || substr(ngay, 1, 2) BETWEEN ? AND ?", new String[]{fromDate, toDate});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.d(TAG, "getDoanhThu: " + cursor.getInt(0));
            return cursor.getInt(0);
        }

        else return 0;
    }
}
