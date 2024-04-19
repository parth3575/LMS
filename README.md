# LMS (Library Management System)

The Library Management System (LMS) is a Java-based application that allows users to manage a library's book and user information. It provides functionalities for adding, removing, and searching books, as well as borrowing and returning books. This implementation does not use a database or any other external storage mechanism, and instead, it stores the library data in in-memory data structures.

## Features

1. **Book Management**:
   - Add new books to the library
   - Remove books from the library
   - Display all available books in the library

2. **User Management**:
   - Add new users to the library system
   - Remove users from the library system
   - Display all registered users in the library system

3. **Book Borrowing**:
   - Borrow a book from the library
   - Calculate the return date and late return fine

4. **Book Returning**:
   - Return a borrowed book to the library
   - Update the book's availability and the user's borrowed books list

5. **Book Searching**:
   - Search for a book in the library
   - Display the book's availability

## Usage

1. Run the `LibraryManagementSystem` class to start the application.
2. Follow the on-screen instructions to navigate through the main menu and perform various library management operations.

## Dependencies

The LMS application uses the following Java standard library classes:

- `java.time.LocalDate`
- `java.time.format.DateTimeFormatter`
- `java.util.ArrayList`
- `java.util.Scanner`
- `java.util.UUID`

## Contributing

If you would like to contribute to the LMS project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your forked repository.
5. Submit a pull request to the original repository.

## License

This project is licensed under the [MIT License](LICENSE).
