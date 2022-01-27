package main.java.lt.debarz.modelJoined;

import lombok.Data;

import javax.persistence.*;

/**
 * Using this strategy results in tables that don't have a lot of null values, as they did with the SINGLE_TABLE
 * strategy. You also don't have to write the full set of table unions that the TABLE_PER_CLASS requires.
 * On the downside, Hibernate has to perform a join between the base class table and the specialized class tables,
 * which makes queries considerably more complex.
* */
//@MappedSuperclass
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Car {
    @Id
    @GeneratedValue
    private Integer id;
    private String make;
    private String model;
    private Integer year;

    /**
     * Choosing a JPA inheritance strategy
     * With four choices, the decision of which strategy to use depends on your use case. In general,
     * I recommend avoiding the TABLE_PER_CLASS strategy because of the query overhead.
     * I also suggest avoiding the MAPPED_SUPERCLASS strategy--or using it only for cases where
     * you don't need to use your classes polymorphically.
     *
     * That leaves the SINGLE_TABLE and JOINED strategies.
     *
     * If you need fast queries and don't mind maintaining extra, unused null columns in a single table,
     * then go with the SINGLE_TABLE strategy.
     * If you don't want to maintain unused columns and you want to model your inheritance relationships
     * more like you would when thinking about a database model, then use the JOINED strategy.
     * Personally, I almost always use JOINED for inheritance relationships in JPA.
     * */
}
