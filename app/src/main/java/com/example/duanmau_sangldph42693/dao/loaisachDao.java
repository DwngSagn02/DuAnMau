package com.example.duanmau_sangldph42693.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_sangldph42693.database.DBHelper;
import com.example.duanmau_sangldph42693.model.loaisach;
import com.example.duanmau_sangldph42693.model.sach;

import java.util.ArrayList;
import java.util.List;

public class loaisachDao {
    DBHelper dbHelper;

    public loaisachDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<loaisach> getListLoaiSach() {
        ArrayList<loaisach> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM loaisach", null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new loaisach(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public boolean addLoaiSach(String nameType) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai", nameType);
        long check = database.insert("loaisach", null, values);
        return check == 1 ? true : false;
    }

    public boolean updateLoaiSach(int maloai, String tenloai) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai", tenloai);
        long check = database.update("loaisach", values, "maloai = ?", new String[]{String.valueOf(maloai)});
        return check == 1 ? true : false;
    }


    // 1 true, 0 false, -1 no permission
    public int removeTypeSach(int maloai) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM sach WHERE maloai = ?", new String[]{String.valueOf(maloai)});

        if (cursor.getCount() > 0)
            return -1;

        long check = database.delete("loaisach", "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1)
            return 0;
        else return 1;
    }

    public String getTenLoai(int maloai) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT tenloai FROM loaisach WHERE maloai = ?", new String[]{String.valueOf(maloai)});

        if (cursor.getCount() >0 ) {
            cursor.moveToFirst();
            return cursor.getString(0);
        } else return "Lỗi";
    }

//    public ArrayList<loaisach> getData(String sql, String...selectionArgs){
//        ArrayList<loaisach> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
//        while (cursor.moveToNext()){
//            loaisach obj = new loaisach();
//            obj.setMaloai(cursor.getInt(0));
//            obj.setTenloai(cursor.getString(1));
//            list.add(obj);
//        }
//        return list;
//    }
//
//    public loaisach getID(String id){
//        String sql = "select * from loaisach where maloai = ?";
//        List<loaisach> list = getData(sql,id);
//        return list.get(0);
//    }
}
