package com.example.demosqlite;

public class TacGia {
    private int id_TacGia;
    private String tenTG;

    public int getId_TacGia() {
        return id_TacGia;
    }

    public void setId_TacGia(int id_TacGia) {
        this.id_TacGia = id_TacGia;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public TacGia(int id_TacGia, String tenTG) {
        this.id_TacGia = id_TacGia;
        this.tenTG = tenTG;
    }

    public TacGia() {
        this.id_TacGia = 0;
        this.tenTG = "null";
    }
}
