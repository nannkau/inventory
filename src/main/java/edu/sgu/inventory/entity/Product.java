package edu.sgu.inventory.entity;

import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;
    private String name;
    private Integer price;
    private Integer stock;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Tag> tags;
}
