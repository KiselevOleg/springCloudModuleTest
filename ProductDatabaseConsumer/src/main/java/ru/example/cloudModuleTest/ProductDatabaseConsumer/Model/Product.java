package ru.example.cloudModuleTest.ProductDatabaseConsumer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "public")
@SuppressWarnings("MagicNumber")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 50, nullable = true, unique = false)
    private String name;
    @Column(name = "quantity", nullable = true, unique = false)
    private Integer quantity;
    @Column(name = "price", nullable = true, unique = false)
    private Float price;
}
