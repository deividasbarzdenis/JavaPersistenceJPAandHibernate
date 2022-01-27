package main.java.lt.debarz.model1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "SUV")
@DiscriminatorValue("SUV")
public class SportsUtilityVehicle extends Car {
    private Integer towingCapacity;
    private Boolean thirdRowSeating;
}
