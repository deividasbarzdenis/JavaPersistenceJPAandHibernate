package lt.debarz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The ProductPriceId class (Listing 1) is a simple Java class that has two member variables: region and productId.
 * It is annotated with the @Embeddable annotation.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class ProductPriceId implements Serializable {
    private String region;
    private Integer productId;
}
