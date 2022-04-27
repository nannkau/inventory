package edu.sgu.inventory.controller;

import edu.sgu.inventory.dto.ProductImport;
import edu.sgu.inventory.entity.Product;
import edu.sgu.inventory.entity.Tag;
import edu.sgu.inventory.repository.ProductRepository;
import edu.sgu.inventory.repository.TagRepository;
import edu.sgu.inventory.utils.FileExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FileController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;
    @RequestMapping("/import")
    public String index(Model model){

        return "upload";
    }
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public String upload(@RequestParam("productFile") MultipartFile productFile) {
        List<ProductImport> productImports= FileExcelUtil.readProductFromFile(productFile);
        productImports.stream().forEach(p->{
            Product product= productRepository.findById(p.getProductId()).orElseGet(() -> null);
            if(product==null){
                product = new Product();
                product.setProductId(p.getProductId());
                product.setName(p.getName());
                product.setColor(p.getColor());
                product.setStock(p.getStock());
                product.setPrice(p.getPrice());
            }
            Tag tag=tagRepository.findById(p.getTagId()).orElseGet(() -> null);
            if(tag==null){
                tag= new Tag();
                tag.setTagId(p.getTagId());
                tag.setRssi("none");
                tag.setCount(0);
            }
            if (product.getTags()==null){
               product.setTags(Arrays.asList(tag));
            }else {
                product.getTags().add(tag);
            }

           productRepository.save(product);
        });
        return "redirect:/";
    }
}
