package JDBC;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book with an ISBN, title, edition number, copyright information, and a list of authors.
 * This class models a book entity in a database and maintains a collection of authors associated with the book.
 */
public class Book {

    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList;

    /**
     * Constructs a Book object with the specified details.
     * Initializes the author list as an empty ArrayList.
     *
     * @param isbn          The International Standard Book Number (ISBN) of the book.
     * @param title         The title of the book.
     * @param editionNumber The edition number of the book.
     * @param copyright     The copyright year of the book.
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new ArrayList<>();
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn The new ISBN for the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The new title for the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the edition number of the book.
     *
     * @return The edition number of the book.
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Sets the edition number of the book.
     *
     * @param editionNumber The new edition number for the book.
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Gets the copyright information of the book.
     *
     * @return The copyright information of the book.
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets the copyright information of the book.
     *
     * @param copyright The new copyright information for the book.
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Gets the list of authors associated with the book.
     *
     * @return A list of authors who contributed to the book.
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Sets the list of authors for the book.
     *
     * @param authorList A new list of authors associated with the book.
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
