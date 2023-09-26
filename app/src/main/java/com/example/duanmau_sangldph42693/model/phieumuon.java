package com.example.duanmau_sangldph42693.model;

public class phieumuon {
    private int mapm, matv, masach,trangthai, tienthue;
    private String matt, ngay;
    private String tentv;
    private String tentt;

    public phieumuon(int mapm,String matt, String tentt, int matv,  String tentv,int masach, String tensach,  String ngay, int trangthai, int tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.masach = masach;
        this.trangthai = trangthai;
        this.tienthue = tienthue;
        this.matt = matt;
        this.ngay = ngay;
        this.tentv = tentv;
        this.tentt = tentt;
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

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
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

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }


    public phieumuon(int matv, int masach, int trangthai, int tienthue, String matt, String ngay) {
        this.matv = matv;
        this.masach = masach;
        this.trangthai = trangthai;
        this.tienthue = tienthue;
        this.matt = matt;
        this.ngay = ngay;
    }

    private String tensach;




}
