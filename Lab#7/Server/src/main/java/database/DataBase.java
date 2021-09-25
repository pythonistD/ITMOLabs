package database;

import java.sql.*;

public class DataBase {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Lab#7";
    static final String USER = "postgres";
    static final String PASS = "200201gd";
    static final String QUERY_FOR_ALL_DRAGONS = "SELECT id, name, age, wingspan, x, y, speaking, tooth, type FROM \"Dragons\"";
    static final String QUERY_GET_BY_ID = "SELECT id, name, age, wingspan, x, y, speaking, tooth, type FROM \"Dragons\" WHERE id=";

    public void showDragon(ResultSet res){
        try {
            while(res.next()){
                //Display values
                System.out.println("Name:" + res.getString("name"));
                System.out.println("Age:" + res.getString("age"));
                System.out.println("Wingspan:" + res.getString("wingspan"));
                System.out.println("X:" + res.getString("x"));
                System.out.println("Y:" + res.getString("y"));
                System.out.println("Speaking:" + res.getString("speaking"));
                System.out.println("Tooth:" + res.getString("tooth"));
                System.out.println("Type:" + res.getString("type"));
                System.out.println("ID:" + res.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getById(int id){
        ResultSet rs = null;
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(QUERY_GET_BY_ID+id);
            System.out.println(rs);
            System.out.println("База данных успешно подключена");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return rs;
    }










}
