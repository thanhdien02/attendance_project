package com.stc.attendance.model;

import java.io.Serializable;
import java.util.Date;

public class ChamCong implements Serializable {

    private String id;
    private String maNhanVien;
    private String ten;
    private Date ngayChamCong;
    public ChamCong(String id, String maNhanVien, String ten, Date ngayChamCong) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.ten = ten;
        this.ngayChamCong = ngayChamCong;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "ChamCong{" +
                "id='" + id + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", ten='" + ten + '\'' +
                ", ngayChamCong=" + ngayChamCong +
                '}';
    }
    public String getMaNhanVien() {
        return maNhanVien;
    }
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public Date getNgayChamCong() {
        return ngayChamCong;
    }
    public void setNgayChamCong(Date ngayChamCong) {
        this.ngayChamCong = ngayChamCong;
    }

}
