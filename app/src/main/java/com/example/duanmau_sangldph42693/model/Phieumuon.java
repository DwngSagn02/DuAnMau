package com.example.duanmau_sangldph42693.model;

public class Phieumuon {
    private int maphieu;
    private String thanhvien;
    private String tensach;
    private double tienthue;
    private String ngaythue;
    private int trangthai;

    public Phieumuon(String thanhvien, String tensach, double tienthue, String ngaythue, int trangthai) {
        this.thanhvien = thanhvien;
        this.tensach = tensach;
        this.tienthue = tienthue;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public String getThanhvien() {
        return thanhvien;
    }

    public void setThanhvien(String thanhvien) {
        this.thanhvien = thanhvien;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public double getTienthue() {
        return tienthue;
    }

    public void setTienthue(double tienthue) {
        this.tienthue = tienthue;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Phieumuon() {
    }

    public Phieumuon(int maphieu, String thanhvien, String tensach, double tienthue, String ngaythue, int trangthai) {
        this.maphieu = maphieu;
        this.thanhvien = thanhvien;
        this.tensach = tensach;
        this.tienthue = tienthue;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
    }
}
