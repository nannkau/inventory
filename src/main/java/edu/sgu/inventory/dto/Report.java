package edu.sgu.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {
    private String productId;
    private Integer realValue;
    private Integer stock;
    private Integer gap;

    public Integer getGap() {
        return stock-realValue;
    }
}
