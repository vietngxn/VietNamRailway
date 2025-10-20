--CREATE

--READ
    --khuyenMai
        select * from KhuyenMai


    --Chuyen Tau

        SELECT
            ct.maChuyenTau AS [Mã Chuyến],
            t.tenTau AS [Đầu Tàu],
            COUNT(DISTINCT tt.maToaTau) AS [Số lượng toa],
            SUM(CASE WHEN gtct.trangThaiGhe = N'còn trống' THEN 1 ELSE 0 END) AS [Vé Trống],
            ct.ngayGioDi AS [Thời Gian Khởi Hành],
            gaDi.tenGa AS [Ga đi],
            gaDen.tenGa AS [Ga đến]
        FROM ChuyenTau ct
        JOIN Tau t ON ct.maTau = t.maTau

        LEFT JOIN GheTrenChuyenTau gtct ON ct.maChuyenTau = gtct.maChuyenTau
        LEFT JOIN GheNgoi gn ON gtct.maGheNgoi = gn.maGheNgoi
        LEFT JOIN ToaTau tt ON gn.maToaTau = tt.maToaTau

        -- Lấy ga đầu tiên trong hành trình
        LEFT JOIN (
            SELECT htg.maHanhTrinh, g.tenGa
            FROM HanhTrinhGa htg
            JOIN Ga g ON htg.maGa = g.maGa
            WHERE htg.thuTuDung = (
                SELECT MIN(thuTuDung)
                FROM HanhTrinhGa htg2
                WHERE htg.maHanhTrinh = htg2.maHanhTrinh
            )
        ) gaDi ON gaDi.maHanhTrinh = ct.maHanhTrinh

        -- Lấy ga cuối cùng trong hành trình
        LEFT JOIN (
            SELECT htg.maHanhTrinh, g.tenGa
            FROM HanhTrinhGa htg
            JOIN Ga g ON htg.maGa = g.maGa
            WHERE htg.thuTuDung = (
                SELECT MAX(thuTuDung)
                FROM HanhTrinhGa htg2
                WHERE htg.maHanhTrinh = htg2.maHanhTrinh
            )
        ) gaDen ON gaDen.maHanhTrinh = ct.maHanhTrinh

        GROUP BY ct.maChuyenTau, t.tenTau, ct.ngayGioDi, gaDi.tenGa, gaDen.tenGa;

--UPDATE

--DELETE