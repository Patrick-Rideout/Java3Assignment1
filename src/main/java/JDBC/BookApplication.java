package JDBC;

import java.util.*;

/**
 * The main class that runs the Book Application.
 * This application allows users to interact with a database to manage books and authors.
 * Users can view, add, edit, and delete books and authors in the library system.
 */
public class BookApplication {

    /**
     * The entry point of the application that runs a loop allowing the user to select various options
     * to interact with the library system.
     * */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        BookDatabaseManager db = new BookDatabaseManager();

        System.out.printf("WELCOME TO THE BOOK APPLICATION\n");

        while (true) {

            List<Book> listOfBooks = db.loadBooks();
            List<Author> listOfAuthors = db.loadAuthors();
            Map<Integer, List<String>> isbnMap = db.loadISBN();

            Library library = new Library(listOfBooks, listOfAuthors, isbnMap);

            System.out.println("\nSelections:");
            System.out.println("""
                    1. Print Books
                    2. Print Authors
                    3. Edit Book
                    4. Edit Author
                    5. Add Book
                    6. Add Author
                    7. Quit
                    """);

            System.out.println("\nENTER SELECTION:");
            String selection = input.nextLine();
            System.out.println();

            if (selection.equals("1")) {
                library.getLibraryBooks().forEach(book -> {
                    StringBuilder authorNames = new StringBuilder();
                    for (Author author : book.getAuthorList()) {
                        if (!authorNames.isEmpty()) {
                            authorNames.append(", ");
                        }
                        authorNames.append(author.getFirstName()).append(" ").append(author.getLastName());
                    }

                    System.out.println(
                            "ISBN: (" + book.getIsbn() + ") " + "Title: (" + book.getTitle() + ") " + "Edition Number: (" + book.getEditionNumber() + ") " + "Copyright: (" + book.getCopyright() + ") " + "Authors: (" + authorNames + ")");
                });

            } else if (selection.equals("2")) {
                library.getLibraryAuthors().forEach(author -> {
                    StringBuilder bookTitles = new StringBuilder();

                    for (Book book : author.getBookList()) {
                        if (!bookTitles.isEmpty()) {
                            bookTitles.append(", ");
                        }
                        bookTitles.append(book.getTitle());
                    }

                    System.out.println("Author ID: " + author.getAuthorID() + " Author: " + author.getFirstName() + " " + author.getLastName() + " | Books: (" + bookTitles + ")");
                });

            } else if (selection.equals("3")) {
                while (true) {
                    System.out.println("Enter an ISBN number to Edit: ");
                    String userISBN = input.nextLine();

                    if (userISBN.length() > 20) {
                        System.out.println("ERROR: ISBN number cannot exceed 20 characters.");
                        continue;
                    }

                    if (library.getBookISBNs().contains(userISBN)) {
                        String newTitle;
                        while (true) {
                            System.out.println("Enter new Book Title: ");
                            newTitle = input.nextLine();

                            if (newTitle.length() > 100) {
                                System.out.println("ERROR: Book title cannot exceed 100 characters.");
                                continue;
                            }

                            break;
                        }

                        String newEditionNumberInput;
                        int newEditionNumber;
                        while (true) {
                            System.out.println("Enter new Edition Number: ");
                            newEditionNumberInput = input.nextLine();

                            if (newEditionNumberInput.length() > 11) {
                                System.out.println("ERROR: Edition number cannot exceed 11 digits.");
                                continue;
                            }

                            try {
                                newEditionNumber = Integer.parseInt(newEditionNumberInput);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("ERROR: Edition number must be an integer.");
                            }
                        }

                        String newCopyright;
                        while (true) {
                            System.out.println("Enter new Copyright Number: ");
                            newCopyright = input.nextLine();

                            if (newCopyright.length() > 4) {
                                System.out.println("ERROR: Copyright number cannot exceed 4 characters.");
                                continue;
                            }

                            db.editBook(userISBN, newTitle, newEditionNumber, newCopyright);

                            System.out.println("UPDATE COMPLETE!");

                            break;
                        }

                    } else {
                        System.out.println("ERROR: ISBN not found in the library.");
                        continue;
                    }

                    break;
                }

            } else if (selection.equals("4")) {
                while (true) {
                    int newAuthorID;
                    while (true) {
                        System.out.print("Enter Author ID to Edit: ");
                        String newAuthorIDInput = input.nextLine();

                        if (newAuthorIDInput.length() > 11) {
                            System.out.println("ERROR: Author ID cannot exceed 11 digits.");
                            continue;
                        }

                        try {
                            newAuthorID = Integer.parseInt(newAuthorIDInput);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: Author ID must be an integer.");
                        }
                    }

                    if (!library.getAuthorIDs().contains(newAuthorID)) {
                        System.out.println("ERROR: Author ID not found.");
                        return;
                    }

                    String newFirstName;
                    while (true) {
                        System.out.print("Enter new First Name: ");
                        newFirstName = input.nextLine();

                        if (newFirstName.length() > 20) {
                            System.out.println("ERROR: First Name cannot exceed 20 characters.");
                        } else {
                            break;
                        }
                    }

                    String newLastName;
                    while (true) {
                        System.out.print("Enter new Last Name: ");
                        newLastName = input.nextLine();

                        if (newLastName.length() > 30) {
                            System.out.println("ERROR: Last Name cannot exceed 30 characters.");
                        } else {
                            break;
                        }
                    }

                    db.editAuthor(newAuthorID, newFirstName, newLastName);
                    System.out.println("Author details updated successfully!");

                    break;
                }
            } else if (selection.equals("5")) {
                String newISBN;
                while (true) {
                    System.out.println("Enter new ISBN Number: ");
                    newISBN = input.nextLine();

                    if (newISBN.length() > 20) {
                        System.out.println("ERROR: ISBN number cannot exceed 20 characters.");
                        continue;
                    }

                    break;
                }

                String newTitle;
                while (true) {
                    System.out.println("Enter new Book Title: ");
                    newTitle = input.nextLine();

                    if (newTitle.length() > 100) {
                        System.out.println("ERROR: Book title cannot exceed 100 characters.");
                        continue;
                    }

                    break;
                }

                String newEditionNumberInput;
                int newEditionNumber;
                while (true) {
                    System.out.println("Enter new Edition Number: ");
                    newEditionNumberInput = input.nextLine();

                    if (newEditionNumberInput.length() > 11) {
                        System.out.println("ERROR: Edition number cannot exceed 11 digits.");
                        continue;
                    }

                    try {
                        newEditionNumber = Integer.parseInt(newEditionNumberInput);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Edition number must be an integer.");
                    }
                }

                String newCopyright;
                while (true) {
                    System.out.println("Enter new Copyright Number: ");
                    newCopyright = input.nextLine();

                    if (newCopyright.length() > 4) {
                        System.out.println("ERROR: Copyright number cannot exceed 4 characters.");
                        continue;
                    }

                    break;
                }

                List<Integer> newAuthorList = new ArrayList<>();
                while (true) {
                    System.out.println("Enter Author(s) ID (0 to quit): ");

                    String authorInput = input.nextLine();
                    Integer newAuthor;

                    try {
                        newAuthor = Integer.parseInt(authorInput);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Author ID must be an integer.");
                        continue;
                    }

                    if (newAuthor.equals(0)) {
                        break;
                    } else if (library.getAuthorIDs().contains(newAuthor)) {
                        if (newAuthorList.contains(newAuthor)) {
                            System.out.println("Author already added to book!");
                        } else {
                            newAuthorList.add(newAuthor);
                        }
                    } else {
                        System.out.println("Author does not exist!");
                    }
                }

                db.addBook(newISBN, newTitle, newEditionNumber, newCopyright, newAuthorList);

            } else if (selection.equals("6")) {
                String newFirstName;
                String newLastName;
                while (true) {
                    System.out.println("Enter new First Name: ");
                    newFirstName = input.nextLine();

                    if (newFirstName.length() > 20) {
                        System.out.println("ERROR: First Name cannot exceed 20 characters.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.println("Enter new Last Name: ");
                    newLastName = input.nextLine();

                    if (newLastName.length() > 30) {
                        System.out.println("ERROR: Last Name cannot exceed 30 characters.");
                        continue;
                    }
                    break;
                }

                db.addAuthor(newFirstName, newLastName);
            }
        }
    }
}
