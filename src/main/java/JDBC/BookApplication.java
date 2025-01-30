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
                List<Author> listOfAuthors = db.loadAuthors();
                List<Book> listOfBooks = db.loadBooks();
                Map<Integer, List<String>> isbnMap = db.loadISBN();

                listOfAuthors.forEach(author -> {
                    if (isbnMap.containsKey(author.getAuthorID())) {
                        List<Book> books = new java.util.ArrayList<>(List.of());

                        isbnMap.get(author.getAuthorID()).forEach(isbn -> {

                            listOfBooks.forEach(book -> {
                                if (book.getIsbn().equals(isbn)) {
                                    books.add(book);
                                }
                            });
                        });
                        author.setBookList(books);
                        }
                    });


                if (listOfBooks.isEmpty()) {
                    System.out.println("No Books Found.");
                } else {
                    for (Book book : listOfBooks) {
                        System.out.println("ISBN: (" + book.getIsbn() + ") Title: (" + book.getTitle() + ") Edition Number: (" + book.getEditionNumber() + ") Copyright: (" + book.getCopyright() + ")");
                    }

                }

            } else if (selection.equals("2")) {
                List<Author> listOfAuthors = db.loadAuthors();
                List<Book> listOfBooks = db.loadBooks();
                Map<Integer, List<String>> isbnMap = db.loadISBN();

                listOfAuthors.forEach(author -> {
                    if (isbnMap.containsKey(author.getAuthorID())) {
                        List<Book> books = new java.util.ArrayList<>(List.of());

                        isbnMap.get(author.getAuthorID()).forEach(isbn -> {

                            listOfBooks.forEach(book -> {
                                if (book.getIsbn().equals(isbn)) {
                                    books.add(book);
                                }
                            });
                        });
                        author.setBookList(books);
                    }
                });


                if (listOfAuthors.isEmpty()) {
                    System.out.println("No Authors Found.");
                } else {
                    for (Author author : listOfAuthors) {
                        System.out.printf("Author ID: (" + author.getAuthorID() + ") Title: (" + author.getFirstName() + ") Last Name: (" + author.getLastName() + ") " + "Books: ");
                        author.getBookList().forEach(book -> {
                            System.out.printf("(" + book.getTitle() + ")");
                        });
                        System.out.printf("\n");
                    }

                }

            } else if (selection.equals("3")) {

            } else if (selection.equals("4")) {

            } else if (selection.equals("5")) {

            } else if (selection.equals("6")) {
                break;
            }



        }

        }


}
