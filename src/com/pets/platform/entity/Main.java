import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:8526/shelter_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "aizeka852626";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Choose an operation: add, search, delete, filter, sort, or exit");
                String operation = scanner.nextLine().trim().toLowerCase();

                switch (operation) {
                    case "add":
                        addPet(scanner);
                        break;
                    case "search":
                        searchPet(scanner);
                        break;
                    case "delete":
                        deletePet(scanner);
                        break;
                    case "filter":
                        filterPets(scanner);
                        break;
                    case "sort":
                        sortPets(scanner);
                        break;
                    case "exit":
                        System.out.println("Exiting program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid operation.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void addPet(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Enter pet name:");
            String name = scanner.nextLine();
            System.out.println("Enter breed:");
            String breed = scanner.nextLine();
            System.out.println("Enter age:");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Is adopted (true/false):");
            boolean adopted = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Enter type (Cat/Dog):");
            String type = scanner.nextLine();
            System.out.println("Enter nature:");
            String nature = scanner.nextLine();
            System.out.println("Enter shelter ID:");
            int shelterId = Integer.parseInt(scanner.nextLine());

            String sql = "INSERT INTO petss (name, breed, age, adopted, type, nature, shelter_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, breed);
                pstmt.setInt(3, age);
                pstmt.setBoolean(4, adopted);
                pstmt.setString(5, type);
                pstmt.setString(6, nature);
                pstmt.setInt(7, shelterId);
                pstmt.executeUpdate();
                System.out.println("Pet added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchPet(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Enter pet name to search:");
            String name = scanner.nextLine();
            String sql = "SELECT * FROM petss WHERE name ILIKE ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%" + name + "%");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println(new Pet(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("breed"),
                            rs.getInt("age"),
                            rs.getBoolean("adopted"),
                            rs.getString("type"),
                            rs.getString("nature"),
                            rs.getInt("shelter_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePet(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Enter pet ID to delete:");
            int id = Integer.parseInt(scanner.nextLine());
            String sql = "DELETE FROM petss WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Pet deleted successfully.");
                } else {
                    System.out.println("No pet found with the given ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void filterPets(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Filter by adoption status (true/false):");
            boolean adopted = Boolean.parseBoolean(scanner.nextLine());
            String sql = "SELECT * FROM petss WHERE adopted = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setBoolean(1, adopted);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println(new Pet(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("breed"),
                            rs.getInt("age"),
                            rs.getBoolean("adopted"),
                            rs.getString("type"),
                            rs.getString("nature"),
                            rs.getInt("shelter_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sortPets(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Sort by (name/age):");
            String column = scanner.nextLine();
            String sql = "SELECT * FROM petss ORDER BY " + column;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    System.out.println(new Pet(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("breed"),
                            rs.getInt("age"),
                            rs.getBoolean("adopted"),
                            rs.getString("type"),
                            rs.getString("nature"),
                            rs.getInt("shelter_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
