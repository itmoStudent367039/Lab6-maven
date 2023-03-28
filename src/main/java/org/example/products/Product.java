package org.example.products;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.exceptions.ValidException;


import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect
@Setter
@Getter
public class Product implements Valid, Comparable<Product> {
    private UUID id;
    private String name;
    private Coordinates coordinates;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
    private java.time.ZonedDateTime creationDate;
    private int price;
    private UnitOfMeasure unitOfMeasure;
    private Person owner;

    public Product() {
        super();
    }

    @Override
    public boolean isValid() throws ValidException {
        boolean isValid = !Objects.isNull(id) &&
                !Objects.isNull(name) &&
                !name.isEmpty() &&
                !Objects.isNull(coordinates) &&
                !Objects.isNull(creationDate) &&
                price > 0 &&
                !Objects.isNull(unitOfMeasure) &&
                !Objects.isNull(owner) &&
                coordinates.isValid() && owner.isValid();
        if (!isValid) {
            throw new ValidException("Element isn't valid");
        }
        return true;
    }

    @Override
    public int compareTo(Product o) {
        int eps = o.price - this.getPrice();
        if (eps != 0) {
            return eps;
        } else {
            return this.name.toUpperCase().compareTo(o.getName().toUpperCase());
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", unitOfMeasure=" + unitOfMeasure +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
