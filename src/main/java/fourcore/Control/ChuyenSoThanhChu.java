package fourcore.Control;

public class ChuyenSoThanhChu {

    private static final String[] DON_VI = {
            "", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"
    };

    private static String docHangChuc(int chuc, int donVi, boolean coHangTram) {
        StringBuilder sb = new StringBuilder();

        if (chuc == 0) {
            if (donVi == 0) return "";
            if (coHangTram) sb.append("lẻ ");
            sb.append(DON_VI[donVi]);
        } else if (chuc == 1) {
            sb.append("mười ");
            if (donVi == 1) sb.append("một");
            else if (donVi == 5) sb.append("lăm");
            else if (donVi > 0) sb.append(DON_VI[donVi]);
        } else {
            sb.append(DON_VI[chuc]).append(" mươi ");
            if (donVi == 1) sb.append("mốt");
            else if (donVi == 5) sb.append("lăm");
            else if (donVi > 0) sb.append(DON_VI[donVi]);
        }

        return sb.toString().trim();
    }

    private static String docBaSo(int number, boolean laNhomCuoi, boolean coNhomTruoc) {
        int tram = number / 100;
        int chuc = (number % 100) / 10;
        int donVi = number % 10;
        StringBuilder sb = new StringBuilder();

        if (tram == 0 && chuc == 0 && donVi == 0) return "";

        if (tram > 0) {
            sb.append(DON_VI[tram]).append(" trăm ");
            sb.append(docHangChuc(chuc, donVi, true));
        } else {
            if (!laNhomCuoi && (chuc > 0 || donVi > 0) && coNhomTruoc)
                sb.append("không trăm ");
            sb.append(docHangChuc(chuc, donVi, tram > 0));
        }

        return sb.toString().trim();
    }

    public  String numberToVietnamese(long number) {
        if (number == 0) return "Không đồng";

        String[] donViNhom = {"", "nghìn", "triệu", "tỷ"};
        StringBuilder result = new StringBuilder();

        int i = 0;
        boolean coNhomTruoc = false;

        while (number > 0) {
            int baSo = (int) (number % 1000);
            boolean laNhomCuoi = (number < 1000);
            if (baSo != 0) {
                String group = docBaSo(baSo, laNhomCuoi, coNhomTruoc);
                if (!group.isEmpty()) {
                    result.insert(0, group + " " + donViNhom[i] + " ");
                }
                coNhomTruoc = true;
            } else {
                coNhomTruoc = coNhomTruoc || (number >= 1000 && number % 1000 == 0);
            }
            number /= 1000;
            i++;
        }

        String text = result.toString().trim().replaceAll("\\s+", " ");
        return text.substring(0, 1).toUpperCase() + text.substring(1) + " đồng";
    }

    public static void main(String[] args) {
    }
}