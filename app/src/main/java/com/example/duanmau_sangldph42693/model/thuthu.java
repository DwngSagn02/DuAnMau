package com.example.duanmau_sangldph42693.model;

public class thuthu {
    private int matt;
    private String hoten, tendn,mk, level;

    public thuthu(String hoten, String tendn, String mk) {
        this.hoten = hoten;
        this.tendn = tendn;
        this.mk = mk;
    }

    public int getMatt() {
        return matt;
    }

    public void setMatt(int matt) {
        this.matt = matt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
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

    public thuthu() {
    }

    public thuthu(int matt, String hoten, String tendn, String mk, String level) {
        this.matt = matt;
        this.hoten = hoten;
        this.tendn = tendn;
        this.mk = mk;
        this.level = level;
    }
}
