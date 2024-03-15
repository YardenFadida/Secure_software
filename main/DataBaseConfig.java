import java.io.InputStream;

import java.util.Properties;

public class DataBaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DataBaseConfig.class.getClassLoader().getResourceAsStream("DB_cred.txt")) {
            if (input == null)
                System.out.println("unable to find config file");
            else
            	properties.load(input);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected static String getJdbcUrl() {
        return properties.getProperty("jdbcUrl");
    }

    protected static String getUsername() {
        return properties.getProperty("username");
    }

    protected static String getPassword() {
        return properties.getProperty("password");
    }
    
    protected static String getSalt() {
        return properties.getProperty("salt");
    }
}
