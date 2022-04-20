package edu.sgu.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse <T>{
    private String error;
    private T data;
}
