import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation: add, search, delete, or exit");
            String operation = scanner.nextLine().toLowerCase();

            switch (operation) {
                case "add":
                    System.out.println("Enter pet details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Breed: ");
                    String breed = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Adopted (true/false): ");
                    boolean adopted = Boolean.parseBoolean(scanner.nextLine());
                    dbManager.addPet(name, breed, age, adopted);
                    break;

                case "search":
                    System.out.println("Enter the name of the pet to search:");
                    String searchName = scanner.nextLine();
                    dbManager.searchPet(searchName);
                    break;

                case "delete":
                    System.out.println("Enter the ID of the pet to delete:");
                    int id = Integer.parseInt(scanner.nextLine());
                    dbManager.deletePet(id);
                    break;

                case "exit":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid operation! Try again.");
                    break;
            }
        }
    }
}