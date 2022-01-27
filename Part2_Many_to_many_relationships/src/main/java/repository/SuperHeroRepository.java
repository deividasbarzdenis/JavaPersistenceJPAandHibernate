package repository;

import model.SuperHero;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SuperHeroRepository {
    private EntityManager entityManager;

    public SuperHeroRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Optional<SuperHero> save(SuperHero superHero) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(superHero);
            entityManager.getTransaction().commit();
            return Optional.of(superHero);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<SuperHero> findById(Integer id) {
        SuperHero superHero = entityManager.find(SuperHero.class, id);
        return superHero != null ? Optional.of(superHero) : Optional.empty();
    }

    public List<SuperHero> findAll() {
        return entityManager.createQuery("from SuperHero").getResultList();
    }

// The deleteById() method follows the same steps, but in this case its actions are to remove SuperHeroes
// from all of its associated Movies.
    public void deleteById(Integer id) {
        // Retrieve the movie with this ID
        SuperHero superHero = entityManager.find(SuperHero.class, id);
        if (superHero != null) {
            try {
                // Start a transaction because we're going to change the database
                entityManager.getTransaction().begin();

                // Remove all references to this superhero in its movies
                superHero.getMovies().forEach(movie -> {
                    movie.getSuperHeroes().remove(superHero);
                });

                // Now remove the superhero
                entityManager.remove(superHero);

                // Commit the transaction
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Now, there are plenty of examples on the Internet of many-to-many relationships that use CascadeType.
     * ALL as a shortcut operation for relationship persistence. So, let's say we set the cascade type strategy
     * to CascadeType.ALL for both of these entities. Then, let's say we decided to delete "Avengers: Infinity War."
     * What do you think would happen?
     * In this configuration, deleting "Avengers: Infinity War" would cascade the delete operation to both Ironman
     * and Thor. Worse, because they both have cascading constraints to "The Avengers," that movie would also
     * be deleted. Deleting one movie would end up deleting the entire database!
     *
     * That's why I recommend setting the cascade type to PERSIST. In this case, saving a Movie will save its
     * SuperHeroes, but deleting a Movie will not delete its SuperHeroes.
     *
     * The downside of PERSIST is that we'll need to perform a few additional steps if we ever do decide to delete
     * an entity in our domain model. The upside is that if we delete a Movie entity (such as "Avengers: Infinity War")
     * we won't also end up deleting Thor and Ironman.
     * */
}
