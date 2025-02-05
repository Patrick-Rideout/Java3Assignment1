package JDBC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a library that manages a collection of books and authors.
 * This class maintains relationships between books and authors using an ISBN mapping.
 */
public class Library {

    private List<Book> libraryBooks;
    private List<Author> libraryAuthors;
    private Map<Integer, List<String>> isbnMap;

    /**
     * Constructs a Library object with a list of books, a list of authors, and an ISBN mapping.
     * This constructor establishes relationships between authors and books based on the provided mapping.
     *
     * @param libraryBooks  A list of books available in the library.
     * @param libraryAuthors A list of authors associated with the library.
     * @param isbnMap A mapping of author IDs to lists of book ISBNs, establishing author-book relationships.
     */
    public Library(List<Book> libraryBooks, List<Author> libraryAuthors, Map<Integer, List<String>> isbnMap) {
        this.libraryBooks = libraryBooks;
        this.libraryAuthors = libraryAuthors;
        this.isbnMap = isbnMap;

        // Associate books with authors
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

        // Associate authors with books
        libraryBooks.forEach(book -> {
            List<Author> authors = new ArrayList<>();

            isbnMap.forEach((authorID, isbnList) -> {
                if (isbnList.contains(book.getIsbn())) {
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

    /**
     * Gets the mapping of author IDs to lists of book ISBNs.
     *
     * @return A map where each author ID maps to a list of book ISBNs.
     */
    public Map<Integer, List<String>> getIsbnMap() {
        return isbnMap;
    }

    /**
     * Sets the mapping of author IDs to book ISBNs.
     *
     * @param isbnMap A new mapping of author IDs to book ISBNs.
     */
    public void setIsbnMap(Map<Integer, List<String>> isbnMap) {
        this.isbnMap = isbnMap;
    }

    /**
     * Gets the list of authors in the library.
     *
     * @return A list of authors.
     */
    public List<Author> getLibraryAuthors() {
        return libraryAuthors;
    }

    /**
     * Sets the list of authors in the library.
     *
     * @param libraryAuthors A new list of authors.
     */
    public void setLibraryAuthors(List<Author> libraryAuthors) {
        this.libraryAuthors = libraryAuthors;
    }

    /**
     * Gets the list of books in the library.
     *
     * @return A list of books.
     */
    public List<Book> getLibraryBooks() {
        return libraryBooks;
    }

    /**
     * Sets the list of books in the library.
     *
     * @param libraryBooks A new list of books.
     */
    public void setLibraryBooks(List<Book> libraryBooks) {
        this.libraryBooks = libraryBooks;
    }

    /**
     * Retrieves a list of all author IDs in the library.
     *
     * @return A list of author IDs.
     */
    public List<Integer> getAuthorIDs() {
        List<Integer> authorIDs = new ArrayList<>();
        libraryAuthors.forEach(author -> authorIDs.add(author.getAuthorID()));
        return authorIDs;
    }

    /**
     * Retrieves a list of all book ISBNs in the library.
     *
     * @return A list of book ISBNs.
     */
    public List<String> getBookISBNs() {
        List<String> bookISBNs = new ArrayList<>();
        libraryBooks.forEach(book -> bookISBNs.add(book.getIsbn()));
        return bookISBNs;
    }
}
