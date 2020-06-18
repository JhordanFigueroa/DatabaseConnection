import java.sql.*;
import java.util.Properties;

public class Connector {

    private String db_url;
    private String db_name;
    private String db_user;
    private String db_pass;
    private String db_host;
    private String db_port;
    private String db_driver;
    Connection conn;
    Statement st;

    public Connector(Properties props, String pass) {
        db_name = props.getProperty("db_name");
        db_user = props.getProperty("db_user", "root");
        db_pass = pass;
        db_host = props.getProperty("db_host");
        db_port = props.getProperty("db_port");
        db_driver = "com.mysql.cj.jdbc.Driver";  //package of the driver in mysql  - connects to the server
        db_url = "jdbc:mysql://"+db_host+":"+db_port+"/"+db_name+"?serverTimezone=UTC";

        System.out.print(db_url);
    }

    public boolean connect() { //connect to the database
        try {
            Class.forName(db_driver);
            conn = DriverManager.getConnection(db_url, db_user, db_pass);
            st = conn.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if(conn == null) {
                return false;
            }

        }
        System.out.println("Connected to the database");
        return true;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return st.executeQuery(query);
    }
}
