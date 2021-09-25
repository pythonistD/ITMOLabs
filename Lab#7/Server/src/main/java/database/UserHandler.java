package database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserHandler {
    private static DataBase database;
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Lab#7";
    static final String USER = "postgres";
    static final String PASS = "200201gd";
    static final String QUERY_FOR_ALL_DRAGONS = "SELECT id, name, age, wingspan, x, y, speaking, tooth, type FROM \"Dragons\"";
    static final String QUERY_GET_BY_ID = "SELECT id, name, age, wingspan, x, y, speaking, tooth, type FROM \"Dragons\" WHERE id=";

    public UserHandler(DataBase dataBase) {
        database = dataBase;
    }

    public UserHandler() {
    }

    public static String MD2(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginUser(String name, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement ps = connection.prepareStatement("GET  users (name,password) values (?,?)")) {
                ps.setObject(1, name);
                ps.setObject(2, password);
                ps.executeUpdate();
            }
        }
    }

    public void addUser(String name, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name,password,MD2) values (?,?,?)")) {
                ps.setObject(1, name);
                ps.setObject(2, password);
                ps.setObject(3, MD2(password));
                ps.executeUpdate();
                System.out.println("Пользователь успешно добавлен");
            }
        }

    }

    public boolean userExist(String username) throws SQLException {
        boolean exist = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (Statement ps = connection.createStatement()) {
                try (ResultSet rs = ps.executeQuery("SELECT name,password FROM users")) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        if (name.equals(username)) {
                            exist = true;
                            break;
                        }
                    }
                }
            }
        }
        return exist;
    }

    public boolean accountExist(String username, String password) throws SQLException {
        boolean exist = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (Statement ps = connection.createStatement()) {
                try (ResultSet rs = ps.executeQuery("SELECT name,password FROM users")) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String pass = rs.getString("password");
                        if (name.equals(username) && password.equals(pass)) {
                            exist = true;
                        }
                    }
                }
            }
        }
        return exist;
    }
}

