package lt.debarz.model;

import javax.persistence.*;

/**
 * The Book class is a simple POJO (plain old Java object) that manages three properties:
 *     id: The primary key, or identifier, of the book.
 *     name: The name, or title, of the book.
 *     author: The author who wrote the book.
 *
 * The class itself is annotated with three annotations:
 *     @Entity: Identifies the Book as a JPA entity.
 *     @Table: Overrides the name of the table to which this entity will be persisted. In this case we define
 *     the table name as BOOK.
 *     @NamedQueries: Allows you to define JPA Query Language queries that can later be retrieved and executed
 *     by the EntityManager.
 *
 *     The Book's id attribute is annotated with both the @Id and @GeneratedValue. The @Id annotation identifies
 *     the id as the primary key of the Book, which will resolve to the primary key of the underlying database.
 *     The @GeneratedValue annotation tells JPA that the database should generate the primary key when the entity is
 *     persisted to the database. Because we have not specified a @Column annotation, the id will be mapped to
 *     the same column name, "id."
 * */

@Entity
@Table(name = "BOOK")
@NamedQueries({
        @NamedQuery(name = "Book.findByName",
                query = "SELECT b FROM Book b WHERE b.name = :name"),
        @NamedQuery(name = "Book.findAll",
                query = "SELECT b FROM Book b")
})
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    private Author author;

    public Book() {
    }

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author.getName() +
                '}';
    }
}
