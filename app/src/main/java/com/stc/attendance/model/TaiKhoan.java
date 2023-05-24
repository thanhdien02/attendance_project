package com.stc.attendance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoan implements Serializable {

    private String id;
    private String maNhanVien;
    private String name;
    public TaiKhoan(String id, String maNhanVien, String name, String email, String password, String dienThoai, List<String> roles) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dienThoai = dienThoai;
        this.roles = roles;
    }
    public TaiKhoan() {

    }
    // email không được trùng nhau
    private String email;
    public String getId() {
        return id;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDienThoai() {
        return dienThoai;
    }
    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id='" + id + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dienThoai='" + dienThoai + '\'' +
                ", roles=" + roles +
                '}';
    }
    private String password;
    private String dienThoai;
    private List<String> roles = new ArrayList<>();
}
