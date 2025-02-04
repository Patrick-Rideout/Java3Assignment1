package JDBC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Library {

    private List<Book> libraryBooks;

    private List<Author> libraryAuthors;

    private Map<Integer, List<String>> isbnMap;

    public Library(List<Book> libraryBooks, List<Author> libraryAuthors, Map<Integer, List<String>> isbnMap) {
        this.libraryBooks = libraryBooks;
        this.libraryAuthors = libraryAuthors;
        this.isbnMap = isbnMap;

        libraryAuthors.forEach(author -> {
            if (isbnMap.containsKey(author.getAuthorID())) {
                List<Book> books = new ArrayList<>();

                isbnMap.get(author.getAuthorID()).forEach(isbn -> {

                    libraryBooks.forEach(book -> {
                        if (book.getIsbn().equals(isbn)) {
                            books.add(book);
                        }
                    });
                });
                author.setBookList(books);
            }
        });

        libraryBooks.forEach(book -> {
            List<Author> authors = new ArrayList<>();

            isbnMap.forEach((authorID, isbn) -> {
                if (isbn.contains(book.getIsbn())) {
                    libraryAuthors.forEach(author -> {
                        if (author.getAuthorID() == authorID) {
                            authors.add(author);
                        }
                    });
                }
            });

            book.setAuthorList(authors);
        });
    }

    public Map<Integer, List<String>> getIsbnMap() {
        return isbnMap;
    }

    public void setIsbnMap(Map<Integer, List<String>> isbnMap) {
        this.isbnMap = isbnMap;
    }

    public List<Author> getLibraryAuthors() {
        return libraryAuthors;
    }

    public void setLibraryAuthors(List<Author> libraryAuthors) {
        this.libraryAuthors = libraryAuthors;
    }

    public List<Book> getLibraryBooks() {
        return libraryBooks;
    }

    public void setLibraryBooks(List<Book> libraryBooks) {
        this.libraryBooks = libraryBooks;
    }

    public List<Integer> getAuthorIDs() {
        List<Integer> authorIDs = new ArrayList<>();
        libraryAuthors.forEach(author -> {
            authorIDs.add(author.getAuthorID());
        });
        return authorIDs;
    }

    public List<String> getBookISBNs() {
        List<String> bookISBNs = new ArrayList<>();
        libraryBooks.forEach(book -> {
            bookISBNs.add(book.getIsbn());
        });
        return bookISBNs;
    }
}
