package com.example.duanmau_sangldph42693.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLIB";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng thủ thư
        db.execSQL("create table thuthu(matt text primary key, hoten text, mk text, level text)");

        // Tạo bảng thành viên
        db.execSQL("create table thanhvien(matv int primary key autoincrement, hoten text, namsinh integer)");

        // Tạo bảng sách
        db.execSQL("create table sach(masach int primary key, tensach text, giathue integer, loai text)");

        // Tạo bảng loại sách
        db.execSQL("create table loaisach(maloai int primary key autoincrement, tenloai text)");

        // Tạo bảng phiếu mượn
        db.execSQL("create table phieumuon(maphieu int primary key autoincrement, thanhvien text, tensach text, tienthue integer, ngaythue text, trangthai integer)");

        // Thêm dữ liệu vào bảng
        db.execSQL("insert into thuthu values('99','admin','admin','admin')");
        db.execSQL("insert into thanhvien values(1,'Lê Đăng Sang',2001)");
        db.execSQL("insert into sach values(1,'Java 1',1500,'Java')");
        db.execSQL("insert into loaisach values(1,'Java')");
        db.execSQL("insert into phieumuon values(1,'Lê Đăng Sang','Java 1',1500,'17-09-2003',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion){
            db.execSQL("drop table if exists thuthu");
            db.execSQL("drop table if exists thanhvien");
            db.execSQL("drop table if exists sach");
            db.execSQL("drop table if exists loaisach");
            db.execSQL("drop table if exists phieumuon");
            onCreate(db);
        }
    }
}
