package com.example.duanmau_sangldph42693.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLIB";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng thủ thư
        db.execSQL("create table thuthu(matt text primary key, hoten text, matkhau text, level text)");

        // Tạo bảng thành viên
        db.execSQL("create table thanhvien(matv integer primary key autoincrement, hoten text, namsinh integer)");

        // Tạo bảng loại sách
        db.execSQL("create table loaisach(maloai integer primary key autoincrement, tenloai text)");

        // Tạo bảng sách
        db.execSQL("create table sach(masach integer primary key, tensach text," +
                " giathue integer," +
                " maloai integer references loaisach(maloai))");

        // Tạo bảng phiếu mượn
        db.execSQL("create table phieumuon(mapm integer primary key autoincrement," +
                "matv integer references thanhvien(matv)," +
                "masach integer references sach(masach)," +
                "tienthue integer," +
                "ngay text," +
                "gio text," +
                "trangthai integer)");

        // Thêm dữ liệu vào bảng
        db.execSQL("insert into thuthu values('admin','admin','123','Admin'),('sang','Lê Đăng Sang','a12','Thủ Thư')");
        db.execSQL("insert into thanhvien values(1,'Lê Đăng Sang',2001),(2,'Phạm Minh Hiếu',2004),(3,'Bùi Văn Sơn',2002)");
        db.execSQL("insert into sach values(1,'Java 1',1500,1),(2,'Java 2',3000,1),(3,'CSS/HTML',2000,2)");
        db.execSQL("insert into loaisach values(1,'Java'),(2,'WEB')");
        db.execSQL("insert into phieumuon values(1,1,1,1500,'17-09-2003','10:22',1),(2,2,3,2000,'13-09-2003','09:35',0),(3,1,2,3000,'19-09-2003','19:10',0)");
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
