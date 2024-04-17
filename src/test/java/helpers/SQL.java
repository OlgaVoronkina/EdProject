package helpers;
import com.jcraft.jsch.Session;

import java.sql.*;
import java.util.Collection;

public class SQL extends FrameworkConfigurator{
    public static Connection connection;
    public static Collection collection;
    public static Statement statement;
    public static Session session;
    public  ResultSet resultSet;
    public String value;
    public static  String URL;
    public static String USERNAME;
    public static String PASSWORD;

    public void Connect(){
        if (count=="1"){    //если есть разные БД
            URL = ""; //
            USERNAME = ""; //
            PASSWORD = ""; //
        }
        if (count=="1") {
            URL = ""; //
            USERNAME = ""; //
            PASSWORD = ""; //
        }
    }

    public void startConnection(String SQL) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement =connection.createStatement();
        resultSet =  statement.executeQuery(SQL);
    }

    public void updateConnection(String SQL) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement =connection.createStatement();
        statement.executeUpdate(SQL);
    }

}
