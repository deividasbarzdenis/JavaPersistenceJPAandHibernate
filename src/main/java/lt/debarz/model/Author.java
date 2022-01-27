package lt.debarz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Author class isn't much different from the Book class:
 *
 *     The @Entity annotation identifies Author as a JPA entity.
 *     The @Table annotation tells Hibernate that this entity should be stored in the AUTHOR table.
 *     The @Table annotation also defines an Author.findByName named query.
 *
 * The Author class maintains a list of books written by the given author, which is annotated with
 * the @OneToMany annotation. Author's @OneToMany annotation matches the @ManyToOne annotation on the Book class.
 * The mappedBy field tells Hibernate that this field is stored in the Book's author property.
 * */
@Entity
@Table(name="AUTHOR")
@NamedQueries({
        @NamedQuery(name = "Author.findByName",
                query = "SELECT a FROM Author a WHERE a.name = :name")
})
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private final List<Book> books = new ArrayList<>();
    public Author() {
    }
    public Author(String name) {
        this.name = name;
    }
    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
