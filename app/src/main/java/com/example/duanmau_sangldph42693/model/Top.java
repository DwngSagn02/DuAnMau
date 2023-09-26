package com.example.duanmau_sangldph42693.model;

public class Top {
    private int masach, soluong;
    private String tensach;

    public Top( String tensach,int soluong) {
        this.soluong = soluong;
        this.tensach = tensach;
    }

    public Top() {
    }

    public Top(int masach, String tensach, int soluong) {
        this.masach = masach;
        this.soluong = soluong;
        this.tensach = tensach;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


}
