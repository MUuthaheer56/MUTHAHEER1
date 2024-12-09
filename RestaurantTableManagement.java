import java.util.Scanner;

public class RestaurantTableManagement {
    private static final int NUM_TABLES = 20;
    private static boolean[] tables = new boolean[NUM_TABLES];
    private static String[] tableBookings = new String[NUM_TABLES];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\nRestaurant Table Management System");
            System.out.println("1. Book a table");
            System.out.println("2. Release a table");
            System.out.println("3. View table status");
            System.out.println("4. Switch tables");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    bookTable(scanner);
                    break;
                case 2:
                    releaseTable(scanner);
                    break;
                case 3:
                    viewTableStatus();
                    break;
                case 4:
                    switchTables(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void bookTable(Scanner scanner) {
        System.out.print("Enter table number to book (1-20): ");
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); 
        if (tableNumber < 1 || tableNumber > NUM_TABLES) {
            System.out.println("Invalid table number. Please try again.");
        } else if (tables[tableNumber - 1]) {
            System.out.println("Table " + tableNumber + " is already booked.");
        } else {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            tables[tableNumber - 1] = true;
            tableBookings[tableNumber - 1] = name;
            System.out.println("Table " + tableNumber + " booked successfully for " + name + ".");
        }
    }

    private static void releaseTable(Scanner scanner) {
        System.out.print("Enter table number to release (1-20): ");
        int tableNumber = scanner.nextInt();
        if (tableNumber < 1 || tableNumber > NUM_TABLES) {
            System.out.println("Invalid table number. Please try again.");
        } else if (!tables[tableNumber - 1]) {
            System.out.println("Table " + tableNumber + " is already available.");
        } else {
            tables[tableNumber - 1] = false;
            tableBookings[tableNumber - 1] = null;
            System.out.println("Table " + tableNumber + " released successfully.");
        }
    }

    private static void viewTableStatus() {
        System.out.println("\nTable Status:");
        for (int i = 0; i < NUM_TABLES; i++) {
            String status = tables[i] ? "Booked by " + tableBookings[i] : "Available";
            System.out.println("Table " + (i + 1) + ": " + status);
        }
    }

    private static void switchTables(Scanner scanner) {
        System.out.print("Enter first table number to switch (1-20): ");
        int firstTable = scanner.nextInt();
        System.out.print("Enter second table number to switch with (1-20): ");
        int secondTable = scanner.nextInt();

        if (firstTable < 1 || firstTable > NUM_TABLES || secondTable < 1 || secondTable > NUM_TABLES) {
            System.out.println("Invalid table number(s). Please try again.");
        } else if (firstTable == secondTable) {
            System.out.println("Both table numbers are the same. No switch needed.");
        } else {
            boolean tempTableStatus = tables[firstTable - 1];
            String tempName = tableBookings[firstTable - 1];

            tables[firstTable - 1] = tables[secondTable - 1];
            tableBookings[firstTable - 1] = tableBookings[secondTable - 1];

            tables[secondTable - 1] = tempTableStatus;
            tableBookings[secondTable - 1] = tempName;

            System.out.println("Switched table " + firstTable + " with table " + secondTable + ".");
        }
    }
}