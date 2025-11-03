import java.io.*;
import java.util.*;

class Book implements Serializable {
    int id;
    String title;
    String author;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return id + " | " + title + " | " + author;
    }
}

public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static String fileName = "library.dat";

    public static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("✅ Book added successfully!\n");
    }

    public static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ No books found.\n");
        } else {
            System.out.println("ID | Title | Author");
            for (Book b : books)
                System.out.println(b);
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(books);
            System.out.println("💾 Books saved to file successfully!\n");
        } catch (IOException e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            books = (List<Book>) in.readObject();
            System.out.println("📚 Books loaded from file successfully!\n");
        } catch (Exception e) {
            System.out.println("⚠️ No existing file found.\n");
        }
    }

    public static void main(String[] args) {
        loadFromFile();
        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book\n2. View Books\n3. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> { saveToFile(); System.exit(0); }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}
