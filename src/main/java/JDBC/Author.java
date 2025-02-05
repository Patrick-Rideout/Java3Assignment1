package JDBC;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an author with an ID, first name, last name, and a list of books.
 * This class models an author entity in a database and contains a collection of books written by the author.
 */
public class Author {

    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;

    /**
     * Constructs an Author object with the specified ID, first name, and last name.
     * Initializes the book list as an empty ArrayList.
     *
     * @param authorID   Author identification number.
     * @param firstName  The first name of the author.
     * @param lastName   The last name of the author.
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new ArrayList<>();
    }

    /**
     * Gets the author's unique ID.
     *
     * @return The author's ID.
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * Sets the author's unique ID.
     *
     * @param authorID The new ID for the author.
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    /**
     * Gets the author's first name.
     *
     * @return The first name of the author.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the author's first name.
     *
     * @param firstName The new first name for the author.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the author's last name.
     *
     * @return The last name of the author.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the author's last name.
     *
     * @param lastName The new last name for the author.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the list of books written by the author.
     *
     * @return A list of books associated with the author.
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Sets the list of books for the author.
     *
     * @param bookList A new list of books associated with the author.
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}