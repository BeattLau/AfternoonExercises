package Week8Exercises;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WednesdayExercises {
    public static void main(String[] args) throws IOException {
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{"John Doe", "30", "john.doe@email.com", "123-456-7890", "123 Main St"});
        userData.add(new String[]{"Jane Smith", "25", "jane.smith@email.com", "987-654-3210", "456 Elm St"});
        userData.add(new String[]{"Bob Johnson", "35", "bob.johnson@email.com", "555-555-5555", "789 Oak St"});
        userData.add(new String[]{"Jenny Stevens", "22", "jenny.stevens@email.com", "000-999-8888", "100 James St"});

        String csvFile = "/Users/laurabeattie/IdeaProjects/AfternoonExercises/Week8Exercises/user_data.csv";


        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Name,Age,Email,Phone,Address\n");
            for (String[] user : userData) {
                String line = String.join(",", user);
                writer.write(line + "\n");
            }
            readUserData(csvFile);
            System.out.println("User data has been saved to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addNewUser(userData);
        writeUserData(csvFile, userData);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name, Email, or Phone to search for: ");
        String searchTerm = sc.nextLine();

        searchAndDisplay(userData, searchTerm);
        sortUsersByAge(userData);
        displayUsers(userData);
        updateAddress(userData, sc);
    }

    private static List<String[]> readUserData(String csvFile) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDataLine = line.split("\\s*,\\s*");
                if (userDataLine.length == 5) {
                    data.add(userDataLine);
                }
            }
        }
        return data;
    }

    private static void addNewUser(List<String[]> userData) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        String age = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        userData.add(new String[]{name, age, email, phone, address});
    }

    private static void writeUserData(String csvFile, List<String[]> userData) throws IOException {
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Name,Age,Email,Phone,Address\n");
            for (String[] user : userData) {
                String line = String.join(",", user);
                writer.write(line + "\n");
            }
        }
    }

    private static void searchAndDisplay(List<String[]> userData, String searchTerm) {
        System.out.println("Search results for: " + searchTerm);
        boolean found = false;
        for (String[] user : userData) {
            String name = user[0];
            String email = user[2];
            String phone = user[3];

            if (name.toLowerCase().contains(searchTerm.toLowerCase()) ||
                    email.toLowerCase().contains(searchTerm.toLowerCase()) ||
                    phone.contains(searchTerm)) {
                System.out.println("Name: " + name);
                System.out.println("Age: " + user[1]);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Address: " + user[4]);
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching user found.");
        }
    }

    private static void sortUsersByAge(List<String[]> userData) {
        userData.sort(Comparator.comparingInt(user -> Integer.parseInt(user[1])));
    }

    private static void displayUsers(List<String[]> userData) {
        for (String[] user : userData) {
            System.out.println("Name: " + user[0]);
            System.out.println("Age: " + user[1]);
            System.out.println("Email: " + user[2]);
            System.out.println("Phone: " + user[3]);
            System.out.println("Address: " + user[4]);
            System.out.println();
        }
    }

    private static void updateAddress(List<String[]> userData, Scanner sc) {
        System.out.print("Enter the name of the user whose address you wish to update: ");
        String searchName = sc.nextLine();
        List<String[]> matchingUsers = userData.stream()
                .filter(user -> user[0].equalsIgnoreCase(searchName))
                .collect(Collectors.toList());

        if (!matchingUsers.isEmpty()) {
            System.out.println("Found matching user(s):");
            for (String[] user : matchingUsers) {
                System.out.println("Name: " + user[0]);
                System.out.println("Age: " + user[1]);
                System.out.println("Email: " + user[2]);
                System.out.println("Phone: " + user[3]);
                System.out.println("Address: " + user[4]);
            }

            System.out.print("Enter the new address: ");
            String newAddress = sc.nextLine();


            for (String[] user : matchingUsers) {
                user[4] = newAddress;
            }
        } else {
            System.out.println("No matching user found.");
        }
    }
}












