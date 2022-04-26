package edu.sgu.inventory.dto;

import edu.sgu.inventory.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductImport {
    public ProductImport() {
    }

    public ProductImport(String productId, String name, Integer price, Integer stock, String color, String tagId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.color = color;
        this.tagId = tagId;
    }

    private String productId;
    private String name;
    private Integer price;
    private Integer stock;
    private String color;
    private String  tagId;

}
