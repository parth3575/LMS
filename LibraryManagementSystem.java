// Libraries
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


// LibraryItem class serves as the parent class for Book and User classes (Encapsulation)
// it encapsulates the common properties of library items
class LibraryItem {
    protected String name;
    protected String author;
    protected String publisher;
    protected String dateAdded;

    // constructor to initialize the LibraryItem object
    public LibraryItem(String name) {
        this.name = name;
    }

    // getter methods to access the protected properties
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}


// Book class extends the LibraryItem class (Inhereitance)
class Book extends LibraryItem {
    private boolean available;

    // constructor to initialize the Book object
    public Book(String name, String author, String publisher, String dateAdded, boolean available) {
        super(name);
        this.author = author;
        this.publisher = publisher;
        this.dateAdded = dateAdded;
        this.available = available;
    }

    // getter and setter methods for the available property (Encapsulation)
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // display book information (Polymorphism)
    public void displayBookInfo() {
        System.out.println("Book Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Date Added: " + dateAdded);
        System.out.println("Available: " + available);
    }
}


// User class extends the LibraryItem class (Inhereitance)
class User extends LibraryItem {
    private String userId;
    private String emailId;
    private ArrayList<Book> borrowedBooks;

    // constructor to initialize the User object
    public User(String name, String emailId) {
        super(name);
        this.userId = generateUserId();
        this.emailId = emailId;
        this.borrowedBooks = new ArrayList<>();
    }

    // generate unique user ID
    private String generateUserId() {
        // Reference: https://stackoverflow.com/questions/3804591/efficient-method-to-generate-uuid-string-in-java-uuid-randomuuid-tostring-w
        // Reference: https://stackoverflow.com/questions/4267475/how-to-generate-shorter-uuids-of-8-characters-in-length-instead-of-32-characters
        return UUID.randomUUID().toString().substring(0, 8); 
    }

    // getter method for the userId property (Encapsulation)
    public String getUserId() {
        return userId;
    }

    // getter method for the emailId property (Encapsulation)
    public String getEmailId() {
        return emailId;
    }

    // getter method for the borrowedBooks list (Encapsulation)
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // methods to add and remove borrowed books from the user's list
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    // display the user's information, including the borrowed books
    public void displayUserInfo() {
        System.out.println("User Name: " + name);
        System.out.println("User ID: " + userId);
        System.out.println("Email ID: " + emailId);
        System.out.println("Borrowed Books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            borrowedBooks.get(i).displayBookInfo(); // print borrowed book info along with user info.
        }
    }
}


// BorrowingRecord class
class BorrowingRecord {
    private Book book;
    private User user;
    private String borrowedDate;
    private String returnDate;

    // constructor to initialize the BorrowingRecord object
    public BorrowingRecord(Book book, User user, String borrowedDate, String returnDate) {
        this.book = book;
        this.user = user;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }

    // getter methods to access the properties of the BorrowingRecord (Encapsulation)
    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}


// main class
class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>(); // only Book objects can be added to this list.
    private static ArrayList<User> users = new ArrayList<>(); // only User objects can be added to this list.
    private static ArrayList<BorrowingRecord> borrowingRecords = new ArrayList<>(); // only BorrowingRecord objects can be added to this list.
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Adding sample data

        // for books
        Book book1 = new Book("Rich Dad Poor Dad", "Robert Kiyosaki", "Plata Publishing", "01-Apr-1997", true);
        Book book2 = new Book("48 Laws of Power", "Robert Greene", "Penguin Books", "18-Sep-1998", true);
        Book book3 = new Book("Deep Work", "Cal Newport", "Grand Central Publishing", "05-Jan-2016", true);
        Book book4 = new Book("Atomic Habits", "James Clear", "Avery", "16-Oct-2018", true);
        Book book5 = new Book("The Power of your Subconscious Mind", "Joseph Murphy", "Prentice-Hall", "28-Aug-1963",
                true);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        System.out.println("Sample books have been added.");

        // for users
        User user1 = new User("Mark Zuckerberg", "mark@meta.com");
        User user2 = new User("Bill Gates", "bill@microsoft.com");
        User user3 = new User("Jeff Bezos", "jeff@amazon.com");
        User user4 = new User("Elon Musk", "elon@tesla.com");
        User user5 = new User("Steve Jobs", "steve@apple.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        System.out.println("Sample users have been added.");

        while (true) {
            displayMainMenu();
            char choice = sc.next().charAt(0); // Reference: https://www.geeksforgeeks.org/gfact-51-java-scanner-nextchar/
            sc.nextLine();

            switch (choice) {
                case 'a':
                    bookManagement();
                    break;
                case 'b':
                    userManagement();
                    break;
                case 'c':
                    borrowBook();
                    break;
                case 'd':
                    returnBook();
                    break;
                case 'e':
                    searchBook();
                    break;
                case 'f':
                    System.out.println("Thanks for using LMS!!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // display main menu
    private static void displayMainMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("a. Book Management");
        System.out.println("b. User Management");
        System.out.println("c. Borrow");
        System.out.println("d. Return");
        System.out.println("e. Search");
        System.out.println("f. Exit");
        System.out.print("Enter your choice: ");
    }

    // book management menu
    private static void bookManagement() {
        while (true) {
            System.out.println("\nBook Management");
            System.out.println("a. Add new book");
            System.out.println("b. Remove book");
            System.out.println("c. Display all books");
            System.out.println("d. Back to main menu");
            System.out.print("Enter your choice: ");
            char choice = sc.next().charAt(0);
            sc.nextLine();

            switch (choice) {
                case 'a':
                    addBook();
                    break;
                case 'b':
                    removeBook();
                    break;
                case 'c':
                    displayAllBooks();
                    break;
                case 'd':
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // add book
    public static void addBook() {
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter publisher name: ");
        String publisher = sc.nextLine();
        System.out.print("Enter date added (DD-MMM-YYYY): ");
        String dateAdded = sc.nextLine();

        Book newBook = new Book(name, author, publisher, dateAdded, true);
        books.add(newBook);
        System.out.println("Book added successfully.");
    }

    // remove book
    private static void removeBook() {
        System.out.print("Enter book name to remove: ");
        String name = sc.nextLine();

        for (int i = 0; i < books.size(); i++) { // Reference: https://beginnersbook.com/2013/12/how-to-find-length-of-arraylist-in-java/
            if (books.get(i).getName().equalsIgnoreCase(name)) {
                books.remove(i); // Reference: https://www.geeksforgeeks.org/remove-element-arraylist-java/
                System.out.println("Book removed successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    // display all books
    private static void displayAllBooks() {
        System.out.println("\nAll available books:");
        for (int i = 0; i < books.size(); i++) {
            // if (! books.get(i).isAvailable()) { continue; } // If the book is not available, skip it and move to the next book
            books.get(i).displayBookInfo();
            System.out.println();
        }
        System.out.println("Total books: " + books.size());
    }

    // user management menu
    private static void userManagement() {
        while (true) {
            System.out.println("\nUser Management");
            System.out.println("a. Add new user");
            System.out.println("b. Remove user");
            System.out.println("c. Display all users");
            System.out.println("d. Back to main menu");
            System.out.print("Enter your choice: ");
            char choice = sc.next().charAt(0);
            sc.nextLine();

            switch (choice) {
                case 'a':
                    addUser();
                    break;
                case 'b':
                    removeUser();
                    break;
                case 'c':
                    displayAllUsers();
                    break;
                case 'd':
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // add user
    private static void addUser() {
        System.out.print("Enter user name: ");
        String name = sc.nextLine();
        System.out.print("Enter email ID: ");
        String emailId = sc.nextLine();

        User newUser = new User(name, emailId); // create a new User object
        users.add(newUser); // add the new User object to the users list
        System.out.println("User added successfully. User ID: " + newUser.getUserId());
    }

    // remove user
    private static void removeUser() {
        System.out.print("Enter email ID to remove: ");
        String emailId = sc.nextLine();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmailId().equalsIgnoreCase(emailId)) {
                users.remove(i);
                System.out.println("User removed successfully.");
                return;
            }
        }

        System.out.println("User not found.");
    }

    // display all users
    private static void displayAllUsers() {
        System.out.println("\nAll registered users:");
        for (int i = 0; i < users.size(); i++) {
            users.get(i).displayUserInfo();
            System.out.println();
        }
        System.out.println("Total users: " + users.size());
    }


    // borrow book process
    private static void borrowBook() {

        System.out.print("Enter book name: ");
        String bookName = sc.nextLine();

        // find book based on user's input
        Book book = findBook(bookName);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        // check if the book is available for borrowing
        if (!book.isAvailable()) {
            // if not available
            System.out.println("Sorry!, The book is currently unavailable. Please check back after a week.");
            return;
        }

        System.out.print("Enter email ID: ");
        String emailId = sc.nextLine();

        // find user based on the email ID
        User user = findUser(emailId);
        if (user == null) {
            // If user not found
            System.out.println("User not found.");
            return;
        }

        // calculate return date based on borrow date
        LocalDate today = LocalDate.now(); // Reference: https://www.w3schools.com/java/java_date.asp
        LocalDate returnDate = today.plusDays(14); // Reference: https://www.javatpoint.com/java-date-add-days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String borrowedDate = today.format(formatter);
        String returnDateStr = returnDate.format(formatter);

        // display the return date and late return fine
        System.out.println("Return date: " + returnDateStr);
        System.out.println("Late return fine: $5 per day");

        // add borrowed book in the user's list,
        user.addBorrowedBook(book);
        book.setAvailable(false); // set availability to false
        BorrowingRecord record = new BorrowingRecord(book, user, borrowedDate, returnDateStr);
        borrowingRecords.add(record); // create new record, and add it to the borrowingRecords list

        System.out.println("Book borrowed successfully.");
    }

    // method to find a book based on the book name
    private static Book findBook(String bookName) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equalsIgnoreCase(bookName) && books.get(i).isAvailable()) {
                System.err.println(bookName + " is available for borrowing.");
                return books.get(i);
            }
        }
        return null;
    }

    // method to find a user based on the email ID
    private static User findUser(String emailId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmailId().equalsIgnoreCase(emailId)) {
                return users.get(i);
            }
        }
        return null;
    }

    // book return process
    private static void returnBook() {
        System.out.print("Enter book name: ");
        String bookName = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < borrowingRecords.size(); i++) {
            // check if book name matches the book name provided by the user (case-insensitive)
            if (borrowingRecords.get(i).getBook().getName().equalsIgnoreCase(bookName)) {
                System.out.println("Borrowed date: " + borrowingRecords.get(i).getBorrowedDate());
                System.out.println("Returned on time. Thank you!");
                borrowingRecords.get(i).getUser().removeBorrowedBook(borrowingRecords.get(i).getBook());
                borrowingRecords.get(i).getBook().setAvailable(true);
                borrowingRecords.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found in the borrowing records.");
        }
    }

    // book search functionality
    private static void searchBook() {
        System.out.print("Enter book name to search: ");
        String bookName = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            // check if book name matches the book name provided by the user (case-insensitive)
            if (books.get(i).getName().equalsIgnoreCase(bookName)) {
                if (books.get(i).isAvailable()) {
                    System.out.println("The book is available for borrowing at - Row 1 - Rack 3.");
                } else {
                    System.out.println("Sorry!, The book is currently unavailable. Please check back after a week.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found in the library.");
        }
    }
}