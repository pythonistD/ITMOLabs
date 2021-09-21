package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DragonDataBase {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/Lab#7";
    static final String USER = "postgres";
    static final String PASS = "200201gd";
    static final String QUERY_FOR_ALL_DRAGONS = "SELECT id, name, age, speaking, x, y, tooth, type, owner FROM Dragons";
    static final String QUERY_GET_BY_ID = "SELECT id, name, coordinates, height, passportid, haircolor, nationality, location, owner FROM people WHERE id=";

    public void connectToDataBase(){
        try {
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
