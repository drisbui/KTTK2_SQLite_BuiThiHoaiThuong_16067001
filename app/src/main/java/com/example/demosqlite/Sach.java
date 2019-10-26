package com.example.demosqlite;

public class Sach {
    private int id_Sach;
    private String tenSach;
    private int id_TacGia;

    public int getId_Sach() {
        return id_Sach;
    }

    public void setId_Sach(int id_Sach) {
        this.id_Sach = id_Sach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getId_TacGia() {
        return id_TacGia;
    }

    public void setId_TacGia(int id_TacGia) {
        this.id_TacGia = id_TacGia;
    }

    public Sach(int id_Sach, String tenSach, int id_TacGia) {
        this.id_Sach = id_Sach;
        this.tenSach = tenSach;
        this.id_TacGia = id_TacGia;
    }

    public Sach() {
        this.id_Sach = 0;
        this.tenSach = "null";
        this.id_TacGia = 0;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "id_Sach=" + id_Sach +
                ", tenSach='" + tenSach + '\'' +
                ", id_TacGia=" + id_TacGia +
                '}';
    }
}
