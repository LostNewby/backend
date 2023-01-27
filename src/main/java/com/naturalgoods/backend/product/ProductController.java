package com.naturalgoods.backend.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
