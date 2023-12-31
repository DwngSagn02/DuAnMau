package com.example.duanmau_sangldph42693.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_sangldph42693.database.DBHelper;
import com.example.duanmau_sangldph42693.model.thuthu;

import java.util.ArrayList;
import java.util.List;

public class thuthuDao {

private SQLiteDatabase db;
    DBHelper dbHelper;
    public thuthuDao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(thuthu obj){
        ContentValues values = new ContentValues();
        values.put("matt",obj.getMatt());
        values.put("hoten",obj.getHoten());
        values.put("matkhau",obj.getMatkhau());
        values.put("level",obj.getLevel());
        return db.insert("thuthu",null,values);
    }

    public int updatePass(String matt, String mkCu, String mkMoi){
        String sql = "SELECT * FROM thuthu WHERE matt =? AND matkhau = ?";
        List<thuthu> list = getData(sql,matt,mkCu);
        if (list.size() != 0){
            ContentValues values = new ContentValues();
            values.put("matkhau",mkMoi);
            long check = db.update("thuthu",values,"matt = ?", new String[]{matt});
            if (check == -1){
                return -1;
            }
            return 1;
        }
        return 0 ;
    }

    public List<thuthu> getAll(){
        String sql = "SELECT * FROM thuthu";
        return getData(sql);
    }

    public boolean checkTK(String id){
        String sql = "SELECT * FROM thuthu WHERE matt=?";
        List<thuthu> list = getData(sql,id);
        if (list.size() == 0){
            return false;
        }
        return true;
    }

    public thuthu getID(String id){
        String sql = "SELECT * FROM thuthu WHERE matt=?";
        List<thuthu> list = getData(sql,id);
        return list.get(0);
    }

    // check login
    public boolean checkLogin(String id, String password){
        String sql = "SELECT * FROM thuthu WHERE matt=? AND matkhau=?";
        List<thuthu> list = getData(sql,id,password);
        if (list.size() == 0){
            return false;
        }
        return true;
    }

    @SuppressLint("Range")
    private List<thuthu> getData(String sql, String...selectionArgs){
        List<thuthu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            thuthu obj = new thuthu();
            obj.setMatt(cursor.getString(cursor.getColumnIndex("matt")));
            obj.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
            obj.setMatkhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            obj.setLevel(cursor.getString(cursor.getColumnIndex("level")));
            list.add(obj);
        }
        return list;
    }
    public String getLevel(String matt) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT level FROM thuthu WHERE matt = ?", new String[]{matt});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        } else return "Lỗi xác thực !";
    }

    public String getName(String matt) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT hoten FROM thuthu WHERE matt = ?", new String[]{matt});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        } else return "Lỗi xác thực !";
    }

}
