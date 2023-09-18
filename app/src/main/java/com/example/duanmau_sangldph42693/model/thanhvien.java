package com.example.duanmau_sangldph42693.model;

public class thanhvien {
    private int matv;
    private String hoten;
    private int namsinh;

    public thanhvien() {
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public thanhvien(int matv, String hoten, int namsinh) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
    }
}
