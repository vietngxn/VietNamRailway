-- ======================================
-- TẠO DATABASE
-- ======================================
CREATE DATABASE QuanLyVeTauDB;
GO
USE QuanLyVeTauDB;
GO

-- ======================================
-- BẢNG NHÂN VIÊN & TÀI KHOẢN
-- ======================================
CREATE TABLE ChucVu (
    maChucVu NVARCHAR(20) PRIMARY KEY,
    tenChucVu NVARCHAR(20)
);

CREATE TABLE NhanVien (
    maNhanVien NVARCHAR(20) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    maChucVu NVARCHAR(20) NOT NULL,
    ngaySinh DATE,
    diaChi NVARCHAR(50),
    email NVARCHAR(100),
    sdt NVARCHAR(15),
    ngayVaoLam DATE DEFAULT GETDATE(),
    tinhTrangLamViec NVARCHAR(15) DEFAULT N'còn làm' CHECK (tinhTrangLamViec IN (N'còn làm', N'đã nghỉ')),
    gioiTinh NVARCHAR(10),
    cccd NVARCHAR(15),
    FOREIGN KEY (maChucVu) REFERENCES ChucVu(maChucVu)
);

CREATE TABLE TaiKhoan (
    maTaiKhoan NVARCHAR(20) PRIMARY KEY,
    maNhanVien NVARCHAR(20) NOT NULL,
    tenDangNhap NVARCHAR(50) NOT NULL,
    matKhau NVARCHAR(100) NOT NULL,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

-- ======================================
-- BẢNG KHÁCH HÀNG
-- ======================================
CREATE TABLE KhachHang (
    maKhachHang NVARCHAR(20) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    sdt NVARCHAR(20) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    cccd NVARCHAR(15),
    passport NVARCHAR(15),
    doiTuong NVARCHAR(30)
);

-- ======================================
-- BẢNG LOẠI TÀU & TÀU
-- ======================================
CREATE TABLE LoaiTau (
    maLoaiTau NVARCHAR(20) PRIMARY KEY,
    tenLoaiTau NVARCHAR(100) NOT NULL,
    giaCuoc DECIMAL(18,2) NOT NULL DEFAULT 0
);

CREATE TABLE Tau (
    maTau NVARCHAR(20) PRIMARY KEY,
    tenTau NVARCHAR(100) NOT NULL,
    maLoaiTau NVARCHAR(20) NOT NULL,
    FOREIGN KEY (maLoaiTau) REFERENCES LoaiTau(maLoaiTau)
);

-- ======================================
-- BẢNG LOẠI GHẾ
-- ======================================
CREATE TABLE LoaiGhe (
    maLoaiGhe NVARCHAR(20) PRIMARY KEY,
    tenLoaiGhe NVARCHAR(50) NOT NULL,
    giaLoaiGhe DECIMAL(10,2) DEFAULT 0
);

-- ======================================
-- BẢNG HÀNH TRÌNH - GA (N-N)
-- ======================================
CREATE TABLE HanhTrinh (
    maHanhTrinh NVARCHAR(20) PRIMARY KEY,
    tenHanhTrinh NVARCHAR(200) NOT NULL
);

CREATE TABLE Ga (
    maGa NVARCHAR(20) PRIMARY KEY,
    tenGa NVARCHAR(100) NOT NULL,
    cuLy DECIMAL(10,2) DEFAULT 0,
    thoiGian DATETIME DEFAULT GETDATE()
);

CREATE TABLE HanhTrinhGa (
    maHanhTrinhGa NVARCHAR(20) PRIMARY KEY,
    maHanhTrinh NVARCHAR(20) NOT NULL,
    maGa NVARCHAR(20) NOT NULL,
    thuTuDung INT DEFAULT 0,
    FOREIGN KEY (maHanhTrinh) REFERENCES HanhTrinh(maHanhTrinh),
    FOREIGN KEY (maGa) REFERENCES Ga(maGa),
    CONSTRAINT UQ_HanhTrinhGa UNIQUE (maHanhTrinh, maGa)
);

-- ======================================
-- CHUYẾN TÀU
-- ======================================
CREATE TABLE ChuyenTau (
    maChuyenTau NVARCHAR(20) PRIMARY KEY,
    maTau NVARCHAR(20) NOT NULL,
    maHanhTrinh NVARCHAR(20) NOT NULL,
    ngayGioDi DATETIME NOT NULL,
    ngayGioDen DATETIME NOT NULL,
    giaCuocTrenChuyenTau DECIMAL(18,2) DEFAULT 0,
    FOREIGN KEY (maTau) REFERENCES Tau(maTau),
    FOREIGN KEY (maHanhTrinh) REFERENCES HanhTrinh(maHanhTrinh)
);

-- ======================================
-- TOA – KHOANG – TẦNG – GHẾ
-- ======================================
CREATE TABLE LoaiToaTau (
    maLoaiToaTau NVARCHAR(20) PRIMARY KEY,
    tenLoaiToa NVARCHAR(50) NOT NULL
);

CREATE TABLE ToaTau (
    maToaTau NVARCHAR(20) PRIMARY KEY,
    maLoaiToaTau NVARCHAR(20) NOT NULL,
    tenToaTau NVARCHAR(100) NOT NULL,
    soToa INT,
    FOREIGN KEY (maLoaiToaTau) REFERENCES LoaiToaTau(maLoaiToaTau)
);

CREATE TABLE KhoangTau (
    maKhoangTau NVARCHAR(20) PRIMARY KEY,
    soKhoang INT NOT NULL,
);

CREATE TABLE Tang (
    maTang NVARCHAR(20) PRIMARY KEY,
    soTang INT NOT NULL,
    giaTang DECIMAL(10,2) DEFAULT 0
);

CREATE TABLE GheNgoi (
    maGheNgoi NVARCHAR(20) PRIMARY KEY,
    maTang NVARCHAR(20) NOT NULL,
    maLoaiGhe NVARCHAR(20) NOT NULL,
    maKhoangTau NVARCHAR(20) NOT NULL,
    maToaTau NVARCHAR(20) NOT NULL,
    soGhe INT NOT NULL DEFAULT 0,
    giaTriTangThem DECIMAL(18,2) DEFAULT 0,
    luuDong BIT DEFAULT 0,
    FOREIGN KEY (maTang) REFERENCES Tang(maTang),
    FOREIGN KEY (maLoaiGhe) REFERENCES LoaiGhe(maLoaiGhe),
    FOREIGN KEY (maKhoangTau) REFERENCES KhoangTau(maKhoangTau),
    FOREIGN KEY (maToaTau) REFERENCES ToaTau(maToaTau),
    CONSTRAINT UQ_GheNgoi UNIQUE (maTang, soGhe)
);

CREATE TABLE GheTrenChuyenTau (
    maGheTrenChuyenTau NVARCHAR(20) PRIMARY KEY,
    maChuyenTau NVARCHAR(20) NOT NULL,
    maGheNgoi NVARCHAR(20) NOT NULL,
    giaTienGhe DECIMAL(18,2) DEFAULT 0,
    trangThaiGhe NVARCHAR(10) NOT NULL DEFAULT N'còn trống'
        CHECK (trangThaiGhe IN (N'đã bán', N'còn trống')),
    FOREIGN KEY (maGheNgoi) REFERENCES GheNgoi(maGheNgoi),
    FOREIGN KEY (maChuyenTau) REFERENCES ChuyenTau(maChuyenTau)
);

-- ======================================
-- BẢNG KHUYẾN MÃI
-- ======================================
CREATE TABLE KhuyenMai (
    maKhuyenMai NVARCHAR(20) PRIMARY KEY,
    tenChuongTrinh NVARCHAR(100),
    giaTriPhanTramKhuyenMai DECIMAL(5,2) DEFAULT 0,
    ngayBatDau DATETIME,
    ngayKetThuc DATETIME,
    trangThaiKhuyenMai NVARCHAR(20) DEFAULT N'kích hoạt'
        CHECK (trangThaiKhuyenMai IN (N'kích hoạt', N'kết thúc')),
    dieuKienApDungKhuyenMai NVARCHAR(100)
);

CREATE TABLE DoiTuongGiamGia (
    maDoiTuongGiamGia NVARCHAR(20) PRIMARY KEY,
    tenDoiTuongGiamGia NVARCHAR(50) NOT NULL,
    giaTriPhanTramGiamGia DECIMAL(5,2),
    trangThaiGiamGia NVARCHAR(20)
);

-- ======================================
-- BẢNG HÓA ĐƠN
-- ======================================
CREATE TABLE HoaDon (
    maHoaDon NVARCHAR(20) PRIMARY KEY,
    maNhanVien NVARCHAR(20) NOT NULL,
    ngayThanhToan DATETIME DEFAULT GETDATE() NOT NULL,
    tongTien DECIMAL(18,2) DEFAULT 0,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

-- ======================================
-- BẢNG VÉ TÀU
-- ======================================
CREATE TABLE Ve (
    maVeTau NVARCHAR(20) PRIMARY KEY,
    gaDi NVARCHAR(100) NOT NULL,
    gaDen NVARCHAR(100) NOT NULL,
    tenTau NVARCHAR(100),
    ngayGioDi DATETIME2 NOT NULL,
    ngayGioDen DATETIME2 NOT NULL,
    soToa INT CHECK (soToa >= 0),
    soKhoang INT CHECK (soKhoang >= 0),
    soTang INT CHECK (soTang >= 0),
    soGhe INT CHECK (soGhe >= 0),
    loaiVe NVARCHAR(50),
    maGiayTo NVARCHAR(50),
    giaVe DECIMAL(18,2) DEFAULT 0,
    ghiChu NVARCHAR(255),
    trangThaiDoiVe NVARCHAR(10) DEFAULT N'chưa đổi'
        CHECK (trangThaiDoiVe IN (N'đã đổi', N'chưa đổi')),
    trangThaiVe NVARCHAR(10) DEFAULT N'hoạt động'
        CHECK (trangThaiVe IN (N'hoạt động', N'đã hoàn trả', N'kết thúc')),
    maChuyenTau NVARCHAR(20) NOT NULL,
    maKhachHang NVARCHAR(20) NOT NULL,
    maKhuyenMai NVARCHAR(20) NULL,
    maDoiTuongGiamGia NVARCHAR(20) NOT NULL,
    FOREIGN KEY (maChuyenTau) REFERENCES ChuyenTau(maChuyenTau),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKhuyenMai),
    FOREIGN KEY (maDoiTuongGiamGia) REFERENCES DoiTuongGiamGia(maDoiTuongGiamGia)
);

CREATE TABLE ChiTietHoaDon (
    maChiTietHoaDon NVARCHAR(20) PRIMARY KEY,
    maHoaDon NVARCHAR(20) NOT NULL,
    maVeTau NVARCHAR(20) NOT NULL,
    moTa NVARCHAR(200),
    donGia DECIMAL(18,2) DEFAULT 0,
    giaTriThueVAT DECIMAL(18,2) DEFAULT 0,
    thanhTien DECIMAL(18,2) DEFAULT 0,
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (maVeTau) REFERENCES Ve(maVeTau)
);

-- ======================================
-- LỊCH SỬ TƯƠNG TÁC VÉ
-- ======================================
CREATE TABLE LoaiTuongTacVe (
    maLoaiTuongTac NVARCHAR(20) PRIMARY KEY,
    tenLoaiTuongTac NVARCHAR(20)
        CHECK (tenLoaiTuongTac IN (N'bán', N'hoàn trả', N'đổi', N'cấp lại vé'))
);

CREATE TABLE LichSuTuongTacVe (
    maTuongTac NVARCHAR(20) PRIMARY KEY,
    maLoaiTuongTac NVARCHAR(20) NOT NULL,
    maVeTau NVARCHAR(20) NOT NULL,
    giaTriChenhLech DECIMAL(18,2) DEFAULT 0,
    ngayTuongTac DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (maLoaiTuongTac) REFERENCES LoaiTuongTacVe(maLoaiTuongTac),
    FOREIGN KEY (maVeTau) REFERENCES Ve(maVeTau)
);
GO
-- Dữ liệu mẫu cho bảng ChucVu
INSERT INTO ChucVu (maChucVu, tenChucVu) VALUES
(N'CV01', N'Nhân viên bán vé'),
(N'CV02', N'Kiểm soát vé'),
(N'CV03', N'Quản lý');

-- Dữ liệu mẫu cho bảng NhanVien
INSERT INTO NhanVien (maNhanVien, hoTen, maChucVu, ngaySinh, diaChi, email, sdt, ngayVaoLam, tinhTrangLamViec, gioiTinh, cccd) VALUES
(N'NV001', N'Nguyễn Văn A', N'CV01', '1985‑07‑15', N'123 Lê Lợi, TP HCM', N'vana@example.com', N'0901234567', '2022‑01‑10', N'còn làm', N'Nam', N'012345678901'),
(N'NV002', N'Trần Thị B', N'CV02', '1990‑03‑22', N'45 Nguyễn Huệ, TP HCM', N'thib@example.com', N'0912345678', '2021‑05‑20', N'còn làm', N'Nữ', N'098765432109'),
(N'NV003', N'Lê Văn C', N'CV03', '1978‑11‑02', N'78 Hai Bà Trưng, TP HCM', N'levanc@example.com', N'0923456789', '2020‑09‑01', N'còn làm', N'Nam', N'011223344556');

-- Dữ liệu mẫu cho bảng TaiKhoan
INSERT INTO TaiKhoan (maTaiKhoan, maNhanVien, tenDangNhap, matKhau) VALUES
(N'TK001', N'NV001', N'vana', N'pass@123'),
(N'TK002', N'NV002', N'thib', N'pass@456'),
(N'TK003', N'NV003', N'levanc', N'pass@789');

-- Dữ liệu mẫu cho bảng KhachHang
INSERT INTO KhachHang (maKhachHang, hoTen, sdt, email, cccd, passport, doiTuong) VALUES
(N'KH001', N'Phạm Hồng Dương', N'0934567890', N'duongph@example.com', N'015678912345', NULL, N'Người lớn'),
(N'KH002', N'Nguyễn Thị Lan', N'0945678901', N'lannt@example.com', N'016789123456', NULL, N'Trẻ em'),
(N'KH003', N'John Smith', N'+11234567890', N'john.smith@example.com', NULL, N'P1234567', N'Khách quốc tế');

-- Dữ liệu mẫu cho bảng LoaiTau
INSERT INTO LoaiTau (maLoaiTau, tenLoaiTau, giaCuoc) VALUES
(N'LT01', N'Tàu Thường', 50000),
(N'LT02', N'Tàu Cao Tốc', 150000),
(N'LT03', N'Tàu Ngủ Đêm', 250000);

-- Dữ liệu mẫu cho bảng Tau
INSERT INTO Tau (maTau, tenTau, maLoaiTau) VALUES
(N'T001', N'Super Express', N'LT02'),
(N'T002', N'Night Dreamer', N'LT03'),
(N'T003', N'Regional 1', N'LT01');

-- Dữ liệu mẫu cho bảng LoaiGhe
INSERT INTO LoaiGhe (maLoaiGhe, tenLoaiGhe, giaLoaiGhe) VALUES
(N'LG01', N'Ghế thường', 0),
(N'LG02', N'Ghế VIP', 50000),
(N'LG03', N'Giường ngủ', 150000);

-- Dữ liệu mẫu cho bảng HanhTrinh
INSERT INTO HanhTrinh (maHanhTrinh, tenHanhTrinh) VALUES
(N'HT001', N'Hà Nội ↔ TP HCM'),
(N'HT002', N'Đà Nẵng ↔ Nha Trang');

-- Dữ liệu mẫu cho bảng Ga
INSERT INTO Ga (maGa, tenGa, cuLy, thoiGian) VALUES
(N'G001', N'Hà Nội', 0, GETDATE()),
(N'G002', N'Vinh', 293, GETDATE()),
(N'G003', N'Đà Nẵng', 764, GETDATE()),
(N'G004', N'Nha Trang', 940, GETDATE()),
(N'G005', N'TP HCM', 1720, GETDATE());

-- Dữ liệu mẫu cho bảng HanhTrinhGa
INSERT INTO HanhTrinhGa (maHanhTrinhGa, maHanhTrinh, maGa, thuTuDung) VALUES
(N'HTG001', N'HT001', N'G001', 1),
(N'HTG002', N'HT001', N'G002', 2),
(N'HTG003', N'HT001', N'G005', 3),
(N'HTG004', N'HT002', N'G003', 1),
(N'HTG005', N'HT002', N'G004', 2);

-- Dữ liệu mẫu cho bảng ChuyenTau
INSERT INTO ChuyenTau (maChuyenTau, maTau, maHanhTrinh, ngayGioDi, ngayGioDen, giaCuocTrenChuyenTau) VALUES
(N'CT001', N'T001', N'HT001', '2025‑10‑20 08:00:00', '2025‑10‑20 18:00:00', 500000),
(N'CT002', N'T002', N'HT002', '2025‑10‑21 22:00:00', '2025‑10‑22 06:00:00', 700000);

-- Dữ liệu mẫu cho bảng LoaiToaTau, ToaTau, KhoangTau, Tang
INSERT INTO LoaiToaTau (maLoaiToaTau, tenLoaiToa) VALUES
(N'LTToa01', N'Toà thường'),
(N'LTToa02', N'Toà VIP');

INSERT INTO ToaTau (maToaTau, maLoaiToaTau, tenToaTau, soToa) VALUES
(N'TT001', N'LTToa01', N'Toà 1', 1),
(N'TT002', N'LTToa02', N'Toà 2', 2);

INSERT INTO KhoangTau (maKhoangTau, soKhoang) VALUES
(N'KT01', 1),
(N'KT02', 2);

INSERT INTO Tang (maTang, soTang, giaTang) VALUES
(N'T1', 1, 0),
(N'T2', 2, 50000);

-- Dữ liệu mẫu cho bảng GheNgoi
INSERT INTO GheNgoi (maGheNgoi, maTang, maLoaiGhe, maKhoangTau, maToaTau, soGhe, giaTriTangThem, luuDong) VALUES
(N'Ghe001', N'T1', N'LG01', N'KT01', N'TT001', 1, 0, 0),
(N'Ghe002', N'T1', N'LG02', N'KT01', N'TT001', 2, 50000, 0),
(N'Ghe003', N'T2', N'LG03', N'KT02', N'TT002', 1, 150000, 0);

-- Dữ liệu mẫu cho bảng GheTrenChuyenTau
INSERT INTO GheTrenChuyenTau (maGheTrenChuyenTau, maChuyenTau, maGheNgoi, giaTienGhe, trangThaiGhe) VALUES
(N'GT001', N'CT001', N'Ghe001', 500000, N'còn trống'),
(N'GT002', N'CT001', N'Ghe002', 550000, N'đã bán'),
(N'GT003', N'CT002', N'Ghe003', 700000, N'còn trống');

-- Dữ liệu mẫu cho bảng KhuyenMai, DoiTuongGiamGia
INSERT INTO KhuyenMai (maKhuyenMai, tenChuongTrinh, giaTriPhanTramKhuyenMai, ngayBatDau, ngayKetThuc, trangThaiKhuyenMai, dieuKienApDungKhuyenMai) VALUES
(N'KM01', N'Mùa hè rực rỡ', 10, '2025‑06‑01', '2025‑08‑31', N'kích hoạt', N'Tất cả vé'),
(N'KM02', N'Ưu đãi người lớn tuổi', 15, '2025‑09‑01', '2025‑12‑31', N'kết thúc', N'Người > 60 tuổi');

INSERT INTO DoiTuongGiamGia (maDoiTuongGiamGia, tenDoiTuongGiamGia, giaTriPhanTramGiamGia, trangThaiGiamGia) VALUES
(N'DT01', N'Học sinh – sinh viên', 5, N'kích hoạt'),
(N'DT02', N'Người lớn tuổi', 10, N'kích hoạt');

-- Dữ liệu mẫu cho bảng HoaDon
INSERT INTO HoaDon (maHoaDon, maNhanVien, ngayThanhToan, tongTien) VALUES
(N'HD001', N'NV001', '2025‑10‑18 14:30:00', 500000),
(N'HD002', N'NV002', '2025‑10‑18 15:00:00', 700000);

-- Dữ liệu mẫu cho bảng Ve
INSERT INTO Ve (maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe, maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, maChuyenTau, maKhachHang, maKhuyenMai, maDoiTuongGiamGia) VALUES
(N'V001', N'Hà Nội', N'TP HCM', N'Super Express', '2025‑10‑20 08:00:00', '2025‑10‑20 18:00:00', 1, 1, 1, 1, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02'),
(N'V002', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025‑10‑21 22:00:00', '2025‑10‑22 06:00:00', 2, 1, 2, 1, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01');

-- Dữ liệu mẫu cho bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maChiTietHoaDon, maHoaDon, maVeTau, moTa, donGia, giaTriThueVAT, thanhTien) VALUES
(N'CTHD001', N'HD001', N'V001', N'Ve Super Express 1 toa', 500000, 50000, 550000),
(N'CTHD002', N'HD002', N'V002', N'Ve Night Dreamer VIP', 820000, 82000, 902000);


-- Dữ liệu mẫu cho bảng LoaiTuongTacVe
INSERT INTO LoaiTuongTacVe (maLoaiTuongTac, tenLoaiTuongTac) VALUES
(N'LT01', N'bán'),
(N'LT02', N'hoàn trả'),
(N'LT03', N'đổi'),
(N'LT04', N'cấp lại vé');

-- Dữ liệu mẫu cho bảng LichSuTuongTacVe
INSERT INTO LichSuTuongTacVe (maTuongTac, maLoaiTuongTac, maVeTau, giaTriChenhLech, ngayTuongTac) VALUES
(N'TT001', N'LT01', N'V001', 0, '2025-10-18 14:30:00'), -- vé V001 được bán
(N'TT002', N'LT03', N'V001', 50000, '2025-10-19 10:00:00'), -- vé V001 đổi, chênh lệch giá 50,000
(N'TT003', N'LT04', N'V002', 0, '2025-10-20 09:00:00'), -- vé V002 cấp lại
(N'TT004', N'LT02', N'V002', -820000, '2025-10-21 12:00:00'); -- vé V002 hoàn trả, hoàn tiền


