/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquanan;

import java.util.Date;

/**
 *
 * @author Tan
 */
public class HoaDon {
    private String ID,MaHoaDon,MaKhachHang;

    public HoaDon(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }
    private Double TongTien,TienKhachDua,TienTraLai;
    private int MaNhanVien,DemSoHoaDon;

    public int getDemSoHoaDon() {
        return DemSoHoaDon;
    }

    public void setDemSoHoaDon(int DemSoHoaDon) {
        this.DemSoHoaDon = DemSoHoaDon;
    }

    public HoaDon(int DemSoHoaDon) {
        this.DemSoHoaDon = DemSoHoaDon;
    }

    private Date ThoiGian;
    public HoaDon(){
        
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getTongTien() {
        return TongTien;
    }

    public void setTongTien(Double TongTien) {
        this.TongTien = TongTien;
    }

    public Double getTienKhachDua() {
        return TienKhachDua;
    }

    public void setTienKhachDua(Double TienKhachDua) {
        this.TienKhachDua = TienKhachDua;
    }

    public Double getTienTraLai() {
        return TienTraLai;
    }

    public void setTienTraLai(Double TienTraLai) {
        this.TienTraLai = TienTraLai;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public Date getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Date ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public HoaDon(String ID, Double TongTien, Double TienKhachDua, Double TienTraLai, String MaHoaDon, int MaNhanVien, Date ThoiGian) {
        this.ID = ID;
        this.TongTien = TongTien;
        this.TienKhachDua = TienKhachDua;
        this.TienTraLai = TienTraLai;
        this.MaHoaDon = MaHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.ThoiGian = ThoiGian;
    }
}
