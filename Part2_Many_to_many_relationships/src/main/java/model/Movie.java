package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<SuperHero> superHeroes = new HashSet<>();

    public Movie() {
    }

    public Movie(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<SuperHero> getSuperHeroes() {
        return superHeroes;
    }

    /**
     * When configuring entities for persistence, it isn't enough to simply add a superhero to a movie;
     * we also need to update the other side of the relationship. This means we need to add the movie to the superhero.
     * When both sides of the relationship are configured properly, so that the movie has a reference to the superhero
     * and the superhero has a reference to the movie, then the join table will also be properly populated.
     * */
    public void addSuperHero(SuperHero superHero) {
        superHeroes.add(superHero);
        superHero.getMovies().add(this);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
