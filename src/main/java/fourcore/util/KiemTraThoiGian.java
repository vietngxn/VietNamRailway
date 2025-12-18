package fourcore.util;
import java.time.Duration;
import java.time.LocalDateTime;
public class KiemTraThoiGian {
    public static boolean isMoreThan24Hours(LocalDateTime input) {
        LocalDateTime now = LocalDateTime.now();
        long hours = Duration.between(now, input).toHours();
        return hours > 24;
    }

}
