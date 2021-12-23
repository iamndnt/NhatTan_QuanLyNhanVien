package com.ndnt.nhattan_quanlynhanvien;

public class NhanVien
{

    private String manv,tennv;
    private boolean gioitinh,check;

    public NhanVien(String manv, String tennv, boolean gioitinh, boolean check) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.check = check;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
