package ru.example.cloudModuleTest.ProductProducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.cloudModuleTest.ProductProducer.Service.ProductService;
import ru.example.cloudModuleTest.ProductProducer.model.Product;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    /**
     *
     * @param name
     * @param q
     * @param price
     * @return
     */
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProduct(final @RequestParam String name, final @RequestParam Integer q, final @RequestParam Float price) {
        if (productService.addProductToKafka(new Product(name, q, price))) {
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
