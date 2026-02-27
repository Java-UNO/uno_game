package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBase {

    private String username;
    private String password;
    private String databaseUrl;

    public DataBase() {

        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("database.properties");
            props.load(fis);

            this.username = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            this.databaseUrl = props.getProperty("db.url");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}