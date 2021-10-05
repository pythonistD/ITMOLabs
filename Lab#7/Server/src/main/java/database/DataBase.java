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
    static final String QUERY_ADD_DRAGON = "INSERT INTO \"Dragons\" (name,age, wingspan, x, y, speaking, tooth, type, owner) values (?,?,?,?,?,?,?,?,?)";
    static final String DELETE_ELEMENT = "DELETE FROM \"Dragons\" WERE id = ?";

    public static void getDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QUERY_GET_BY_ID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static synchronized void uploadDataFromDataBase() {
        String total = "";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_FOR_ALL_DRAGONS);
        ) {
            while (rs.next()) {
                long id = rs.getInt("id");
                String name = rs.getString("name");
                Long age = Long.parseLong(rs.getString("age"));
                Double wingspan = Double.parseDouble(rs.getString("wingspan"));
                Double x = Double.parseDouble(rs.getString("x"));
                Double y = Double.parseDouble(rs.getString("y"));
                Boolean speaking = Boolean.parseBoolean(rs.getString("speaking"));
                Double tooth = Double.parseDouble(rs.getString("tooth"));
                DragonType dragonType = DragonType.valueOf(rs.getString("type"));
                String owner = rs.getString("owner");
                Dragon.getDragonsCollection().add(new Dragon(id, name, age, wingspan, speaking, new Coordinates(x, y), new DragonHead(tooth), dragonType, owner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addDragonToDataBase(Dragon dragon) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY_ADD_DRAGON)) {
                ps.setObject(1, dragon.getName());
                ps.setObject(2, dragon.getAge()+"");
                ps.setObject(3, dragon.getWingspan()+"");
                ps.setObject(4, dragon.getSpeaking()+"");
                ps.setObject(5, dragon.getCoordinates().getX()+"");
                ps.setObject(6, dragon.getCoordinates().getY()+"");
                ps.setObject(7, dragon.getHead().getToothCount()+"");
                ps.setObject(8, dragon.getType()+"");
                ps.setObject(9, dragon.getOwner());
                ps.executeUpdate();
            }
        }
    }

    public void showDragon(ResultSet res) {
        try {
            while (res.next()) {
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

    public ResultSet getById(int id) {
        ResultSet rs = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(QUERY_GET_BY_ID + id);
            System.out.println(rs);
            System.out.println("База данных успешно подключена");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return rs;
    }

    public synchronized void updateDragonsDataInDataBase(int id, String whatToUpdate, String newValue) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE \"Dragons\" SET " + whatToUpdate + " = '" + newValue + "' WHERE id = " + id)) {
                ps.executeUpdate();
            }
        }
    }

    public static synchronized boolean removeFromDb(long id, String author) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_FOR_ALL_DRAGONS);) {
            while (rs.next()) {
                long iterId = Long.parseLong(rs.getString("id"));
                String iterAuthor = rs.getString("owner");
                if (iterId == id && iterAuthor.equals(author)) {
                    PreparedStatement removeStatement = conn.prepareStatement(DELETE_ELEMENT);
                    removeStatement.setString(1, String.valueOf(id));
                    removeStatement.executeUpdate();
                    removeStatement.close();
                    return true;
                }
            }
        }
        return false;
    }

}
