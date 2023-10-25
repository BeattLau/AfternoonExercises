package Week8Exercises;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

    public class MusieTask {
        public static void main(String[] args) {
            String inputFileName = "/Users/laurabeattie/IdeaProjects/AfternoonExercises/Week8Exercises/input.txt";
            String outputFileName = "/Users/laurabeattie/IdeaProjects/AfternoonExercises/Week8Exercises/output.txt";

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.toUpperCase().replace("JAVA", "Python");

                    writer.write(line);
                    writer.newLine();


                    System.out.println(line);
                }

                System.out.println("Transformation and writing to output.txt completed.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

