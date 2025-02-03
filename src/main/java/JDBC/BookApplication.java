package JDBC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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




            } else if (selection.equals("4")) {

            } else if (selection.equals("5")) {

            } else if (selection.equals("6")) {
                break;
            }



        }

        }


}
