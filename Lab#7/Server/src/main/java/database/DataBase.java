package database;

import model.Coordinates;
import model.Dragon;
import model.DragonHead;
import model.DragonType;

import java.sql.*;

public class DataBase {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Lab#7";
    static final String USER = "postgres";
    static final String PASS = "200201gd";
    static final String QUERY_FOR_ALL_DRAGONS = "SELECT id, name, age, wingspan, x, y, speaking, tooth, type, owner FROM \"Dragons\"";
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
    public static void getDataFromDatabase(){
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QUERY_GET_BY_ID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static synchronized void uploadDataFromDataBase(User user){
        String total = "";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_FOR_ALL_DRAGONS);
        ) {
            while(rs.next()){
                long id = rs.getInt("id");
                String name =  rs.getString("name");
                Long age = Long.parseLong(rs.getString("age"));
                Double wingspan = Double.parseDouble(rs.getString("wingspan"));
                Double x = Double.parseDouble(rs.getString("x"));
                Double y = Double.parseDouble(rs.getString("y"));
                Boolean speaking =  Boolean.parseBoolean(rs.getString("speaking"));
                Double tooth = Double.parseDouble(rs.getString("tooth"));
                DragonType dragonType = DragonType.valueOf(rs.getString("type"));
                String owner = rs.getString("owner");
                Dragon.getDragonsCollection().add(new Dragon(id,name,age,wingspan, speaking, new Coordinates(x,y),new DragonHead(tooth),dragonType,owner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }











}