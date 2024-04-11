/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquanan;


/**
 *
 * @author Tan
 */
public class NhanVien {
    private String TenNV,DiaChi,Loai;
    private int MaNV,SDT,SoCMND,GioiTinh;
    public NhanVien(){

    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public int getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(int SoCMND) {
        this.SoCMND = SoCMND;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public NhanVien(String TenNV, String DiaChi, String Loai, int MaNV, int SDT, int SoCMND, int GioiTinh) {
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.Loai = Loai;
        this.MaNV = MaNV;
        this.SDT = SDT;
        this.SoCMND = SoCMND;
        this.GioiTinh = GioiTinh;
    }
}