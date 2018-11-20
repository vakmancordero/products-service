package  com.tecgurus.products_service.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Data @AllArgsConstructor
    class Product {
        private String name;
        private double price;
        private String category;
    }

    private Map<String, List<Product>> products = populateProducts(9);

    @GetMapping("/byCategory/{category}")
    public ResponseEntity getInventoryProducts(@PathVariable String category) {

        try {

            this.validateCategory(category);

            return ResponseEntity.ok(products.get(category));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/allCategories")
    public Set<String> getAllCategories() {
        return products.keySet();
    }

    private void validateCategory(String category) throws Exception {

        this.getAllCategories().stream()
                .filter(cat -> cat.equals(category))
                .findFirst().orElseThrow(() -> new Exception("Invalid category"));
    }

    private Map<String, List<Product>> populateProducts(int productsByCategory) {

        List<Product> products = new ArrayList<>();

        Random r = new Random();

        r.ints(10, 1, (10 + 1)).forEach((category) -> {
            
            for (int i = 1; i <= productsByCategory; i++) {
                products.add(
                    new Product(
                        String.valueOf(category)
                            .concat("Product"),
                        category * 100,
                        "Category "
                            .concat(String.
                                valueOf(category))
                    )
                );
            }

        });

        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

    }


}