import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:8526/shelter_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "aizeka852626";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addPet(String name, String breed, int age, boolean adopted) {
        String sql = "INSERT INTO petss (name, breed, age, adopted) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, breed);
            pstmt.setInt(3, age);
            pstmt.setBoolean(4, adopted);
            pstmt.executeUpdate();
            System.out.println("Pet added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchPet(String name) {
        String sql = "SELECT * FROM petss WHERE name ILIKE ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Breed: " + rs.getString("breed") +
                        ", Age: " + rs.getInt("age") +
                        ", Adopted: " + rs.getBoolean("adopted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePet(int id) {
        String sql = "DELETE FROM petss WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Pet deleted successfully!");
            } else {
                System.out.println("Pet not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
