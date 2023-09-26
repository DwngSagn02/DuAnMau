package com.example.duanmau_sangldph42693.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_sangldph42693.database.DBHelper;
import com.example.duanmau_sangldph42693.model.sach;

import java.util.ArrayList;
import java.util.List;

public class sachDao {
    DBHelper dbHelper;
    public sachDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<sach> selectAllSach(){
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = sqLiteDatabase.rawQuery("select * from sach", null);
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
                do {
                    list.add(new sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }
    public boolean addSach(int maloai, String tensach, int giathue) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maloai", maloai);
        values.put("tensach", tensach);
        values.put("giathue", giathue);
        long check = database.insert("sach", null, values);
        Log.d("TAG", "addSach: " + check);
        return check > 0 ? true : false;
    }

    public boolean updateSach(int masach, int maloai, String tensach, int giathue) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maloai", maloai);
        values.put("tensach", tensach);
        values.put("giathue", giathue);
        long check = database.update("sach", values, "masach = ?", new String[]{String.valueOf(masach)});
        return check > 0 ? true : false;
    }

    public String deleteSach(int masach) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM phieumuon WHERE masach = ?", new String[]{String.valueOf(masach)});

        if (cursor.getCount() > 0) {
            return "Không thể xóa sách này !";
        } else {
            database = dbHelper.getWritableDatabase();
            long check = database.delete("sach", "masach = ?", new String[]{String.valueOf(masach)});
            return check == 1 ? "Xóa thành công." : "Xóa thất bại !";
        }
    }

    public ArrayList<sach> getData(String sql, String...selectionArgs){
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            sach obj = new sach();
            obj.setMasach(cursor.getInt(0));
            obj.setTensach(cursor.getString(1));
            list.add(obj);
        }
        return list;
    }

    public sach getID(String id){
        String sql = "select * from sach where masach = ?";
        List<sach> list = getData(sql,id);
        return list.get(0);
    }
}
