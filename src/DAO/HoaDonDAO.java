/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import quanliquanan.ChiTietHoaDon;
import quanliquanan.HoaDon;
import quanliquanan.KhachHang;
import quanliquanan.NhanVien;
import quanliquanan.ThucDon;
/**
 *
 * @author Tan
 */
public class HoaDonDAO {
    private Connection con;
    public HoaDonDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlquanan?useSSl=flase","root","tanvip01@");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ThucDon laygiatritocbb(String ID) throws SQLException{
        String sql ="select * from tblthucdon where MaMonAN=?"; 
    
        try(
            PreparedStatement ps =con.prepareStatement(sql);          
            ){
        ps.setString(1, ID );
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            ThucDon td = new ThucDon();
            td.setID(rs.getString(1));
            td.setTenMonAn(rs.getString(2));
            td.setDonGia( rs.getDouble(3));    
//            td.setDonVi(rs.getString(4));
            return td;
        }
        return null;
    }
    }
        public KhachHang laykhachhang(String TenKhachHang,int SoDT) throws SQLException{
        String sql ="select * from tblkhachhang where TenKhachhang=? and SoDienThoai=?"; 
    
        try(
            PreparedStatement ps =con.prepareStatement(sql);          
            ){
        ps.setInt(2, SoDT);
        ps.setString(1, TenKhachHang );
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            KhachHang kh = new KhachHang();
            kh.setMaKhachHang(rs.getString(1));
            kh.setTenKhachHang(rs.getString(2));
            kh.setSoDienThoai(rs.getInt(3));      
            return kh;
        }
        return null;
    }
        }

    public ThucDon timkiemtd(String TenMon) throws Exception{
        
    String sql ="select * from tblthucdon where TenMon=?"; 
    try(
            PreparedStatement ps =con.prepareStatement(sql);          
            ){
        ps.setString(1, TenMon );
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            ThucDon td = new ThucDon();
            td.setID(rs.getString(1));
            td.setTenMonAn(rs.getString(2));
            td.setDonGia( rs.getDouble(3));
            td.setDonVi(rs.getString(4));
            
            return td;
        }
        return null;
    }
    }
    public NhanVien timkiemnv(int MaNV) throws Exception{
        
    String sql ="select * from tblnhanvien where MaNV=?"; 
    try(
            PreparedStatement ps =con.prepareStatement(sql);          
            ){
        ps.setInt(1, MaNV );
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt(1));
            nv.setTenNV(rs.getString(2));
            
            return nv;
        }
        return null;
    }


    }
    public boolean luuhoadon(HoaDon hd){
        String sql ="insert into tblhoadon (MaHoaDon ,  ThoiGian, TongTien, TienTraLai, TienKhachDua, MaKhachHang,MaNV) Values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps =con.prepareStatement(sql);        
            ps.setString(1, hd.getMaHoaDon());
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            String date=sdf.format(hd.getThoiGian());
            ps.setString(2, date);
            ps.setDouble(3, hd.getTongTien());
            ps.setDouble(4, hd.getTienTraLai());
            ps.setDouble(5, hd.getTienKhachDua());    
            ps.setString(6, hd.getMaKhachHang());
            ps.setInt(7, hd.getMaNhanVien());
            return ps.executeUpdate()>0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
    public boolean luukhachhang(KhachHang kh){
        String sql ="insert into tblkhachhang (MaKhachHang ,  TenKhachHang, SoDienThoai) Values(?,?,?)";
        try{
            PreparedStatement ps =con.prepareStatement(sql);        
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getTenKhachHang());
            ps.setInt(3, kh.getSoDienThoai());                     
            return ps.executeUpdate()>0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
    public boolean luuchitiethoadon(ChiTietHoaDon cthd){
        String sql ="insert into tblchitiethoadon (MaHoaDon, MaMonAN,SoLuong) Values(?,?,?)";
        try{
            PreparedStatement ps =con.prepareStatement(sql);        
            ps.setString(1, cthd.getMaHoaDon());
            ps.setString(2, cthd.getMaMonAn());
            ps.setInt(3, cthd.getSoLuong());
           
            return ps.executeUpdate()>0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
    
}
