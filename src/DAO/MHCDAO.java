/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Tan
 */
import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import quanliquanan.ChiTietHoaDon;
import quanliquanan.HoaDon;
import quanliquanan.KhachHang;
import quanliquanan.NhanVien;
import quanliquanan.ManHinhChinh;
import quanliquanan.ThucDon;

/**
 *
 * @author Tan
 */
public class MHCDAO {
    /**
     * @param args the command line arguments
     */
   private Connection con;
    
    public MHCDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlquanan?useSSl=flase","root","tanvip01@");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ThucDon> getListThucDon() {
        ArrayList<ThucDon> listtd =new ArrayList<>();
        String sql =" select * from tblthucdon";
        try{
             PreparedStatement ps =con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                ThucDon td = new ThucDon();
                td.setID(rs.getString(1));
                td.setTenMonAn(rs.getString(2));
                td.setDonGia(rs.getDouble(3));
                td.setDonVi(rs.getString(4));
                td.setHinh(rs.getString(5));
                listtd.add(td);
            }            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listtd;
        }
    public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> listcthd =new ArrayList<>();
        String sql =" select * from tblchitiethoadon";
        try{
             PreparedStatement ps =con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaHoaDon(rs.getString(1));
                cthd.setMaMonAn(rs.getString(2));
                cthd.setSoLuong(rs.getInt(3));
                listcthd.add(cthd);
            }            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listcthd;
        }
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> listhd =new ArrayList<>();
        String sql =" select * from tblhoadon";
        try{
             PreparedStatement ps =con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setThoiGian(rs.getDate(2));
                hd.setTongTien(rs.getDouble(3));
                hd.setTienKhachDua(rs.getDouble(4));
                hd.setTienTraLai(rs.getDouble(5));
                hd.setMaKhachHang(rs.getString(6));
                hd.setMaNhanVien(rs.getInt(7));
                listhd.add(hd);
            }            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
        }
    public ArrayList<KhachHang> getListKhachHang() {
        ArrayList<KhachHang> listkh =new ArrayList<>();
        String sql =" select * from tblkhachhang";
        try{
             PreparedStatement ps =con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString(1));
                kh.setTenKhachHang(rs.getString(2));
                kh.setSoDienThoai(rs.getInt(3));              
                listkh.add(kh);
            }            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listkh;
        }
    public ArrayList<NhanVien> getListNhanVien() {
        ArrayList<NhanVien> list =new ArrayList<>();
        String sql =" select * from tblnhanvien";
        try{
             PreparedStatement ps =con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTenNV(rs.getString(2));
                nv.setSDT(rs.getInt(3));
                nv.setGioiTinh(rs.getInt(4));
                nv.setSoCMND(rs.getInt(5));
                nv.setDiaChi(rs.getString(6));
                nv.setLoai(rs.getString(7));
                list.add(nv);
            }            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
    public  boolean themtd(ThucDon td){
        String sql="insert into tblthucdon(MaMonAn,TenMon,DonGia,DonVi,Hinh) values (?,?,?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, td.getID());
            ps.setString(2, td.getTenMonAn());
            ps.setDouble(3, td.getDonGia());
            ps.setString(4, td.getDonVi());
            ps.setString(5, td.getHinh());
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean themnv(NhanVien nv){
        String sql ="insert into tblnhanvien (MaNV ,TenNV, SDT, GioiTinh, SoCMND, DiaChi, Loai) Values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps =con.prepareStatement(sql);        
            ps.setInt(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setInt(3, nv.getSDT());
            ps.setInt(4, nv.getGioiTinh());
            ps.setInt(5, nv.getSoCMND());
            ps.setString(6, nv.getDiaChi());
            ps.setString(7, nv.getLoai());
            return ps.executeUpdate()>0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
    
    public boolean updatenv(NhanVien nv) throws Exception{
        String sql ="update tblnhanvien set TenNV=?,SDT=?,GioiTinh =?,SoCMND=?,DiaChi=?,Loai=? where MaNV=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setInt(7, nv.getMaNV());
                ps.setString(1, nv.getTenNV());
                ps.setInt(2,nv.getSDT());
                ps.setInt(3, nv.getGioiTinh());
                ps.setInt(4, nv.getSoCMND());
                ps.setString(5, nv.getDiaChi());
                ps.setString(6, nv.getLoai());
                return ps.executeUpdate()>0;
        }
    }
    public boolean updatetd(ThucDon td) throws Exception{
        String sql ="update tblthucdon set TenMon=?,DonGia=?,DonVi=?,Hinh =? where MaMonAn=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setString(5, td.getID());
                ps.setString(1, td.getTenMonAn());
                ps.setDouble(2,td.getDonGia());
                ps.setString(3, td.getDonVi());
                ps.setString(4, td.getHinh());
                return ps.executeUpdate()>0;
        }
    }
    public NhanVien timkiem(String MaSV) throws Exception{
        String sql ="select * from tblnhanvien where manv=?"; 
        try(
                PreparedStatement ps =con.prepareStatement(sql);          
                ){
            
            ps.setInt(1,Integer.parseInt(MaSV) );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTenNV(rs.getString(2));
                nv.setSDT(rs.getInt(3));
                nv.setGioiTinh(rs.getInt(4));
                nv.setSoCMND(rs.getInt(5));
                nv.setDiaChi(rs.getString(6));
                nv.setLoai(rs.getString(7));
                return nv;
            }
            return null;
        }   
    }
    public HoaDon timkiemhd(String MaHoaDOn) throws Exception{
        String sql ="select * from tblhoadon where MaHoaDon=?"; 
        try(
                PreparedStatement ps =con.prepareStatement(sql);          
                ){
            ps.setString(1, MaHoaDOn );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setThoiGian(rs.getDate(2));
                hd.setTongTien(rs.getDouble(3));
                hd.setTienKhachDua(rs.getDouble(4));
                hd.setTienTraLai(rs.getDouble(5));   
                hd.setMaKhachHang(rs.getString(6));
                hd.setMaNhanVien(rs.getInt(7));
                return hd;
            }
            return null;
        }       
    }
    public  ArrayList<ChiTietHoaDon>  timkiemcthd( String MaHoaDon, String MaMonAn) throws Exception{
        ArrayList<ChiTietHoaDon> listcttdtk =new ArrayList<>();
        String sql ="select * from tblchitiethoadon where MaHoaDon=? and MaMonAn=?"; 
        try(
                PreparedStatement ps =con.prepareStatement(sql);          
                ){
            ps.setString(1,MaHoaDon);
            ps.setString(2, MaMonAn);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaHoaDon(rs.getString(1));
                cthd.setMaMonAn(rs.getString(2));
                cthd.setSoLuong(rs.getInt(3));           
                listcttdtk.add(cthd);
            }        
        }  
         return listcttdtk;
    }
    public  ArrayList<KhachHang>  timkiemkh(String TenKhachHang) throws Exception{
        ArrayList<KhachHang> listkh =new ArrayList<>();
        String sql ="select * from tblkhachhang where TenKhachHang=?"; 
        try(
                PreparedStatement ps =con.prepareStatement(sql);          
                ){          
            ps.setString(1,TenKhachHang);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString(1));
                kh.setTenKhachHang(rs.getString(2));
                kh.setSoDienThoai(rs.getInt(3));                       
                listkh.add(kh);
            }          
        }  
         return listkh;
    }
    
    public ThucDon timkiemtd(String ID) throws Exception{
        
    String sql ="select * from tblthucdon where MaMonAn=?"; 
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
            td.setDonVi(rs.getString(4));
            td.setHinh(rs.getString(5));
            
            return td;
        }
        return null;
    }
    }
    public boolean delete(String MaSV) throws Exception{
        String sql ="delete from tblnhanvien where MaNV=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setInt(1, Integer.parseInt(MaSV));
                return ps.executeUpdate()>0;      
        }
    }
    public boolean deletehd(String MaHD) throws Exception{
        String sql ="delete from tblhoadon where MaHoaDon=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setString(1, MaHD);
                return ps.executeUpdate()>0;      
        }
    }
    public boolean deleteHDnhanvien(int MaNV) throws Exception{
        String sql ="delete from tblhoadon where MaNV=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setInt(1, MaNV);
                return ps.executeUpdate()>0;      
        }
    }
    public boolean deleteCTHDnhanvien(int MaNV) throws Exception{
        String sql ="DELETE FROM tblchitiethoadon USING tblhoadon, tblchitiethoadon,tblnhanvien WHERE tblhoadon.MaHoaDon = tblchitiethoadon.MaHoaDon AND tblnhanvien.MaNV=tblhoadon.MaNV and tblnhanvien.MaNV=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setInt(1, MaNV);
                return ps.executeUpdate()>0;      
        }
    }
    public boolean deletecthd(String MaHD) throws Exception{
        String sql ="delete from tblchitiethoadon where MaHoaDon=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
               
                ps.setString(1, MaHD);
                return ps.executeUpdate()>0;      
        }
    }
    public boolean deletechitiethoadontd(String ID) throws Exception{
        String sql ="delete from tblchitiethoadon where MaMonAn=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setString(1, ID);
                return ps.executeUpdate()>0;      
        }
    }
    public boolean deletetd(String ID) throws Exception{
        String sql ="delete from tblthucdon where MaMonAn=?";
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setString(1, ID);
                return ps.executeUpdate()>0;      
        }
    }
    public static void main(String[] args) {
        new MHCDAO();
    }
}

