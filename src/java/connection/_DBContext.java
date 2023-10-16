package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Helper;

public class _DBContext {

    protected Connection connection;

    private String HOSTNAME = "localhost";
    private String PORT = "3306";
    private Properties properties = Helper.getPropertiesByFileName("constant/const.properties");

    public _DBContext() {
        try {
            String url = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + properties.getProperty("database.name");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, properties.getProperty("database.username"), properties.getProperty("database.password"));
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {// tcst
        Properties properties = Helper.getPropertiesByFileName("constant/const.properties");
        System.out.println(properties.getProperty("database.name"));
        System.out.println(new _DBContext().getConnection());
    }

}
