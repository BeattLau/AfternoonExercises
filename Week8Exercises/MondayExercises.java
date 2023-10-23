package Week8Exercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MondayExercises {
    public static void main(String[] args) {
        List<String> enteredDetails = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: "); //1
        String name = sc.nextLine();
        enteredDetails.add("Name: " + name);

        int age1 = 0; //2
        boolean isValidAge = false;

        while (!isValidAge) {
            try {
                System.out.print("Enter your age: ");
                age1 = Integer.parseInt(sc.nextLine());

                if (age1 >= 1) {
                    isValidAge = true;
                } else {
                    System.out.println("Invalid input, Age must be a positive integer");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, Age must be a positive integer");
            }
        }

        enteredDetails.add("Age: " + age1);



        System.out.print("Enter your email address: ");
        String email = sc.nextLine();
        enteredDetails.add("Email address: " + email);

        while (!isValidEmail(email)) {
            System.out.println("Invalid email. Please enter a valid email address.");
            System.out.print("Enter your email address: ");
            email = sc.nextLine();
        }


        System.out.print("Enter your phone number: ");
        String phone = sc.nextLine();
        enteredDetails.add("Phone number: " + phone);

        System.out.print("Enter your address: ");
        String address = sc.nextLine();
        enteredDetails.add("Address: " + address + "\n");

        System.out.print("Enter the title of your favourite book: "); //7
        String title = sc.nextLine();
        enteredDetails.add("Favourite book: " + title);

        System.out.print("Enter the author of your favourite book: ");
        String author = sc.nextLine();
        enteredDetails.add("Author: " + author);

        System.out.print("Enter the publication year of your favourite book: ");
        String year = sc.nextLine();
        enteredDetails.add("Publication year: " + year + "\n");

        System.out.print("Enter your favourite colour: "); //9
        String colour = sc.nextLine();
        enteredDetails.add("Favourite colour: " + colour);


        sc.close();

        System.out.println("Entered details: "); //3
        for (String details : enteredDetails) {
            System.out.println(details);
        }
        writeDetailsToFile(enteredDetails, "user_details.txt"); //4

        int currentYear = Calendar.getInstance().get(Calendar.YEAR); //6
        int birthYear = currentYear - age1;
        System.out.println("Users birth year: " + birthYear);
    }

    public static void writeDetailsToFile(List<String> details, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (String detail : details) {
                fw.write(detail + "\n");
            }
            fw.close();
            System.out.println("Entered details have been written to " + fileName); //5
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;

            System.out.println("Contents of " + fileName + ":");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while reading the file.");
        }
    }
    public static boolean isValidEmail(String validEmail) { //8
        return validEmail.contains("@") && validEmail.contains(".");

    }
}







