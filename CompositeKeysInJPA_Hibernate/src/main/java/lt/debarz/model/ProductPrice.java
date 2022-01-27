package lt.debarz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The ProductPrice class (Listing 2) is a JPA entity that is mapped to the "PRODUCT_PRICE" table and defines an
 * id field of type ProductPriceId. The ProductPriceId field type is annotated with the @EmbeddedId annotation.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUCT_PRICE")
public class ProductPrice {
    @EmbeddedId
    private ProductPriceId id;
    private Double price;
}
