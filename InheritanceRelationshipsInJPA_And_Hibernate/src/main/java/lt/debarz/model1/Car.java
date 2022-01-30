package lt.debarz.model1;

import lombok.Data;

import javax.persistence.*;

/**
 * Inheritance strategy #3: Single Table
 * The next strategy maps all fields from all entities to a single table,
 * with a discriminator column to identify the instance type:
 *
 * The single table strategy is implemented using the @Inheritance annotation with InstanceType.SINGLE_TABLE.
 * We add to this a @DiscriminatorColumn annotation, which defines a column name we'll use to discriminate between
 * specific class types. The specialized classes, such as SportsCar and SportsUtilityVehicle, extend the base class
 * and add a @DiscriminatorValue annotation, which specifies the name to identify its instances in the database.
 *
 * The challenge with this implementation is that each table maintains all fields for all attributes
 * for all specialized classes. You also lose the ability to define specialized fields to be non-null,
 * because they will be null for other specialized classes. For example, a row representing a SportsCar
 * will have topSpeed and zeroToSixty values, but will have null towingCapacity and thirdRowSeating column values.
 * */
//@MappedSuperclass
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Car_Type")
public abstract class Car {
    @Id
    @GeneratedValue
    private Integer id;
    private String make;
    private String model;
    private Integer year;
}
