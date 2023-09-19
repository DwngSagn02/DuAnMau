package com.example.duanmau_sangldph42693.model;

public class thuthu {
    private String matt, hoten,mk, level;

    public thuthu() {
    }

    public thuthu(String matt) {
        this.matt = matt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public thuthu(String hoten, String mk, String level) {
        this.hoten = hoten;
        this.mk = mk;
        this.level = level;
    }

    public thuthu(String matt, String hoten, String mk, String level) {
        this.matt = matt;
        this.hoten = hoten;
        this.mk = mk;
        this.level = level;
    }
}
