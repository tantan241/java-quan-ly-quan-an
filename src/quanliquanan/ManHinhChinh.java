/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquanan;

import java.awt.Color;
import quanliquanan.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DAO.MHCDAO;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author vuviettan
 */
public class ManHinhChinh extends javax.swing.JFrame {
    private ArrayList<NhanVien> list;
    private ArrayList<ThucDon> listtd;
    private ArrayList<HoaDon> listhd;
    private ArrayList<ChiTietHoaDon> listcthd;
    private ArrayList<ChiTietHoaDon> listcthdtk;
    private ArrayList<KhachHang> listkh;
    DefaultTableModel model;
    DefaultTableModel modeltd;
    DefaultTableModel modelhd;
    DefaultTableModel modelcthd;
    DefaultTableModel modelcthdtk;
    DefaultTableModel modelkh;
//    private byte[] personalImage;
    /**
     * Creates new form ManHinhChinh
     */
    public ManHinhChinh()  {
        initComponents();
        this.setLocationRelativeTo(null);
        listkh = new MHCDAO().getListKhachHang();
        modelkh =(DefaultTableModel) tblkhachhang.getModel();
        modelkh.setColumnIdentifiers(new Object[]{
            "STT","Mã khách hàng","Tên khách hàng","Số điện thoại"
        });
        list = new MHCDAO().getListNhanVien();
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setColumnIdentifiers(new Object[]{
            "STT","Mã Nhân Viên", "Tên Nhân Viên", "Số Điện Thoại", "Giới Tính", "Số CMND", "Địa Chỉ", "Loại"
        });
        listtd = new MHCDAO().getListThucDon();
        modeltd = (DefaultTableModel) tbThucDon.getModel();
        modeltd.setColumnIdentifiers(new Object[]{
            "STT","Mã Món Ăn", "Tên Món Ăn", "Đơn Giá","Đơn Vị","Hình"
        });
        listhd = new MHCDAO().getListHoaDon();
        modelhd = (DefaultTableModel) tbhoadon.getModel();
        modelhd.setColumnIdentifiers(new Object[]{
            "STT","Mã Hóa Đơn", "Thời Gian", "Tổng Tiền","Tiền Trả Lại","Tiền Khách Đưa","Mã Khách Hàng","Mã Nhân Viên"
        });
        listcthd =new MHCDAO().getListChiTietHoaDon();
        modelcthd = (DefaultTableModel) tbchitiethoadon.getModel();
        modelcthd.setColumnIdentifiers(new Object[]{
            "STT","Mã Hóa Đơn", "Mã Món Ăn","Số lượng"
        });
        listcthd =new MHCDAO().getListChiTietHoaDon();
        modelcthd = (DefaultTableModel) tbchitiethoadon.getModel();
        modelcthd.setColumnIdentifiers(new Object[]{
            "STT","Mã Hóa Đơn", "Mã Món Ăn","Số lượng"
        });
        showTable();
        showTabletd();
        showTablehd();
        showTablecthd();
        showTablekh();
        txtSoDienThoai.setDocument(new Chichonhapso());
        txtSoCMND.setDocument(new Chichonhapso());
        txtDonGia.setDocument(new Chichonhapso());
    }
    public void tabletotextv(){
        int i =tblNhanVien.getSelectedRow();
        NhanVien nv =list.get(i);
        txtMaNhanVien.setText(String.valueOf(nv.getMaNV()));
        txtTenNhanVien.setText(nv.getTenNV());
        rdNam.setSelected(nv.getGioiTinh()==1);
        rdNu.setSelected(nv.getGioiTinh()==0);
        txtSoDienThoai.setText(String.valueOf(nv.getSDT()));
        txtSoCMND.setText(String.valueOf(nv.getSoCMND()));
        txtDiaChi.setText(nv.getDiaChi());
        txtLoai.setText(nv.getLoai());
    }
     public void tabletotexthd(){
        int i =tbhoadon.getSelectedRow();
        HoaDon hd =listhd.get(i);
        txtMaHoaDontk.setText(hd.getMaHoaDon());
    }
     public void tabletotextcthd(){
        int i =tbchitiethoadon.getSelectedRow();
        ChiTietHoaDon cthd =listcthd.get(i);
        txtMaHoaDontk.setText(cthd.getMaHoaDon());
        txtManvtk.setText(cthd.getMaMonAn());
    }
    public void tabletotexttd(){
        int i =tbThucDon.getSelectedRow();
        ThucDon td = listtd.get(i);
        txtMaMonAn.setText(td.getID());
        txtTenMonAn.setText(td.getTenMonAn());
        txtDonGia.setText(String.valueOf(td.getDonGia()));
        txtDonVi.setText(td.getDonVi());
        txtlink.setText(td.getHinh());

        ImageIcon icon = new ImageIcon(txtlink.getText());
        Image image = icon.getImage().getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), Image.SCALE_SMOOTH);
        lbImage.setIcon(icon);
    }
    public void showTabletd() {
        int j=1;
        listtd = new MHCDAO().getListThucDon();
        for (ThucDon td: listtd) {
            modeltd.addRow(new Object[]{
                j++, td.getID(),td.getTenMonAn(),td.getDonGia(),td.getDonVi(),td.getHinh()
            });
        }
    }
    public void showTable() {
        int i=1;
        list = new MHCDAO().getListNhanVien();
        for (NhanVien nv: list) {
            model.addRow(new Object[]{
                i++, nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getGioiTinh(), nv.getSoCMND(),nv.getDiaChi() ,nv.getLoai()
            });
        }
    }
    public void showTablehd() {
        int t=1;
        listhd = new MHCDAO().getListHoaDon();
        for (HoaDon hd: listhd) {
            modelhd.addRow(new Object[]{
                t++, hd.getMaHoaDon(), hd.getThoiGian(), hd.getTongTien(), hd.getTienTraLai(), hd.getTienKhachDua(),hd.getMaKhachHang(),hd.getMaNhanVien()
            });
        }
    }
     public void showTablekh() {
        for (KhachHang kh: listkh) {
            modelkh.addRow(new Object[]{
                k++, kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoDienThoai()
            });
        }
    }
     public void showTablecthd() {
        int g=1;
        listcthd = new MHCDAO().getListChiTietHoaDon();
        for (ChiTietHoaDon cthd: listcthd) {
            modelcthd.addRow(new Object[]{
                g++, cthd.getMaHoaDon(), cthd.getMaMonAn(), cthd.getSoLuong()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtSoCMND = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhap = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtLoai = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaMonAn = new javax.swing.JTextField();
        txtTenMonAn = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbThucDon = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btnchonanhthucdon = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();
        txtlink = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnSuaThucDon = new javax.swing.JButton();
        btnXoaThucDon = new javax.swing.JButton();
        btnResetThucDon = new javax.swing.JButton();
        btnTimKiemThucDon = new javax.swing.JButton();
        btnThemThucDon = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtDonVi = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbhoadon = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtMaHoaDontk = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtManvtk = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbchitiethoadon = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtTenKHTK = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnthongke = new javax.swing.JButton();
        lbtonghoaodn = new javax.swing.JLabel();
        jdatetungay = new com.toedter.calendar.JDateChooser();
        jdatedenngay = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbtongtienthu = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbgoinhieu = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbgoinhieusolan = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbgoiitsolan = new javax.swing.JLabel();
        lbgoiit = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbtennvtk = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Quán Ăn");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Nhân Viên", "Tên Nhân Viên", "Số điện thoại", "Giới tính", "Số CMND", "Địa Chỉ", "Loại"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã Nhân Viên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên Nhân Viên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Giới Tính:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số Điện Thoại:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Số CMND:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Loại:");

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCapNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCapNhap.setText("Cập Nhập");
        btnCapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReset.setText("Nhâp mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(rdNam);
        rdNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdNu.setText("Nữ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(txtSoDienThoai)
                            .addComponent(txtSoCMND)
                            .addComponent(txtDiaChi)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtLoai)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdNam)
                            .addComponent(rdNu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mã Món Ăn:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tên Món Ăn:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Đơn Giá:");

        txtMaMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaMonAnActionPerformed(evt);
            }
        });

        txtTenMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenMonAnActionPerformed(evt);
            }
        });

        tbThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Món Ăn", "Tên Món Ăn", "Đơn Giá", "Đơn Vị", "Hình"
            }
        ));
        tbThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThucDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbThucDon);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Hình ảnh");

        btnchonanhthucdon.setText("Chọn ảnh");
        btnchonanhthucdon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonanhthucdonActionPerformed(evt);
            }
        });

        btnSuaThucDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSuaThucDon.setText("Cập nhập");
        btnSuaThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThucDonActionPerformed(evt);
            }
        });

        btnXoaThucDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaThucDon.setText("Xóa");
        btnXoaThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThucDonActionPerformed(evt);
            }
        });

        btnResetThucDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnResetThucDon.setText("Nhập mới");
        btnResetThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThucDonActionPerformed(evt);
            }
        });

        btnTimKiemThucDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimKiemThucDon.setText("Tìm Kiếm");
        btnTimKiemThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemThucDonActionPerformed(evt);
            }
        });

        btnThemThucDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemThucDon.setText("Thêm");
        btnThemThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThucDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiemThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnResetThucDon)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnXoaThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiemThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnSuaThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnResetThucDon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Đơn Vị:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenMonAn, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(txtMaMonAn, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(txtDonVi)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnchonanhthucdon)
                                .addGap(141, 141, 141))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtlink, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 32, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMaMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtlink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnchonanhthucdon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        jTabbedPane1.addTab("Thực Đơn", jPanel2);

        tbhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Thời Gian", "Tổng Tiền", "Tiền Trả Lại", "Tiền Khách Đưa", "Mã Khách Hàng", "Mã Nhân Viên"
            }
        ));
        tbhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhoadonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbhoadon);
        if (tbhoadon.getColumnModel().getColumnCount() > 0) {
            tbhoadon.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Hóa Đơn");

        jLabel15.setText("Mã Hóa Đơn:");

        jLabel16.setText("Mã Món Ăn:");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Tìm hóa đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Xóa hóa đơn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Tìm Chi tiết hóa đơn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHoaDontk)
                    .addComponent(txtManvtk, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jButton3)
                        .addGap(36, 36, 36)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(136, 136, 136))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHoaDontk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtManvtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbchitiethoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Món Ăn", "Số Lượng"
            }
        ));
        tbchitiethoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbchitiethoadonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbchitiethoadon);
        if (tbchitiethoadon.getColumnModel().getColumnCount() > 0) {
            tbchitiethoadon.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Chi tiết hoa đơn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        jTabbedPane1.addTab("Quản lí hóa đơn", jPanel5);

        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại"
            }
        ));
        jScrollPane5.setViewportView(tblkhachhang);

        jLabel17.setText("Tên khách hàng");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenKHTK, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTenKHTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        btnthongke.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthongke.setText("Thống kê");
        btnthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongkeActionPerformed(evt);
            }
        });

        jdatetungay.setDateFormatString("yyyy-MM-dd");

        jdatedenngay.setDateFormatString("yyyy-MM-dd");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Thống kê");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Từ ngày");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Đến ngày");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Tổng số hóa đơn đã lập:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Tổng tiền bán được:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Món ăn gọi nhiều nhất:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Số lần:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Món ăn  gọi ít nhất:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Số lần:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Nhân viên lập nhiều hóa đơn nhất:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jdatetungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdatedenngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbtongtienthu, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbtonghoaodn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(123, 123, 123))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbgoinhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbgoiit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbgoinhieusolan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbgoiitsolan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(lbtennvtk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jdatedenngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnthongke)
                                .addGap(15, 15, 15)
                                .addComponent(lbtonghoaodn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel21))))
                    .addComponent(jdatetungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel22)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel23))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbtongtienthu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(lbgoinhieusolan, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lbgoinhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbgoiitsolan, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27)
                        .addComponent(lbgoiit, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lbtennvtk, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khách hàng ", jPanel8);

        btnQuayLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 51, 51));
        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Bán hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(jButton1)
                        .addGap(67, 67, 67)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        dispose();
        DangNhap dangnhap = new DangNhap();
        dangnhap.show();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnSuaThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThucDonActionPerformed
        // TODO add your handling code here:
        if (txtMaMonAn.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã món ăn");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this,
                        "Bạn có muốn cập nhập thực đơn?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
        if(hoi==JOptionPane.YES_OPTION){
            try{
                ThucDon td= new ThucDon();
                td.setID(txtMaMonAn.getText());
                td.setTenMonAn(txtTenMonAn.getText());
                td.setDonGia(Double.parseDouble(txtDonGia.getText()));   
                td.setDonVi(txtDonVi.getText());
                td.setHinh(txtlink.getText());
                MHCDAO dao = new MHCDAO();
                dao.updatetd(td);
                JOptionPane.showMessageDialog(rootPane, "Cập nhập thành công");
                int i=0;
                while(i<tbThucDon.getRowCount()){
                    modeltd.removeRow(i);}
                showTabletd();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, "Lỗi!Mời bạn điền lại thông tin");
            }   
        }
        if(hoi==JOptionPane.NO_OPTION){
            return;  
        }  
        
    }//GEN-LAST:event_btnSuaThucDonActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaNhanVien.setText("");
        txtDiaChi.setText("");
        txtSoCMND.setText("");
        txtSoDienThoai.setText("");
        txtTenNhanVien.setText("");
        txtLoai.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        
        if (txtMaNhanVien.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã nhân viên");
            return;
        }
        
               
        NhanVien nv= new NhanVien();
        nv.setMaNV((int) Float.parseFloat(txtMaNhanVien.getText()));
        nv.setTenNV(txtTenNhanVien.getText());
        nv.setSDT((int) Float.parseFloat(txtSoDienThoai.getText()));   
        nv.setGioiTinh(rdNam.isSelected()? 1:0);
        nv.setSoCMND((int) Float .parseFloat(txtSoCMND.getText()));
        nv.setDiaChi(txtDiaChi.getText());
        nv.setLoai(txtLoai.getText());
        if(new MHCDAO().themnv(nv)){
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
            list.add(nv); 
        } 
        else{
            JOptionPane.showMessageDialog(rootPane, "Student's ID cannot be duplicated!");
        }     
        
        showResult();
    }//GEN-LAST:event_btnThemActionPerformed
    int i =1,j=1,k= 1 ,g=1,f=1,t=1;

    public void showResult() {
        NhanVien nv = list.get(list.size() - 1);
        model.addRow(new Object[]{
            tblNhanVien.getRowCount()+1, nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getGioiTinh(), nv.getSoCMND(),nv.getDiaChi() ,nv.getLoai()
        });
    }
    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        tabletotextv();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        
        if(txtMaNhanVien.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã nhân viên");
            return;
        }
        try{
            
            MHCDAO dao =new MHCDAO();
            NhanVien nv=dao.timkiem(txtMaNhanVien.getText());
            if (nv !=null){
                txtTenNhanVien.setText(nv.getTenNV());
                txtSoDienThoai.setText(String.valueOf(nv.getSDT()));
                rdNam.setSelected(nv.getGioiTinh()==1);
                rdNu.setSelected(nv.getGioiTinh()==0);
                txtSoCMND.setText(String.valueOf(nv.getSoCMND()));
                txtDiaChi.setText(nv.getDiaChi());
                txtLoai.setText(nv.getLoai());
            }
            else{
                 JOptionPane.showMessageDialog(rootPane, "Nhân viên không tồn tại");  
            }
        }       
        catch(Exception e){ 
            JOptionPane.showMessageDialog(this, "ERROR"+e.getMessage());
            e.printStackTrace();   
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        // TODO add your handling code here:
        StringBuilder tb = new StringBuilder();
        if (txtMaNhanVien.getText().equals("")){
            tb.append("Vui lòng tìm kiếm nhân viên cần sửa");
        }
        if (tb.length()>0){
            JOptionPane.showMessageDialog(this, tb);
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this,
                        "Bạn có muốn cập nhập nhân viên?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
        if(hoi==JOptionPane.YES_OPTION){
            try{
                NhanVien nv= new NhanVien();
                nv.setMaNV(Integer.parseInt(txtMaNhanVien.getText()));
                nv.setTenNV(txtTenNhanVien.getText());
                nv.setSDT(Integer.parseInt(txtSoDienThoai.getText()));   
                nv.setGioiTinh(rdNam.isSelected()? 1:0);
                nv.setSoCMND(Integer.parseInt(txtSoCMND.getText()));
                nv.setDiaChi(txtDiaChi.getText());
                nv.setLoai(txtLoai.getText());
                MHCDAO dao = new MHCDAO();
                dao.updatenv(nv);
                JOptionPane.showMessageDialog(rootPane, "Cập nhập thành công");
                int i=0;
                while(i<tblNhanVien.getRowCount()){
                    model.removeRow(i);}
                showTable();
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, "Lỗi!Mời bạn điền lại thông tin");
            }   
        }
        if(hoi==JOptionPane.NO_OPTION){
            return;  
        }            
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        StringBuilder tb = new StringBuilder();
        if (txtMaNhanVien.getText().equals("")){
            tb.append("Vui lòng tìm kiếm nhân viên cần xóa");
        }
        if (tb.length()>0){
            JOptionPane.showMessageDialog(this, tb);
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn xóa nhân viên?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
        if(hoi==JOptionPane.YES_OPTION){
            try{
              MHCDAO dao = new MHCDAO();
              dao.deleteCTHDnhanvien(Integer.parseInt(txtMaNhanVien.getText()));
              dao.deleteHDnhanvien(Integer.parseInt(txtMaNhanVien.getText()));
              dao.delete(txtMaNhanVien.getText());             
              JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
              int i=0;
              while(i<tblNhanVien.getRowCount()){
                    model.removeRow(i);}
              showTable();
              while(i<tbhoadon.getRowCount()){
                    modelhd.removeRow(i);}
              showTablehd();
              while(i<tbchitiethoadon.getRowCount()){
                    modelcthd.removeRow(i);}
              showTablecthd();
            }
            catch(Exception e){
              JOptionPane.showMessageDialog(rootPane, "Nhân viên không tồn tại");
            } 
        }
        if(hoi==JOptionPane.NO_OPTION){
            return;  
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTenMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenMonAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMonAnActionPerformed

    private void btnchonanhthucdonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonanhthucdonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(jPanel1)==JFileChooser.CANCEL_OPTION){
            return;
        }
        
        File f =chooser.getSelectedFile();
        String Filename = f.getAbsolutePath();
        txtlink.setText(Filename);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(Filename);
        Image image = icon.getImage().getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), Image.SCALE_SMOOTH);
        lbImage.setIcon(icon);
        
    }//GEN-LAST:event_btnchonanhthucdonActionPerformed

    private void btnThemThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThucDonActionPerformed
        // TODO add your handling code here:
        String image =txtlink.getText();
        image=image.replace("\\","\\\\");
        
        if (txtMaMonAn.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã món ăn");
            return;
        }      
        ThucDon td= new ThucDon();
        td.setID(txtMaMonAn.getText());
        td.setTenMonAn(txtTenMonAn.getText());
        td.setDonGia( Double.parseDouble(txtDonGia.getText()));  
        td.setDonVi(txtDonVi.getText());
        td.setHinh(image);
        
        if(new MHCDAO().themtd(td)){
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
            listtd.add(td);
             
        } 
        else{
            JOptionPane.showMessageDialog(rootPane, "Mã đã tồn tại");
        }      
        showResulttd();
    }//GEN-LAST:event_btnThemThucDonActionPerformed
    

    public void showResulttd() {
        ThucDon td = listtd.get(listtd.size() - 1);
        modeltd.addRow(new Object[]{
            tbThucDon.getRowCount()+1, td.getID(), td.getTenMonAn(), td.getDonGia(),td.getDonVi(),td.getHinh()
        });
    }
    private void btnResetThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetThucDonActionPerformed
        // TODO add your handling code here:
        txtMaMonAn.setText("");
        txtTenMonAn.setText("");
        txtDonGia.setText("");
        txtDonVi.setText("");
        lbImage.setText(null);
        txtlink.setText("");
        
    }//GEN-LAST:event_btnResetThucDonActionPerformed

    private void btnTimKiemThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemThucDonActionPerformed
        // TODO add your handling code here:
         if(txtMaMonAn.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã Món ăn");
            return;
        }
        try{
            
            MHCDAO dao =new MHCDAO();
            ThucDon td=dao.timkiemtd(txtMaMonAn.getText());
            if (td !=null){
                txtMaMonAn.setText(td.getID());
                txtTenMonAn.setText(td.getTenMonAn());
                txtDonGia.setText(Double.toString(td.getDonGia()));
                txtDonVi.setText(td.getDonVi());
                txtlink.setText(td.getHinh());

                ImageIcon icon = new ImageIcon(txtlink.getText());
                Image image = icon.getImage().getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), Image.SCALE_SMOOTH);
                lbImage.setIcon(icon);
                
            }
            else{
                 JOptionPane.showMessageDialog(rootPane, "Món ăn không tồn tại");  
            }
        }       
        catch(Exception e){ 
            JOptionPane.showMessageDialog(this, "ERROR"+e.getMessage());
            e.printStackTrace();   
        }
    }//GEN-LAST:event_btnTimKiemThucDonActionPerformed

    private void btnXoaThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThucDonActionPerformed
        // TODO add your handling code here:
        StringBuilder tb = new StringBuilder();
        if (txtMaMonAn.getText().equals("")){
            tb.append("Vui lòng tìm kiếm món ăn cần xóa");
        }
        if (tb.length()>0){
            JOptionPane.showMessageDialog(this, tb);
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn xóa món ăn không?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
        if(hoi==JOptionPane.YES_OPTION){
            try{
              MHCDAO dao = new MHCDAO();
              dao.deletechitiethoadontd(txtMaMonAn.getText());
              dao.deletetd(txtMaMonAn.getText());
              JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
              int i=0;
              while(i<tbThucDon.getRowCount()){
                    modeltd.removeRow(i);}
              showTabletd();
            }
            catch(Exception e){
              JOptionPane.showMessageDialog(rootPane, "Món ăn không tồn tại");
            } 
        }
        if(hoi==JOptionPane.NO_OPTION){
            return;  
        }
    }//GEN-LAST:event_btnXoaThucDonActionPerformed

    private void txtMaMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaMonAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMonAnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        ManHinhBanHang mhbh= new ManHinhBanHang();
        mhbh.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        ManHinhChinh mhc= new ManHinhChinh();
        mhc.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThucDonMouseClicked
        // TODO add your handling code here:
        tabletotexttd();
    }//GEN-LAST:event_tbThucDonMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelhdtk = new DefaultTableModel();
        modelhdtk =(DefaultTableModel)tbhoadon.getModel();
        try{
            MHCDAO dao =new MHCDAO();
            HoaDon hd=dao.timkiemhd(txtMaHoaDontk.getText());
            int i=0;
            while(i<tbhoadon.getRowCount()){
            modelhdtk.removeRow(i);}
            modelhdtk.addRow(new Object[]
            {     
             "1",
             hd.getMaHoaDon(),
             hd.getThoiGian(),
             hd.getTongTien(),
             hd.getTienKhachDua(),
             hd.getTienTraLai(),
             hd.getMaKhachHang()
            });  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy");
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel modelcthdtk = new DefaultTableModel();
        modelcthdtk =(DefaultTableModel)tbchitiethoadon.getModel();
        int i=0;
        while(i<tbchitiethoadon.getRowCount()){
            modelcthdtk.removeRow(i);}
        try {
            // TODO add your handling code here:
            listcthdtk =new MHCDAO().timkiemcthd( txtMaHoaDontk.getText(),txtManvtk.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy");
        }
        modelcthdtk = (DefaultTableModel) tbchitiethoadon.getModel();

        modelcthd.setColumnIdentifiers(new Object[]{
            "STT", "Mã Hóa Đơn", "Mã Món Ăn","Số lượng"
        });
        for (ChiTietHoaDon cthd: listcthdtk) {
            modelcthdtk.addRow(new Object[]{
                f++, cthd.getMaHoaDon(), cthd.getMaMonAn(), cthd.getSoLuong()
            });
        }     
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhoadonMouseClicked
        // TODO add your handling code here:
        tabletotexthd();
    }//GEN-LAST:event_tbhoadonMouseClicked

    private void tbchitiethoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbchitiethoadonMouseClicked
        // TODO add your handling code here:
        tabletotextcthd();
    }//GEN-LAST:event_tbchitiethoadonMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn  không?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
        if(hoi==JOptionPane.YES_OPTION){
            try{
              MHCDAO dao = new MHCDAO();
              dao.deletecthd(txtMaHoaDontk.getText());
              dao.deletehd(txtMaHoaDontk.getText());
              JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
              int i=0;
              while(i<tbhoadon.getRowCount()){
                    modelhd.removeRow(i);}
              showTablehd();
              while(i<tbchitiethoadon.getRowCount()){
                    modelcthd.removeRow(i);}
              showTablecthd();
            }
            catch(Exception e){
              JOptionPane.showMessageDialog(rootPane, "Hóa đơn không tồn tại");
            } 
        }
        if(hoi==JOptionPane.NO_OPTION){
            return;  
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelkh = new DefaultTableModel();
        modelkh =(DefaultTableModel)tblkhachhang.getModel();
        int i=0;
        while(i<tblkhachhang.getRowCount()){
            modelkh.removeRow(i);}
        try {
            // TODO add your handling code here:
            listkh =new MHCDAO().timkiemkh(txtTenKHTK.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Không tìm thấy");
        }
        modelkh = (DefaultTableModel) tblkhachhang.getModel();

        modelkh.setColumnIdentifiers(new Object[]{
            "STT","Mã khách hàng", "Tên khách hàng", "Số điện thoại"
        });
        for (KhachHang kh: listkh) {
            modelkh.addRow(new Object[]{
                f++, kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoDienThoai()
            });
        }     
    }//GEN-LAST:event_btnTimActionPerformed

    public void thongke(){
        try{
            HoaDon hd = new HoaDon();
            ChiTietHoaDon cthd =new ChiTietHoaDon();
            NhanVien nv = new NhanVien();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlquanan?useSSl=flase","root","tanvip01@");
            String datetungay =((JTextField)jdatetungay.getDateEditor().getUiComponent()).getText();
            String datedenngay =((JTextField)jdatedenngay.getDateEditor().getUiComponent()).getText();
            Statement stm= con.createStatement();
            String sql ="select count(*) from tblhoadon where ThoiGian >= '"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null ";
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){            
            hd.setDemSoHoaDon(rs.getInt(1));
            lbtonghoaodn.setText(Integer.toString(hd.getDemSoHoaDon()));
            }            
            Statement stm1= con.createStatement();
            String sql1 ="SELECT Sum(TongTien)  FROM tblhoadon where ThoiGian >= '"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null ";
            ResultSet rs1 = stm1.executeQuery(sql1);
            if(rs1.next()){
            hd.setTongTien(rs1.getDouble(1));
            lbtongtienthu.setText(Double.toString(hd.getTongTien()));
            } 
            
            Statement stm3= con.createStatement();
            String sql3 ="SELECT count(*) FROM tblchitiethoadon join tblhoadon" +
                         " where tblhoadon.MaHoaDon=tblchitiethoadon.MaHoaDon" +
                         " and tblhoadon.ThoiGian >='"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null " +
                         " GROUP BY MaMonAn" +
                         " HAVING COUNT(*) >= ALL ( SELECT COUNT(*) FROM tblchitiethoadon  GROUP BY MaMonAn)";
            ResultSet rs3 = stm3.executeQuery(sql3);
            if(rs3.next()){
            hd.setDemSoHoaDon(rs3.getInt(1));
            lbgoinhieusolan.setText(Integer.toString(hd.getDemSoHoaDon()));}
                    
            Statement stm2= con.createStatement();
            String sql2 ="SELECT MaMonAn FROM tblchitiethoadon join tblhoadon" +
                         " where tblhoadon.MaHoaDon=tblchitiethoadon.MaHoaDon" +
                         " and tblhoadon.ThoiGian >='"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null " +
                         " GROUP BY MaMonAn" +
                         " HAVING COUNT(*) >= ALL ( SELECT COUNT(*) FROM tblchitiethoadon  GROUP BY MaMonAn)";
            ResultSet rs2 = stm2.executeQuery(sql2);
            if(rs2.next() ){            
                cthd.setMaMonAn(rs2.getString(1));
                lbgoinhieu.setText(cthd.getMaMonAn());
                System.out.println(rs2.getRow());
            } 
            if(rs2.getRow()>1){
                lbgoinhieu.setText("Có nhiều món");
            }
            
            Statement stm4= con.createStatement();
            String sql4 ="SELECT MaMonAn FROM tblchitiethoadon join tblhoadon" +
                         " where tblhoadon.MaHoaDon=tblchitiethoadon.MaHoaDon" +
                         " and tblhoadon.ThoiGian >='"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null " +
                         " GROUP BY MaMonAn" +
                         " HAVING COUNT(*) <= ALL ( SELECT COUNT(*) FROM tblchitiethoadon  GROUP BY MaMonAn)";
            ResultSet rs4 = stm4.executeQuery(sql4);
            if(rs4.next()){            
            cthd.setMaMonAn(rs4.getString(1));
            lbgoiit.setText(cthd.getMaMonAn());
            }         
            
            Statement stm5= con.createStatement();
            String sql5 ="SELECT count(*) FROM tblchitiethoadon join tblhoadon" +
                         " where tblhoadon.MaHoaDon=tblchitiethoadon.MaHoaDon" +
                         " and tblhoadon.ThoiGian >='"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null " +
                         " GROUP BY MaMonAn" +
                         " HAVING COUNT(*) <= ALL ( SELECT COUNT(*) FROM tblchitiethoadon  GROUP BY MaMonAn)";
            ResultSet rs5 = stm5.executeQuery(sql5);
            if(rs5.next()){
            hd.setDemSoHoaDon(rs5.getInt(1));
            lbgoiitsolan.setText(Integer.toString(hd.getDemSoHoaDon()));}
            
            
            Statement stm6= con.createStatement();
            String sql6 ="SELECT TenNV FROM tblnhanvien join tblhoadon" +
                         " where tblhoadon.MaNV=tblnhanvien.MaNV" +
                         " and tblhoadon.ThoiGian >='"+datetungay+"' and ThoiGian <=  '"+datedenngay+"' and MaKhachHang is not null " +
                         " GROUP BY tblhoadon.MaNV" +
                         " HAVING COUNT(*) >= ALL ( SELECT COUNT(*) FROM tblhoadon  GROUP BY MaNV)";
            ResultSet rs6 = stm6.executeQuery(sql6);
            if(rs6.next() && rs6.getRow()==1){
                    nv.setTenNV(rs6.getString(1));
                    lbtennvtk.setText(nv.getTenNV());
            }
            else{
                lbtennvtk.setText("Có nhiều nhân viên");
            }
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showConfirmDialog(rootPane, "hhhh");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(rootPane, "kkkk");
        }
    }
    private void btnthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongkeActionPerformed
        thongke();
      
            // TODO add your handling code here:
   
    }//GEN-LAST:event_btnthongkeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new ManHinhChinh().setVisible(true);            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhap;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetThucDon;
    private javax.swing.JButton btnSuaThucDon;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThucDon;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemThucDon;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaThucDon;
    private javax.swing.JButton btnchonanhthucdon;
    private javax.swing.JButton btnthongke;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdatedenngay;
    private com.toedter.calendar.JDateChooser jdatetungay;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbgoiit;
    private javax.swing.JLabel lbgoiitsolan;
    private javax.swing.JLabel lbgoinhieu;
    private javax.swing.JLabel lbgoinhieusolan;
    private javax.swing.JLabel lbtennvtk;
    private javax.swing.JLabel lbtonghoaodn;
    private javax.swing.JLabel lbtongtienthu;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbThucDon;
    private javax.swing.JTable tbchitiethoadon;
    private javax.swing.JTable tbhoadon;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblkhachhang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtLoai;
    private javax.swing.JTextField txtMaHoaDontk;
    private javax.swing.JTextField txtMaMonAn;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtManvtk;
    private javax.swing.JTextField txtSoCMND;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKHTK;
    private javax.swing.JTextField txtTenMonAn;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtlink;
    // End of variables declaration//GEN-END:variables
}
