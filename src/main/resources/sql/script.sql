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
	isRemove BIT NOT NULL DEFAULT 0,
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
    doiTuong NVARCHAR(30),
	isRemove BIT NOT NULL DEFAULT 0
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
	isRemove BIT NOT NULL DEFAULT 0,
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
    FOREIGN KEY (maGa) REFERENCES Ga(maGa)
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
	isRemove BIT NOT NULL DEFAULT 0,
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
	isRemove BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (maLoaiToaTau) REFERENCES LoaiToaTau(maLoaiToaTau)
);

CREATE TABLE KhoangTau (
    maKhoangTau NVARCHAR(20) PRIMARY KEY,
    soKhoang INT NOT NULL,
	isRemove BIT NOT NULL DEFAULT 0
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
	isRemove BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (maTang) REFERENCES Tang(maTang),
    FOREIGN KEY (maLoaiGhe) REFERENCES LoaiGhe(maLoaiGhe),
    FOREIGN KEY (maKhoangTau) REFERENCES KhoangTau(maKhoangTau),
    FOREIGN KEY (maToaTau) REFERENCES ToaTau(maToaTau),
);

CREATE TABLE GheTrenChuyenTau (
    maGheTrenChuyenTau NVARCHAR(20) PRIMARY KEY,
    maChuyenTau NVARCHAR(20) NOT NULL,
    maGheNgoi NVARCHAR(20) NOT NULL,
    giaTienGhe DECIMAL(18,2) DEFAULT 0,
    trangThaiGhe NVARCHAR(10) NOT NULL DEFAULT N'còn trống'
        CHECK (trangThaiGhe IN (N'đã bán', N'còn trống')),
	isRemove BIT NOT NULL DEFAULT 0,
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
    dieuKienApDungKhuyenMai NVARCHAR(100),
	isRemove BIT NOT NULL DEFAULT 0
);

CREATE TABLE DoiTuongGiamGia (
    maDoiTuongGiamGia NVARCHAR(20) PRIMARY KEY,
    tenDoiTuongGiamGia NVARCHAR(50) NOT NULL,
    giaTriPhanTramGiamGia DECIMAL(5,2),
    trangThaiGiamGia NVARCHAR(20),
	isRemove BIT NOT NULL DEFAULT 0
);

-- ======================================
-- BẢNG HÓA ĐƠN - Loại Hóa Dơn 
-- ======================================
CREATE TABLE LoaiHoaDon (
    maLoaiHoaDon NVARCHAR(20) PRIMARY KEY,
	tenLoaiHoaDon nvarchar(100) not null,
	ghiChu nvarchar(100),
);

CREATE TABLE HoaDon (
    maHoaDon NVARCHAR(20) PRIMARY KEY,
	maLoaiHoaDon nvarchar(20) not null,
    maNhanVien NVARCHAR(20) NOT NULL,
	tenKhachHangThanhToan nvarchar(100) not null,
	emailKhachHangThanhToan NVARCHAR(100) NOT NULL,
    cccdKhachHangThanhToan NVARCHAR(15) not null,
	sdtKhachHangThanhToan NVARCHAR(20) NOT NULL,
    ngayThanhToan DATETIME DEFAULT GETDATE() NOT NULL,
    tongTien DECIMAL(18,2) DEFAULT 0,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
	FOREIGN KEY (maLoaiHoaDon) REFERENCES LoaiHoaDon(maLoaiHoaDon),
);

ALTER TABLE HoaDon
ADD diaChiKhachHangThanhToan NVARCHAR(255);
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
    trangThaiDoiVe NVARCHAR(20) DEFAULT N'chưa đổi'
        CHECK (trangThaiDoiVe IN (N'đã đổi', N'chưa đổi')),
    trangThaiVe NVARCHAR(20) DEFAULT N'hoạt động'
        CHECK (trangThaiVe IN (N'hoạt động', N'đã hoàn trả', N'kết thúc')),
    maChuyenTau NVARCHAR(20) NOT NULL,
    maKhachHang NVARCHAR(20) NOT NULL,
    maKhuyenMai NVARCHAR(20) NULL,
    maDoiTuongGiamGia NVARCHAR(20) NOT NULL,
	isRemove BIT NOT NULL DEFAULT 0,
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
	loaiHoaDonChoVeTau NVARCHAR(20) check (loaiHoaDonChoVeTau in (N'vé cá nhân',N'vé tập thể')),
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
(N'NV001', N'Nguyễn Văn A', N'CV01', '1985-07-15', N'123 Lê Lợi, TP HCM', N'vana@example.com', N'0901234567', '2022‑01‑10', N'còn làm', N'Nam', N'012345678901'),
(N'NV002', N'Trần Thị B', N'CV02', '1990-03-22', N'45 Nguyễn Huệ, TP HCM', N'thib@example.com', N'0912345678', '2021‑05‑20', N'còn làm', N'Nữ', N'098765432109'),
(N'NV003', N'Lê Văn C', N'CV03', '1978-11-02', N'78 Hai Bà Trưng, TP HCM', N'levanc@example.com', N'0923456789', '2020‑09‑01', N'còn làm', N'Nam', N'011223344556');

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

--Add them
INSERT INTO ChuyenTau (maChuyenTau, maTau, maHanhTrinh, ngayGioDi, ngayGioDen, giaCuocTrenChuyenTau) VALUES
(N'CT008', N'T001', N'HT001', '2025-10-27 06:00:00', '2025-10-27 14:00:00', 510000),
(N'CT009', N'T002', N'HT002', '2025-10-28 07:30:00', '2025-10-28 15:30:00', 530000),
(N'CT010', N'T001', N'HT001', '2025-10-29 08:00:00', '2025-10-29 16:00:00', 550000),
(N'CT011', N'T002', N'HT002', '2025-10-30 09:00:00', '2025-10-30 17:00:00', 570000),
(N'CT012', N'T001', N'HT001', '2025-10-31 10:00:00', '2025-10-31 18:00:00', 590000),
(N'CT013', N'T002', N'HT002', '2025-11-01 06:30:00', '2025-11-01 14:30:00', 600000),
(N'CT014', N'T001', N'HT001', '2025-11-02 07:00:00', '2025-11-02 15:00:00', 620000),
(N'CT015', N'T002', N'HT002', '2025-11-03 08:30:00', '2025-11-03 16:30:00', 640000),
(N'CT016', N'T001', N'HT001', '2025-11-04 09:00:00', '2025-11-04 17:00:00', 660000),
(N'CT017', N'T002', N'HT002', '2025-11-05 10:00:00', '2025-11-05 18:00:00', 680000),
(N'CT018', N'T001', N'HT001', '2025-11-06 06:00:00', '2025-11-06 14:00:00', 700000),
(N'CT019', N'T002', N'HT002', '2025-11-07 07:30:00', '2025-11-07 15:30:00', 720000),
(N'CT020', N'T001', N'HT001', '2025-11-08 08:00:00', '2025-11-08 16:00:00', 740000),
(N'CT021', N'T002', N'HT002', '2025-11-09 09:00:00', '2025-11-09 17:00:00', 760000),
(N'CT022', N'T001', N'HT001', '2025-11-10 10:00:00', '2025-11-10 18:00:00', 780000),
(N'CT023', N'T002', N'HT002', '2025-11-11 06:30:00', '2025-11-11 14:30:00', 800000),
(N'CT024', N'T001', N'HT001', '2025-11-12 07:00:00', '2025-11-12 15:00:00', 820000),
(N'CT025', N'T002', N'HT002', '2025-11-13 08:30:00', '2025-11-13 16:30:00', 840000),
(N'CT026', N'T001', N'HT001', '2025-11-14 09:00:00', '2025-11-14 17:00:00', 860000),
(N'CT027', N'T002', N'HT002', '2025-11-15 10:00:00', '2025-11-15 18:00:00', 880000);



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

INSERT INTO GheNgoi (maGheNgoi, maTang, maLoaiGhe, maKhoangTau, maToaTau, soGhe, giaTriTangThem, luuDong) VALUES
(N'Ghe004', N'T1', N'LG01', N'KT01', N'TT001', 3, 0, 0),
(N'Ghe005', N'T1', N'LG02', N'KT01', N'TT001', 4, 50000, 0),
(N'Ghe006', N'T2', N'LG03', N'KT02', N'TT002', 5, 150000, 0),
(N'Ghe007', N'T1', N'LG01', N'KT01', N'TT001', 6, 0, 0),
(N'Ghe008', N'T1', N'LG02', N'KT01', N'TT001', 7, 50000, 0),
(N'Ghe009', N'T2', N'LG03', N'KT02', N'TT002', 8, 150000, 0),
(N'Ghe010', N'T1', N'LG01', N'KT01', N'TT001', 9, 0, 0),
(N'Ghe011', N'T1', N'LG02', N'KT01', N'TT001', 10, 50000, 0),
(N'Ghe012', N'T2', N'LG03', N'KT02', N'TT002', 11, 150000, 0),
(N'Ghe013', N'T1', N'LG01', N'KT01', N'TT001', 12, 0, 0);

-- Dữ liệu mẫu cho bảng GheTrenChuyenTau
INSERT INTO GheTrenChuyenTau (maGheTrenChuyenTau, maChuyenTau, maGheNgoi, giaTienGhe, trangThaiGhe) VALUES
(N'GT001', N'CT001', N'Ghe001', 500000, N'còn trống'),
(N'GT002', N'CT001', N'Ghe002', 550000, N'đã bán'),
(N'GT003', N'CT002', N'Ghe003', 700000, N'còn trống');


INSERT INTO GheTrenChuyenTau (maGheTrenChuyenTau, maChuyenTau, maGheNgoi, giaTienGhe, trangThaiGhe) VALUES
(N'GT004', N'CT001', N'Ghe004', 500000, N'còn trống'),
(N'GT005', N'CT001', N'Ghe005', 550000, N'đã bán'),
(N'GT006', N'CT002', N'Ghe006', 700000, N'còn trống'),
(N'GT007', N'CT002', N'Ghe007', 520000, N'đã bán'),
(N'GT008', N'CT003', N'Ghe008', 600000, N'còn trống'),
(N'GT009', N'CT003', N'Ghe009', 580000, N'đã bán'),
(N'GT010', N'CT004', N'Ghe010', 610000, N'còn trống'),
(N'GT011', N'CT004', N'Ghe011', 630000, N'đã bán'),
(N'GT012', N'CT005', N'Ghe012', 650000, N'còn trống'),
(N'GT013', N'CT005', N'Ghe013', 670000, N'đã bán'),
(N'GT014', N'CT006', N'Ghe014', 690000, N'còn trống'),
(N'GT015', N'CT006', N'Ghe015', 710000, N'đã bán'),
(N'GT016', N'CT007', N'Ghe016', 730000, N'còn trống'),
(N'GT017', N'CT007', N'Ghe017', 750000, N'đã bán'),
(N'GT018', N'CT008', N'Ghe018', 770000, N'còn trống'),
(N'GT019', N'CT008', N'Ghe019', 790000, N'đã bán'),
(N'GT020', N'CT009', N'Ghe020', 810000, N'còn trống'),
(N'GT021', N'CT009', N'Ghe021', 830000, N'đã bán'),
(N'GT022', N'CT010', N'Ghe022', 850000, N'còn trống'),
(N'GT023', N'CT010', N'Ghe023', 870000, N'đã bán');

-- Dữ liệu mẫu cho bảng KhuyenMai, DoiTuongGiamGia
INSERT INTO KhuyenMai (maKhuyenMai, tenChuongTrinh, giaTriPhanTramKhuyenMai, ngayBatDau, ngayKetThuc, trangThaiKhuyenMai, dieuKienApDungKhuyenMai) VALUES
(N'KM01', N'Mùa hè rực rỡ', 10, '2025‑06‑01', '2025‑08‑31', N'kích hoạt', N'Tất cả vé'),
(N'KM02', N'Ưu đãi người lớn tuổi', 15, '2025‑09‑01', '2025‑12‑31', N'kết thúc', N'Người > 60 tuổi');

INSERT INTO DoiTuongGiamGia (maDoiTuongGiamGia, tenDoiTuongGiamGia, giaTriPhanTramGiamGia, trangThaiGiamGia) VALUES
(N'DT01', N'Học sinh – sinh viên', 5, N'kích hoạt'),
(N'DT02', N'Người lớn tuổi', 10, N'kích hoạt');

INSERT INTO LoaiHoaDon (maLoaiHoaDon, tenLoaiHoaDon, ghiChu)
VALUES
(N'LHD01', N'bán vé', N'Áp dụng cho hóa đơn bán vé'),
(N'LHD02', N'hoàn trả vé', N'Áp dụng cho hóa đơn hoàn trả vé'),
(N'LHD03', N'đổi vé', N'Áp dụng cho hóa đơn đổi vé');

-- Dữ liệu mẫu cho bảng HoaDon (đã thêm đầy đủ các trường)
INSERT INTO HoaDon (maHoaDon, maLoaiHoaDon, maNhanVien, tenKhachHangThanhToan,emailKhachHangThanhToan, cccdKhachHangThanhToan, sdtKhachHangThanhToan,diaChiKhachHangThanhToan,ngayThanhToan,tongTien)
VALUES
(N'HD001', N'LHD01', N'NV001', N'Nguyễn Văn A', N'nguyenvana@example.com', N'079123456789', N'0912345678', N'183A đường Trung An, Xã Trung An, Huyện Củ Chi, TPHCM', '2025-10-18 14:30:00', 500000),
(N'HD002', N'LHD01', N'NV002', N'Trần Thị B', N'tranthib@example.com', N'079987654321', N'0987654321', N'12 Đường Phan Xích Long, Phường 3, Quận Phú Nhuận, TPHCM', '2025-10-18 15:00:00', 700000),
(N'HD003', N'LHD03', N'NV002', N'Trần Thị B', N'tranthib@example.com', N'079987654321', N'0987654321', N'45 Nguyễn Văn Cừ, Phường 1, Quận 5, TPHCM', '2025-10-18 15:00:00', 700000);

-- Dữ liệu mẫu cho bảng LoaiTuongTacVe
INSERT INTO LoaiTuongTacVe (maLoaiTuongTac, tenLoaiTuongTac) VALUES
(N'LT01', N'bán'),
(N'LT02', N'hoàn trả'),
(N'LT03', N'đổi'),
(N'LT04', N'cấp lại vé');


-- them dataVE test --
-- Bảng Vé
INSERT INTO Ve (maVeTau, gaDi, gaDen, tenTau, ngayGioDi, ngayGioDen, soToa, soKhoang, soTang, soGhe, loaiVe, maGiayTo, giaVe, ghiChu, trangThaiDoiVe, trangThaiVe, maChuyenTau, maKhachHang, maKhuyenMai, maDoiTuongGiamGia, isRemove)
VALUES
(N'V001', N'Hà Nội', N'TP HCM', N'Super Express', '2025-10-20 08:00:00', '2025-10-20 18:00:00', 1, 1, 1, 1, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V002', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-10-21 22:00:00', '2025-10-22 06:00:00', 2, 1, 2, 1, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V003', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-01 08:00:00', '2025-11-01 18:00:00', 1, 2, 1, 2, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động',N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V004', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-02 22:00:00', '2025-11-03 06:00:00', 2, 1, 2, 2, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V005', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-05 08:00:00', '2025-11-05 18:00:00', 1, 1, 1, 3, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V006', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-06 22:00:00', '2025-11-07 06:00:00', 2, 1, 2, 3, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V007', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-07 08:00:00', '2025-11-07 18:00:00', 1, 1, 1, 4, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V008', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-08 22:00:00', '2025-11-09 06:00:00', 2, 1, 2, 4, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V009', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-09 08:00:00', '2025-11-09 18:00:00', 1, 2, 1, 5, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động',N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V010', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-10 22:00:00', '2025-11-11 06:00:00', 2, 1, 2, 5, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V011', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-11 08:00:00', '2025-11-11 18:00:00', 1, 1, 1, 6, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V012', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-12 22:00:00', '2025-11-13 06:00:00', 2, 1, 2, 6, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V013', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-13 08:00:00', '2025-11-13 18:00:00', 1, 1, 1, 7, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V014', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-14 22:00:00', '2025-11-15 06:00:00', 2, 1, 2, 7, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V015', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-15 08:00:00', '2025-11-15 18:00:00', 1, 1, 1, 8, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V016', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-16 22:00:00', '2025-11-17 06:00:00', 2, 1, 2, 8, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V017', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-17 08:00:00', '2025-11-17 18:00:00', 1, 2, 1, 9, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động',N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V018', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-18 22:00:00', '2025-11-19 06:00:00', 2, 1, 2, 9, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V019', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-19 08:00:00', '2025-11-19 18:00:00', 1, 1, 1, 10, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT02', 0),
(N'V020', N'Đà Nẵng', N'Nha Trang', N'Night Dreamer', '2025-11-20 22:00:00', '2025-11-21 06:00:00', 2, 1, 2, 10, N'Khứ hồi', NULL, 820000, NULL, N'chưa đổi', N'hoạt động', N'CT002', N'KH002', NULL, N'DT01', 0),
(N'V021', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-21 08:00:00', '2025-11-21 18:00:00', 1, 1, 1, 11, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH001', N'KM01', N'DT01', 0),
(N'V022', N'Hà Nội', N'TP HCM', N'Super Express', '2025-11-21 08:00:00', '2025-11-21 18:00:00', 1, 1, 1, 11, N'Một chiều', NULL, 550000, NULL, N'chưa đổi', N'hoạt động', N'CT001', N'KH002', N'KM01', N'DT02', 0);
-- Bảng ChiTietHoaDon (tương ứng)
INSERT INTO ChiTietHoaDon (maChiTietHoaDon, maHoaDon, maVeTau, moTa, donGia, giaTriThueVAT, thanhTien, loaiHoaDonChoVeTau)
VALUES
(N'CTHD001', N'HD001', N'V001', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD002', N'HD002', N'V002', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD003', N'HD001', N'V003', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD004', N'HD002', N'V004', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD005', N'HD001', N'V005', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD006', N'HD002', N'V006', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD007', N'HD001', N'V007', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD008', N'HD002', N'V008', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD009', N'HD001', N'V009', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD010', N'HD002', N'V010', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD011', N'HD001', N'V011', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD012', N'HD002', N'V012', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD013', N'HD001', N'V013', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD014', N'HD002', N'V014', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD015', N'HD001', N'V015', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD016', N'HD002', N'V016', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD017', N'HD001', N'V017', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD018', N'HD002', N'V018', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD019', N'HD001', N'V019', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD020', N'HD002', N'V020', N'Vé Night Dreamer VIP', 820000, 82000, 902000, N'vé cá nhân'),
(N'CTHD021', N'HD001', N'V021', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể'),
(N'CTHD022', N'HD001', N'V022', N'Vé Super Express 1 toa', 500000, 50000, 550000, N'vé tập thể');

-- Dữ liệu mẫu cho bảng LichSuTuongTacVe
INSERT INTO LichSuTuongTacVe (maTuongTac, maLoaiTuongTac, maVeTau, giaTriChenhLech, ngayTuongTac) VALUES
(N'TT001', N'LT01', N'V001', 0, '2025-10-18 14:30:00'), -- vé V001 được bán
(N'TT002', N'LT03', N'V001', 50000, '2025-10-19 10:00:00'), -- vé V001 đổi, chênh lệch giá 50,000
(N'TT003', N'LT04', N'V002', 0, '2025-10-20 09:00:00'), -- vé V002 cấp lại
(N'TT004', N'LT02', N'V002', -820000, '2025-10-21 12:00:00'); -- vé V002 hoàn trả, hoàn tiền

