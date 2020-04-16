package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String USERNAME="dba";
    private static final String PASSWORD="sql";
    private static final String CONN_STRING="jdbc:mysql://localhost:3306/musicalbums";
    private static Connection connection = null;
    private Database(){}

    public static Connection getConnection(){
        if(connection==null) createConnection();
        return connection;
    }

    public static void init() {
        try {
            createConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("create table artists(\n" +
                    "    id integer not null AUTO_INCREMENT,\n" +
                    "    name varchar(100) not null,\n" +
                    "    country varchar(100),\n" +
                    "    primary key (id)\n" +
                    ");");
            stmt.executeUpdate("create table albums(\n" +
                    "    id integer not null AUTO_INCREMENT,\n" +
                    "    name varchar(100) not null,\n" +
                    "    artist_id integer not null references artists on delete restrict,\n" +
                    "    release_year integer,\n" +
                    "    primary key (id)\n" +
                    ");");
            stmt.executeUpdate("create table chart(" +
                    " position integer not null references movies on delete restrict," +
                    " album_name varchar(100) not null references actors on delete restrict," +
                    " primary key (position, album_name)" +
                    ");");
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
    public static void createConnection(){
        try {
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        }
        catch(SQLException e){
            System.err.println("Create error");
            System.err.println(e);
        }
    }

    public static void closeConnection(){
        try {
            connection.close();
        }
        catch(SQLException e){
            System.err.println("Close error");
            System.err.println(e);
        }
    }

    public static void commit(){
        try{
            connection.setAutoCommit(false);
            connection.commit();
        }
        catch(SQLException e){
            System.err.println("Commit error");
            System.err.println(e);
        }
    }

    public static void rollback(){
        try{
            connection.rollback();
        }
        catch(SQLException e){
            System.err.println("Rollback error");
            System.err.println(e);
        }
    }
}
