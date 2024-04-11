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
public class ThucDon {
    private String ID,TenMonAn,Hinh,DonVi;

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public ThucDon(String DonVi) {
        this.DonVi = DonVi;
    }
    private Double DonGia;
    public ThucDon(){
        
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String TenMonAn) {
        this.TenMonAn = TenMonAn;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public ThucDon(String ID, String TenMonAn, String Hinh, Double DonGia) {
        this.ID = ID;
        this.TenMonAn = TenMonAn;
        this.Hinh = Hinh;
        this.DonGia = DonGia;
    }

   
    
}
