import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    public void display() {
        System.out.println("ID     : " + bookId);
        System.out.println("Title  : " + title);
        System.out.println("Author : " + author);
        System.out.println("Status : " + (isIssued ? "Issued" : "Available"));
    }
}

class Library {
    private Map<Integer, Book> books = new HashMap<>();

    public void addBook(int id, String title, String author) {
        if (books.containsKey(id)) {
            System.out.println("Book with this ID already exists.");
        } else {
            books.put(id, new Book(id, title, author));
            System.out.println("Book added successfully.");
        }
    }

    public void issueBook(int id) {
        Book b = books.get(id);
        if (b == null) {
            System.out.println("Book not found.");
        } else if (b.isIssued()) {
            System.out.println("Book already issued.");
        } else {
            b.issue();
            System.out.println("Book issued successfully.");
        }
    }

    public void returnBook(int id) {
        Book b = books.get(id);
        if (b == null) {
            System.out.println("Book not found.");
        } else if (!b.isIssued()) {
            System.out.println("Book is not issued.");
        } else {
            b.returnBook();
            System.out.println("Book returned successfully.");
        }
    }

    public void viewBook(int id) {
        Book b = books.get(id);
        if (b == null) {
            System.out.println("Book not found.");
        } else {
            b.display();
        }
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book b : books.values()) {
                System.out.println("---------------------------");
                b.display();
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Book");
            System.out.println("5. View All Books");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            int id;
            String title, author;

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    author = sc.nextLine();
                    lib.addBook(id, title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    id = sc.nextInt();
                    lib.issueBook(id);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    id = sc.nextInt();
                    lib.returnBook(id);
                    break;
                case 4:
                    System.out.print("Enter Book ID to view: ");
                    id = sc.nextInt();
                    lib.viewBook(id);
                    break;
                case 5:
                    lib.viewAllBooks();
                    break;
                case 6:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}