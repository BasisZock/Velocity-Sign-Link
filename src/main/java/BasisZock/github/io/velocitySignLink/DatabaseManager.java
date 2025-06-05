package BasisZock.github.io.velocitySignLink;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.sql.*;
import java.io.File;


public class DatabaseManager {
    private static Connection connection;

    // Creates the database file if it does not exist
    public static void createDatabase() {
        File dbFile = new File("plugins/VelocitySignLink/signs.db");
        try {
            if (!dbFile.exists()) {
                if (dbFile.getParentFile().mkdirs()) {
                    Bukkit.getLogger().info("Directory created: " + dbFile.getParent());
                }
                if (dbFile.createNewFile()) {
                    Bukkit.getLogger().info("Database file created: " + dbFile.getName());
                } else {
                    Bukkit.getLogger().info("Database file already exists.");
                }
            } else {
                Bukkit.getLogger().info("Database file already exists.");
            }
        } catch (IOException e) {
            Bukkit.getLogger().severe("Error creating database file.");
            e.printStackTrace();
        }
    }

    // Establishes connection and creates the signs table if it doesn't exist
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:plugins/VelocitySignLink/signs.db");
            Bukkit.getLogger().info("Database connected!");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS signs ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "x INT, y INT, z INT, "
                    + "world TEXT, text TEXT)";
            connection.createStatement().execute(createTableSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    // Saves sign data into the database
    public static void saveSign(int x, int y, int z, String world, String text) {
        try {
            String insertSQL = "INSERT INTO signs (x, y, z, world, text) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertSQL);
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.setInt(3, z);
            ps.setString(4, world);
            ps.setString(5, text);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves the sign text based on coordinates and world name
    public static String getSignTextByCoordinates(int x, int y, int z, String world) {
        String result = null;
        try {
            String sql = "SELECT text FROM signs WHERE x = ? AND y = ? AND z = ? AND world = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.setInt(3, z);
            ps.setString(4, world);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("text");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static Integer deleteSign(int x, int y, int z, String world) {
        try {
            String sql = "DELETE FROM signs WHERE x = ? AND y = ? AND z = ? AND world = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.setInt(3, z);
            ps.setString(4, world);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            if (rowsAffected > 0) {
                return 2; // Return "2" if a row was deleted
            }
            else if (rowsAffected == 0) {
                return 1; // Return "1" if no row was deleted
            }
            else {
                return 0; // Return "0" if an error occurred
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
