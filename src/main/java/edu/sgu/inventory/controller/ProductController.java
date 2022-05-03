package edu.sgu.inventory.controller;

import edu.sgu.inventory.dto.BaseResponse;
import edu.sgu.inventory.dto.ProductDto;
import edu.sgu.inventory.entity.Product;
import edu.sgu.inventory.entity.Tag;
import edu.sgu.inventory.repository.ProductRepository;
import edu.sgu.inventory.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/tags")
    public ResponseEntity<BaseResponse<List<Tag>>> readTag(@RequestBody List<Tag> tags){
        List<Tag> resTag = tags.stream().map(t->{
            Tag tag = tagRepository.findById(t.getTagId()).get();
            tag.setCount(t.getCount());
            tag.setRssi(t.getRssi());
            return tag;
        }).collect(Collectors.toList());

        BaseResponse<List<Tag>> baseResponse= new BaseResponse<>();
        baseResponse.setData(tagRepository.saveAll(resTag));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductDto>>> getAll(){
        BaseResponse<List<ProductDto>> baseResponse= new BaseResponse<>();
        List<Product> products= productRepository.findAll();
        List<ProductDto> result= products.stream().map(p->{
            ProductDto dto= new ProductDto();
            dto.setProductId(p.getProductId());
            dto.setName(p.getName());
            dto.setStock(p.getStock());
            dto.setTags(p.getTags());
            return dto;
        }).collect(Collectors.toList());
        baseResponse.setData(result);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
