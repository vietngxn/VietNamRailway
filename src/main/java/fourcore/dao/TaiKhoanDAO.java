package fourcore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fourcore.DatabaseConnector.DatabaseConnector;
import fourcore.Entity.ChucVu;
import fourcore.Entity.NhanVien;
import fourcore.Entity.TaiKhoan;

public class TaiKhoanDAO {

    DatabaseConnector databaseConnector = new DatabaseConnector();
    Statement st = databaseConnector.connect();

    public TaiKhoanDAO() throws SQLException {
    }

    public String getMatKhau(String tenDangNhap) throws SQLException
    {
        String sql = "SELECT tenDangNhap, matKhau FROM TaiKhoan WHERE tenDangNhap = '" + tenDangNhap + "'";


        ResultSet rs= st.executeQuery(sql);
        String matKhau = "";
        while(rs.next())
        {
            matKhau = rs.getString(2);
        }
        return matKhau;

    }

    public NhanVien getNhanVien(String tenDangNhap) throws SQLException
    {
        String sql = "SELECT nv.* FROM NhanVien nv "
                + "JOIN TaiKhoan tk ON nv.maNhanVien = tk.maNhanVien "
                + "WHERE tk.tenDangNhap = '" + tenDangNhap + "'";
        ResultSet rs = st.executeQuery(sql);
        NhanVien nv = null;
        while(rs.next())
        {
            String manv = rs.getString(1);
            String hoten = rs.getString(2);
            String maChucVu = rs.getString(3);
            LocalDate ngaySinh = LocalDate.parse(rs.getString(4));
            String diaChi = rs.getString(5);
            String email = rs.getString(6);
            String sdt = rs.getString(7);
            LocalDate ngayVaoLam = LocalDate.parse(rs.getString(8));
            String tinhTrang = rs.getString(9);
            String gioiTinh = rs.getString(10);
            String cccd = rs.getString(11);

            ChucVu cv = new ChucVu(maChucVu);
            nv = new NhanVien(manv, hoten, cv, ngaySinh, diaChi, email, sdt, null, tinhTrang, gioiTinh, cccd);

        }
        return nv;
    }
    
    
    public int getSoLuongNhanVien() throws SQLException {
        String sql = "SELECT COUNT(*) AS SoLuong FROM NhanVien";
        ResultSet rs = st.executeQuery(sql);
        int sl = 0;
        if (rs.next()) {
            sl = rs.getInt("SoLuong");
        }
        return sl;
    }

    
    public boolean themTaiKhoan(TaiKhoan tk) {
        try {
            String sql = "INSERT INTO TaiKhoan (maTaiKhoan, maNhanVien, tenDangNhap, matKhau, isRemove) VALUES ('"
                    + tk.getMaTaiKhoan() + "', '"
                    + tk.getMaNhanVien() + "', '"
                    + tk.getTenDangNhap() + "', '"
                    + tk.getMatKhau() + "', "
                    + tk.getIsRemove() + ")";

            int rows = st.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean capNhatIsRemove(String maTaiKhoan, int isRemove) {
        try {
            String sql = "UPDATE TaiKhoan SET isRemove = " + isRemove
                       + " WHERE maTaiKhoan = '" + maTaiKhoan + "'";

            int rows = st.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public TaiKhoan getTaiKhoanTheoMaNhanVien(String sdt) throws SQLException {
        String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = '" + sdt + "'";
        ResultSet rs = st.executeQuery(sql);

        TaiKhoan tk = null;
        if (rs.next()) {
            tk = new TaiKhoan(
                rs.getString("maTaiKhoan"),
                rs.getString("maNhanVien"),
                rs.getString("tenDangNhap"),
                rs.getString("matKhau"),
                rs.getInt("isRemove")
            );
        }
        return tk;
    }



}