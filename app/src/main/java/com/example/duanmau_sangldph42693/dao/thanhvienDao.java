package com.example.duanmau_sangldph42693.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sangldph42693.database.DBHelper;
import com.example.duanmau_sangldph42693.model.thanhvien;

import java.util.ArrayList;

public class thanhvienDao {
    DBHelper dbHelper;
    public thanhvienDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<thanhvien> selectAllTV(){
        ArrayList<thanhvien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = sqLiteDatabase.rawQuery("select * from thanhvien",null);
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
//                while (!cursor.isAfterLast()){
//                    thanhvien tv = new thanhvien();
//                    tv.setMatv(cursor.getInt(0));
//                    tv.setHoten(cursor.getString(1));
//                    tv.setNamsinh(cursor.getInt(2));
//                    list.add(tv);
//                    cursor.moveToNext();
//                }
                do {
                    list.add(new thanhvien(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.i(TAG,"lỗi",e);
        }
        return list;
    }
    public boolean isHaveData(int matv) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM phieumuon WHERE matv = ?", new String[]{String.valueOf(matv)});
        return cursor.getCount() > 0 ? true : false;
    }

    public boolean deleteTV(int matv) {
        if (isHaveData(matv))
            return false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("thanhvien", "matv = ?", new String[]{String.valueOf(matv)});
        return check == 1 ? true : false;
    }

    public String addTV(String hoten, String namsinh) {
        String msg = "";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("namsinh", namsinh);
        long check = database.insert("thanhvien", null, values);

        if (check > 0)
            msg = "Thêm thành công.";
        else
            msg = "Thêm thất bại";

        return msg;
    }

    public String updateTV(int matv, String hoten, String namsinh) {
        String msg = "";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("namsinh", namsinh);
        long check = database.update("thanhvien", values, "matv = ? ", new String[]{String.valueOf(matv)});

        if (check > 0)
            msg = "Sửa thành công.";
        else
            msg = "Sửa thất bại";

        return msg;
    }
}
