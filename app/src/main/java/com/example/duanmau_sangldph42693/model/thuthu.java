package com.example.duanmau_sangldph42693.model;

public class thuthu {
    private String matt, hoten,matkhau, level;

    public thuthu() {
    }

    public thuthu(String matt) {
        this.matt = matt;
    }

    public thuthu(String hoten, String matkhau) {
        this.hoten = hoten;
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String mk) {
        this.matkhau = matkhau;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public thuthu(String hoten, String matkhau, String level) {
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.level = level;
    }

    public thuthu(String matt, String hoten, String matkhau, String level) {
        this.matt = matt;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.level = level;
    }
}
