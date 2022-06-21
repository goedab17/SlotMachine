package com.example.slotmachinestart.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
public class DB_Database {
    private  String DB_URL;
    private  String DB_DRIVER;
    private String DB_USER;
    private  String DB_PASSWORD;

    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException {
        initURL();
        Class.forName(DB_DRIVER);
    }

    public void initURL()
    {
        DB_Properties dbProperties = new DB_Properties();
        DB_DRIVER = dbProperties.getDb_driver();
        DB_URL = dbProperties.getDb_url();
        DB_USER = dbProperties.getDb_user();
        DB_PASSWORD = dbProperties.getDb_password();
    }

    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        initURL();
        System.out.println("connect");
        System.out.println(DB_URL+ DB_USER+ DB_PASSWORD);
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        cachedConnection = new DB_CachedConnection(connection);
    }

    public void disconnect() throws SQLException {

        if (connection != null) {
            connection.close();
            cachedConnection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection != null || cachedConnection != null) {
            throw new IllegalStateException("not connected to database");
        }
        return cachedConnection.getStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection != null || cachedConnection != null) {
            throw new IllegalStateException("not connected to database");
        }
        cachedConnection.releaseStatement(statement);

    }

//    public static void main(String[] args) {
//        try {
//            DB_Database database= new DB_Database();
//            try {
//
//                database.connect();
//                System.out.println("Connected");
//                DB_CachedConnection cachedConnection;
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}