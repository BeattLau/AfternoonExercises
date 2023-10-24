package Week8Exercises;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class TuesdayExercises {
    public static void main(String[] args) throws IOException {
        ReadDetailsFromFile(); //Exercise 1
        updateDetails(); //Exercise 2
        deleteUserInfo(); //Exercise 3
        createLogFile(); // Exercise 4
        favouriteColour(); //Exercise 6

        try { // Exercise 5
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(System.in));
            System.out.print("Enter file path to read/write data: ");
            String fileName = reader.readLine();

            FileInputStream fileInput = new FileInputStream(fileName); //Does not work if you don't add the path of the file
            System.out.println("Information added to file successfully.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());

        }
    }

    static void ReadDetailsFromFile() {
        BufferedReader bufferedReader = null;
        try {
            File file = new File("/Users/laurabeattie/IdeaProjects/AfternoonExercises/userInfo.txt");

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("userInfo.txt file created");
            } else {

                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(reader);

                String phoneNum = bufferedReader.readLine();
                String address = bufferedReader.readLine();
                System.out.println("Phone number: " + phoneNum);
                System.out.println("Address: " + address);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void updateDetails() {
        Scanner sc = new Scanner(System.in);
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/laurabeattie/IdeaProjects/AfternoonExercises/userInfo.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String email = bufferedReader.readLine();
            String phoneNum = bufferedReader.readLine();
            String address = bufferedReader.readLine();


            System.out.println("Current email address: " + email);
            System.out.println("Current phone number: " + phoneNum);
            System.out.println("Current address: " + address);

            System.out.println("Enter new email address");
            String newEmail = sc.nextLine();
            System.out.print("Enter new phone number");
            String newPhoneNum = sc.nextLine();
            System.out.println("Enter new address");
            String newAddress = sc.nextLine();

            FileWriter fileWriter = new FileWriter("userInfo.txt");
            fileWriter.write(newEmail + "\n" + newPhoneNum + "\n" + newAddress);
            fileWriter.close();

            System.out.println("Updated email address: " + newEmail);
            System.out.println("Updated phone number " + newPhoneNum);
            System.out.println("Updated address: " + newAddress);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());

        }

    }

    static void deleteUserInfo() {
        File file = new File("/Users/laurabeattie/IdeaProjects/AfternoonExercises/userInfo.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to delete user information? y/n");
        String confirmation = sc.nextLine();

        if (confirmation.equals("y")) {
            file.delete();
            System.out.println("User information deleted successfully");
        } else {
            System.out.println("User information saved");
        }
    }

    static void createLogFile() throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date();
        String logMessage = String.format("User interaction: " + timestamp, date);
        FileWriter logWriter = new FileWriter("/Users/laurabeattie/IdeaProjects/AfternoonExercises/Week8Exercises/log.txt", true);
        logWriter.write(logMessage + "\n");
        logWriter.close();

        System.out.println("Log entry added: " + logMessage);
    }

    static void favouriteColour() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter your favourite colour: ");
        String colour = sc.nextLine();


        FileInputStream fileInputStream = new FileInputStream("userInfo.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        String email = bufferedReader.readLine();
        String phoneNum = bufferedReader.readLine();
        String address = bufferedReader.readLine();


        bufferedReader.close();
        fileInputStream.close();


        FileWriter fileWriter = new FileWriter("userInfo.txt");
        fileWriter.write(email + "\n" + phoneNum + "\n" + address + "\n" + colour + "\n");
        fileWriter.close();


        System.out.println("Updated email address: " + email);
        System.out.println("Updated phone number: " + phoneNum);
        System.out.println("Updated address: " + address);
        System.out.println("Favourite colour: " + colour);
    }
}