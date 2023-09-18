package com.example.duanmau_sangldph42693.model;

public class sach {
    private int masach;
    private String tensach;
    private double giathue;
    private String loaisach;

    public sach(String tensach, double giathue, String loaisach) {
        this.tensach = tensach;
        this.giathue = giathue;
        this.loaisach = loaisach;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public double getGiathue() {
        return giathue;
    }

    public void setGiathue(double giathue) {
        this.giathue = giathue;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public sach() {
    }

    public sach(int masach, String tensach, double giathue, String loaisach) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.loaisach = loaisach;
    }
}
