package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    //=================================
    public static DbConnection instance;
    private Connection connection;
    private String Driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/pharmacie";
    private String user = "root";
    private String password = "";
    //=================================


    public DbConnection() throws SQLException {
        try {
            Class.forName(Driver);
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Vous êtes connecté");
        }catch (Exception e) {
            e.getStackTrace();
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static DbConnection getInstance()throws SQLException{
        if (instance==null){
            instance=new DbConnection();
        }else if (instance.getConnection().isClosed()){
            instance=new DbConnection();
        }
        return instance;
    }
    public static void main(String[] args) throws SQLException {
        DbConnection.getInstance().getConnection();
    }
}