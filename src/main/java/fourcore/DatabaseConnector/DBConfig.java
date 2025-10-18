package fourcore.DatabaseConnector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Không tìm thấy file database.properties");
            }
            properties.load(input);
        } catch (IOException e) {

        }
    }

    public static String getUrl() {
        return properties.getProperty("database.url");
    }

    public static String getUsername() {
        return properties.getProperty("database.user");
    }

    public static String getPassword() {
        return properties.getProperty("database.password");
    }
}
