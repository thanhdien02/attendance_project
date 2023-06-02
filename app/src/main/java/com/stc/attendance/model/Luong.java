package com.stc.attendance.model;

import java.io.Serializable;

public class Luong implements Serializable {

    private String id;

    private String maNhanVien;

    public Luong(String id, String maNhanVien, String tenNhanVien, int soNgayLamTrongThang, int thang, int nam, Double tongLuong) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soNgayLamTrongThang = soNgayLamTrongThang;
        this.thang = thang;
        this.nam = nam;
        this.tongLuong = tongLuong;
    }
    public Luong() {

    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Luong{" +
                "id='" + id + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", soNgayLamTrongThang=" + soNgayLamTrongThang +
                ", thang=" + thang +
                ", nam=" + nam +
                ", tongLuong=" + tongLuong +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getSoNgayLamTrongThang() {
        return soNgayLamTrongThang;
    }

    public void setSoNgayLamTrongThang(int soNgayLamTrongThang) {
        this.soNgayLamTrongThang = soNgayLamTrongThang;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public Double getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(Double tongLuong) {
        this.tongLuong = tongLuong;
    }

    private String tenNhanVien;

    private int soNgayLamTrongThang;

    private int thang;

    private int nam;

    private Double tongLuong;
}
