package JDBC;

import java.util.*;

public class BookApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        BookDatabaseManager db = new BookDatabaseManager();

        System.out.printf("WELCOME TO THE BOOK APPLICATION\n");

        while (true) {

            System.out.println("\nSelections:");
            System.out.println("""
                    1. Print Books
                    2. Print Authors
                    3. Edit Book
                    4. Edit Author
                    5. Add Book
                    6. Quit
                    """);

            System.out.println("\nENTER SELECTION:");
            String selection = input.nextLine();

            if (selection.equals("1")) {

                List<Book> listOfBooks = db.loadBooks();
                List<Author> listOfAuthors = db.loadAuthors();
                Map<Integer, List<String>> isbnMap = db.loadISBN();

                Library library = new Library(listOfBooks, listOfAuthors, isbnMap);

                library.getLibraryBooks().forEach(book -> {
                    StringBuilder authorNames = new StringBuilder();
                    for (Author author : book.getAuthorList()) {
                        if (authorNames.length() > 0) {
                            authorNames.append(", ");
                        }
                        authorNames.append(author.getFirstName()).append(" ").append(author.getLastName());
                    }

                    System.out.println(
                            "ISBN: (" + book.getIsbn() + ") " + "Title: (" + book.getTitle() + ") " + "Edition Number: (" + book.getEditionNumber() + ") " + "Copyright: (" + book.getCopyright() + ") " + "Authors: (" + authorNames + ")");

                });

            } else if (selection.equals("2")) {

                List<Book> listOfBooks = db.loadBooks();
                List<Author> listOfAuthors = db.loadAuthors();
                Map<Integer, List<String>> isbnMap = db.loadISBN();

                Library library = new Library(listOfBooks, listOfAuthors, isbnMap);

                library.getLibraryAuthors().forEach(author -> {
                    StringBuilder bookTitles = new StringBuilder();

                    for (Book book : author.getBookList()) {
                        if (bookTitles.length() > 0) {
                            bookTitles.append(", ");
                        }
                        bookTitles.append(book.getTitle());
                    }

                    System.out.println("Author: " + author.getFirstName() + " " + author.getLastName() +
                            " | Books: (" + bookTitles + ")");
                });

            } else if (selection.equals("3")) {

                List<Book> listOfBooks = db.loadBooks();
                List<Author> listOfAuthors = db.loadAuthors();
                Map<Integer, List<String>> isbnMap = db.loadISBN();

                Library library = new Library(listOfBooks, listOfAuthors, isbnMap);

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

                        int newEditionNumber;
                        while (true) {
                            System.out.println("Enter new Edition Number: ");
                            String editionInput = input.nextLine();

                            if (editionInput.length() > 11) {
                                System.out.println("ERROR: Edition number cannot exceed 11 digits.");
                                continue;
                            }

                            try {
                                newEditionNumber = Integer.parseInt(editionInput);
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


            } else if (selection.equals("5")) {

                List<Book> listOfBooks = db.loadBooks();
                List<Author> listOfAuthors = db.loadAuthors();
                Map<Integer, List<String>> isbnMap = db.loadISBN();

                Library library = new Library(listOfBooks, listOfAuthors, isbnMap);

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

                int newEditionNumber;
                while (true) {
                    System.out.println("Enter new Edition Number: ");
                    String editionInput = input.nextLine();

                    if (editionInput.length() > 11) {
                        System.out.println("ERROR: Edition number cannot exceed 11 digits.");
                        continue;
                    }

                    try {
                        newEditionNumber = Integer.parseInt(editionInput);
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
                break;
            }





        }

        }


}
