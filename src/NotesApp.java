import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";
    private static final Scanner sc = new Scanner(System.in); // only one scanner

    public static void writeNotes(boolean append) {
        try (FileWriter writer = new FileWriter(FILE_NAME, append)) {
            System.out.println("Enter your note (type 'exit' to stop):");
            while (true) {
                String note = sc.nextLine(); // use same scanner
                if (note.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write(note + System.lineSeparator());
            }
            System.out.println("Notes saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing notes: " + e.getMessage());
        }
    }

    public static void readNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("------------------\n");

        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Start writing first!");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    // Main menu
    public static void main(String[] args) {
        String choice;

        do {
            System.out.println("==== Notes App ====");
            System.out.println("1. Write Notes (Overwrite)");
            System.out.println("2. Write Notes (Append)");
            System.out.println("3. Read Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextLine(); // use same scanner

            switch (choice) {
                case "1" -> writeNotes(false);
                case "2" -> writeNotes(true);
                case "3" -> readNotes();
                case "4" -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice, try again.");
            }

        } while (!choice.equals("4"));

        sc.close();
    }
}
