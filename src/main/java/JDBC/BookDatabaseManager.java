package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages database operations for books and authors.
 * Provides methods to load, add, edit, and delete books and authors in a MariaDB database.
 */
public class BookDatabaseManager {

    private static final String DB_NAME = "/books";
    private static final String LOAD_BOOKS_QUERY = "SELECT isbn, title, editionNumber, copyright FROM titles";
    private static final String LOAD_AUTHORS_QUERY = "SELECT authorID, firstName, lastName FROM authors";
    private static final String LOAD_ISBN_QUERY = "SELECT authorID, isbn FROM authorisbn;";

    /**
     * Loads all books from the database.
     *
     * @return A list of books retrieved from the database.
     */
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(LOAD_BOOKS_QUERY)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getInt("editionNumber"),
                        rs.getString("copyright")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Loads all authors from the database.
     *
     * @return A list of authors retrieved from the database.
     */
    public List<Author> loadAuthors() {
        List<Author> authors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(LOAD_AUTHORS_QUERY)) {

            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("authorID"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    /**
     * Loads the mapping between authors and books.
     *
     * @return A map where the key is an author ID and the value is a list of associated book ISBNs.
     */
    public Map<Integer, List<String>> loadISBN() {
        Map<Integer, List<String>> isbnMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(LOAD_ISBN_QUERY)) {

            while (rs.next()) {
                int authorID = rs.getInt("authorID");
                String isbn = rs.getString("isbn");

                isbnMap.computeIfAbsent(authorID, k -> new ArrayList<>()).add(isbn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbnMap;
    }

    /**
     * Adds a new book to the database and links it to the specified authors.
     *
     * @param ISBN          The ISBN of the book.
     * @param title         The title of the book.
     * @param editionNumber The edition number of the book.
     * @param copyright     The copyright information of the book.
     * @param authorIDs     A list of author IDs associated with the book.
     */
    public void addBook(String ISBN, String title, int editionNumber, String copyright, List<Integer> authorIDs) {
        String ADD_BOOK = "INSERT INTO titles (isbn, title, editionNumber, copyright) VALUES ('" + ISBN + "', '" + title + "', " + editionNumber + ", '" + copyright + "');";

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(ADD_BOOK);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Integer authorID : authorIDs) {
            String ADD_AUTHOR_LINK = "INSERT INTO authorisbn (authorID, isbn) VALUES (" + authorID + ", '" + ISBN + "');";

            try (Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(ADD_AUTHOR_LINK);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a new author to the database.
     *
     * @param firstName The first name of the author.
     * @param lastName  The last name of the author.
     */
    public void addAuthor(String firstName, String lastName) {
        String ADD_AUTHOR = "INSERT INTO authors (firstName, lastName) VALUES ('" + firstName + "', '" + lastName + "');";

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(ADD_AUTHOR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a book's information in the database.
     *
     * @param ISBN           The ISBN of the book to update.
     * @param newTitle       The new title of the book.
     * @param newEditionNumber The new edition number of the book.
     * @param newCopyright   The new copyright information of the book.
     */
    public void editBook(String ISBN, String newTitle, int newEditionNumber, String newCopyright) {
        String EDIT_BOOK = "UPDATE titles SET title = '" + newTitle + "', editionNumber = " + newEditionNumber + ", copyright = '" + newCopyright + "' WHERE isbn = '" + ISBN + "';";

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(EDIT_BOOK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an author's information in the database.
     *
     * @param authorID The ID of the author to update.
     * @param firstName The new first name of the author.
     * @param lastName  The new last name of the author.
     */
    public void editAuthor(Integer authorID, String firstName, String lastName) {
        String EDIT_AUTHOR = "UPDATE authors SET firstName = '" + firstName + "', lastName = '" + lastName + "' WHERE authorID = " + authorID + ";";

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(EDIT_AUTHOR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a book from the database.
     *
     * @param ISBN The ISBN of the book to delete.
     */
    public void deleteBook(String ISBN) {
        String DELETE_AUTHOR_LINK = "DELETE FROM authorisbn WHERE isbn = '" + ISBN + "';";
        String DELETE_BOOK = "DELETE FROM titles WHERE isbn = '" + ISBN + "';";

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DELETE_AUTHOR_LINK);
            stmt.executeUpdate(DELETE_BOOK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an author from the database.
     *
     * @param authorID The ID of the author to delete.
     */
    public void deleteAuthor(int authorID) {
        String DELETE_AUTHOR_LINK = "DELETE FROM authorisbn WHERE authorID = " + authorID + ";";
        String DELETE_AUTHOR = "DELETE FROM authors WHERE authorID = " + authorID + ";";

        try (Connection conn = DriverManager.getConnection(
                DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DELETE_AUTHOR_LINK);
            stmt.executeUpdate(DELETE_AUTHOR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
