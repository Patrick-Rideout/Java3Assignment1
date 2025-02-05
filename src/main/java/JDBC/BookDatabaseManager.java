package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDatabaseManager {

    private static final String DB_NAME = "/books";
    private static final String LOAD_BOOKS_QUERY = "SELECT isbn, title, editionNumber, copyright FROM titles";
    private static final String LOAD_AUTHORS_QUERY = "SELECT authorID, firstName, lastName FROM authors";
    private static final String LOAD_ISBN_QUERY = "SELECT authorID, isbn FROM authorisbn;";

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<Book>();

        try{
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(LOAD_BOOKS_QUERY);

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

    public List<Author> loadAuthors() {
        List<Author> authors = new ArrayList<Author>();

        try{
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(LOAD_AUTHORS_QUERY);

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

    public Map<Integer, List<String>> loadISBN() {
        Map<Integer, List<String>> isbnMap = new HashMap<>();

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(LOAD_ISBN_QUERY);

            while (rs.next()) {
                int authorID = rs.getInt("authorID");
                String isbn = rs.getString("isbn");

                if (isbnMap.containsKey(authorID)) {
                    isbnMap.get(authorID).add(isbn);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(isbn);
                    isbnMap.put(authorID, list);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbnMap;
    }

    public void addBook(String ISBN, String title, int editionNumber, String copyright, List<Integer> authorIDs) {

        String ADD_BOOK = "INSERT INTO titles (isbn, title, editionNumber, copyright) VALUES (\'" + ISBN + "\', \'" + title + "\' , \'" + editionNumber + "\', \'" + copyright + "\');";

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(ADD_BOOK);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Integer authorID : authorIDs) {

            String ADD_AUTHOR_LINK = "INSERT INTO authorisbn (authorID, isbn) VALUES (" + authorID + ", \'" + ISBN + "\');";

            try {
                Connection conn = DriverManager.getConnection(
                        DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(ADD_AUTHOR_LINK);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addAuthor(String firstName, String lastName) {

        String ADD_AUTHOR = "INSERT INTO authors (firstName, lastName) VALUES (\'" + firstName + "\', \'" + lastName + "\');";

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(ADD_AUTHOR);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBook(String ISBN, String newTitle, int newEditionNumber, String newCopyright) {

        String EDIT_BOOK = "UPDATE titles SET title = \'" + newTitle + "\', editionNumber = " + newEditionNumber + ", copyright = \'" + newCopyright + "\' WHERE isbn = \'" + ISBN + "\';";

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(EDIT_BOOK);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editAuthor(Integer authorID, String firstName, String lastName) {

        String EDIT_AUTHOR = "UPDATE authors SET firstName = \'" + firstName + "\', lastName = \'" + lastName + "\' WHERE authorID = \'" + authorID + "\';";

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(EDIT_AUTHOR);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(String ISBN) {

        String DELETE_AUTHOR_LINK = "DELETE FROM authorisbn WHERE isbn = \'" + ISBN + "\';";
        String DELETE_BOOK = "DELETE FROM titles WHERE isbn = \'" + ISBN + "\';";

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(DELETE_AUTHOR_LINK);

            stmt.executeUpdate(DELETE_BOOK);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(int authorID) {

        String DELETE_AUTHOR_LINK = "DELETE FROM authorisbn WHERE authorID = " + authorID + ";";
        String DELETE_AUTHOR = "DELETE FROM authors WHERE authorID = " + authorID + ";";

        try {
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(DELETE_AUTHOR_LINK);

            stmt.executeUpdate(DELETE_AUTHOR);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
