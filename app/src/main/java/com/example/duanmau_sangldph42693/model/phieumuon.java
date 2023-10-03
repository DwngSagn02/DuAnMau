package com.example.duanmau_sangldph42693.model;

public class phieumuon {
    private int mapm, matv, masach,trangthai, tienthue;
    private String ngay;
    private String tentv,tensach;

    public phieumuon(int mapm, int matv, String tentv, int masach, String tensach, String ngay, int trangthai, int tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.masach = masach;
        this.trangthai = trangthai;
        this.tienthue = tienthue;
        this.ngay = ngay;
        this.tentv = tentv;
        this.tensach = tensach;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }


    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }


    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }


    public phieumuon(int matv, int masach, int trangthai, int tienthue, String ngay) {
        this.matv = matv;
        this.masach = masach;
        this.trangthai = trangthai;
        this.tienthue = tienthue;
        this.ngay = ngay;
    }
}
