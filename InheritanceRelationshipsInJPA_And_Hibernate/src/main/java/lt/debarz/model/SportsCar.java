package main.java.lt.debarz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "SPORTSCAR")
public class SportsCar extends Car{

    private Integer topSpeed;
    private Double zeroToSixty;

}
