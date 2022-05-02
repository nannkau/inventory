package edu.sgu.inventory.dto;

import edu.sgu.inventory.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
public class ProductDto {
    private String productId;
    private String name;
    private Integer price;
    private Integer stock;
//    private String color;
    private List<Tag> tags;
}
