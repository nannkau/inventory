package edu.sgu.inventory.controller;

import edu.sgu.inventory.dto.Report;
import edu.sgu.inventory.entity.Product;
import edu.sgu.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReportController {
    @Autowired
    private ProductRepository productRepository;
    @RequestMapping("/")
    public String index(Model model){
        List<Product> products = productRepository.findAll();
        List<Report> reports = products.stream().map(product -> {
            Report report = new Report();
            report.setProductId(product.getProductId());
//            report.setRealValue(product.getTags().stream().filter(tag -> tag.getCount() > 0).collect(Collectors.toList()).size());
            report.setRealValue(4);
            report.setStock(product.getStock());
            return report;
        }).collect(Collectors.toList());
        model.addAttribute("reports",reports);
        return "index";
    }
}
