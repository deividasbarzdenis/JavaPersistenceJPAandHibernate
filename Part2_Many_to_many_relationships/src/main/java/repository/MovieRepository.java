package repository;

import model.Movie;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    private EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * The save() method persists a Movie to the database. All operations that change the database need
     * to be executed in a transaction, so the save() method wraps the persist() call in between two transaction
     * method calls: begin() and commit(). We persist the Movie to the database using the EntityManager::persist method,
     * which makes the movie "managed" by the EntityManager and saves it to the database.
     * */
    /**
     * The alternative to persist would be merge(), which saves the entity in an "unmanaged" state--meaning that it would
     * save a copy of the entity to the database. When the transaction is committed, the id is automatically generated,
     * so using the persist() method ensures that we will see that new, automatically generated key. Because the cascade
     * type for SuperHero is set to PERSIST, saving a Movie also automatically saves its SuperHeroes.
     * */
    public Optional<Movie> save(Movie movie) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();
            return Optional.of(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
/**
 * The findById() method executes the EntityManager::find method, which retrieves an entity of the
 * specified class by its primary key. In this case, we query for the Movie.class with the specified id.
 * If the movie is found, then it returns it; otherwise it returns null. Rather than dealing with potential null values,
 * the findById() method wraps the movie in an Optional, or returns Optional.empty() if the EntityManager has returned null.
 * */
    public Optional<Movie> findById(Integer id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie != null ? Optional.of(movie) : Optional.empty();
    }

    /**
     * The findAll() method executes the JPQL query "from Movie," which is shorthand for the more explicit query:
     * SELECT m FROM Movie m. This query returns a list containing all Movies in the database.
     * */
    public List<Movie> findAll() {
        return entityManager.createQuery("from Movie").getResultList();
    }

    /**
     * The steps to delete a Movie entity are as follows:
     *
     *     Retrieve the Movie with the specified id from the EntityManager.
     *     Begin a transaction.
     *     Remove all references to Movie from each of its SuperHeroes.
     *     Remove the Movie.
     *     Commit the transaction.
     *
     * The first thing to note is that we can only remove an entity that is managed by the EntityManager.
     * If we have an unmanaged entity (such as a Movie instance that we created with the correct id) then the
     * EntityManager::remove method will fail. This is important to understand because you might be tempted to
     * pass in a Movie instance that has been detached from the EntityManager--such as the result of a findById()
     * method call--but unless it is managed by the EntityManager, the remove() will not work.
     * Assuming we're looking for an entity that is managed by the EntityManager, our first step is to retrieve the
     * Movie using its specified id. Next, we need to remove all references to the Movie in all of the SuperHero entities.
     * If we forget this step then the join table will not be properly cleaned up. In fact, those relationships will
     * stop the Movie from being deleted.
     *
     * Finally, once we have a managed Movie that does not have any references to it, we can safely call
     * the EntityManager::remove method.
     * */
    public void deleteById(Integer id) {
        // Retrieve the movie with this ID
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            try {
                // Start a transaction because we're going to change the database
                entityManager.getTransaction().begin();

                // Remove all references to this movie by superheroes
                movie.getSuperHeroes().forEach(superHero -> {
                    superHero.getMovies().remove(movie);
                });

                // Now remove the movie
                entityManager.remove(movie);

                // Commit the transaction
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
