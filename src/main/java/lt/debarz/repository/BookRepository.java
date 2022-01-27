package lt.debarz.repository;

import lt.debarz.model.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * The BookRepository is initialized with an EntityManager, which we'll create in our sample application.
 * The first method, findById(), invokes EntityManager's find() method, which retrieves an entity of
 * a given class with its given primary key. If, for example, we add a new book and its primary key is generated as "1,"
 * then entityManager.find(Book.class, 1) will return the Book with an ID of 1. If a Book with the requested primary
 * key is not found in the database, then the find() method returns null. Because we want our code to be more resilient
 * and not pass nulls around, it checks the value for null and returns either a valid book, wrapped in an Optional,
 * or Optional.empty().
 * */
public class BookRepository {

    private EntityManager entityManager;
    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Book> findById(Integer id){
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }
    public List<Book> findAll(){
        return entityManager.createQuery("from Book").getResultList();
    }
    public Optional<Book> findByName(String name){
        Book book = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name",
                Book.class)
                .setParameter("name", name)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }
    public Optional<Book> findByNameNamedQuery(String name){
        Book book = entityManager.createNamedQuery("Book.findByName", Book.class)
                .setParameter("name", name)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }
    /**
     * the save() method saves a Book to the database. Both the persist and merge operations, which update
     * the database, need to run in a transaction. We retrieve the resource-level EntityTransaction by invoking
     * the EntityManager::getTransaction method and wrap the persist call in begin() and commit() calls.
     * We opt to persist() the book to the database so that the book will be "managed" and saved to the database.
     * */
    public Optional<Book> save(Book book){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return Optional.of(book);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
