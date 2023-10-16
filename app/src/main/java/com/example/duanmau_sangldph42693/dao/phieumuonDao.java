package com.example.duanmau_sangldph42693.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_sangldph42693.database.DBHelper;
import com.example.duanmau_sangldph42693.model.phieumuon;

import java.util.ArrayList;

public class phieumuonDao {
    DBHelper dbHelper;

    public phieumuonDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<phieumuon> getListPhieuMuon() {
        ArrayList<phieumuon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.mapm, pm.matv, tv.hoten, pm.masach, book.tensach, pm.ngay, pm.gio, pm.trangthai, pm.tienthue FROM phieumuon pm, thanhvien tv, sach book  WHERE pm.matv = tv.matv AND pm.masach = book.masach ORDER BY pm.mapm", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new phieumuon(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean status(int maPM, int code) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai", code);
        long check = database.update("phieumuon", contentValues, "mapm = ?", new String[]{String.valueOf(maPM)});
        if (check == -1)
            return false;
        else return true;
    }

    public boolean addPhieuMuon(phieumuon pm) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matv", pm.getMatv());
        contentValues.put("masach", pm.getMasach());
        contentValues.put("ngay", pm.getNgay());
        contentValues.put("gio", pm.getGio());
        contentValues.put("tienthue", pm.getTienthue());
        contentValues.put("trangthai", pm.getTrangthai());

        long check = database.insert("phieumuon", null, contentValues);

        return check > 0 ? true : false;
    }

    public boolean deletePhieuMuon(int mapm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("phieumuon", "mapm = ?", new String[]{String.valueOf(mapm)});
        return check > 0 ? true : false;
    }
}
