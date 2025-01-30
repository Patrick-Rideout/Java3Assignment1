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

//    public static final String LOAD_ISBN_QUERY = "SELECT a.authorID, a.firstName, a.lastName, t.isbn, t.title, t.editionNumber, t.copyright FROM authors a JOIN authorISBN i ON a.authorID = i.authorID JOIN titles t ON i.isbn = t.isbn;\n";




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





}
