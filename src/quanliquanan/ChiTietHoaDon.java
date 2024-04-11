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
public class ChiTietHoaDon {
    private int SoLuong;
    private String MaHoaDon,MaMonAn;
    public ChiTietHoaDon(){
        
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(String MaMonAn) {
        this.MaMonAn = MaMonAn;
    }

    public ChiTietHoaDon( int SoLuong, String MaHoaDon, String MaMonAn) {
        this.SoLuong = SoLuong;
        this.MaHoaDon = MaHoaDon;
        this.MaMonAn = MaMonAn;
    }
    
}
