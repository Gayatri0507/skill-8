package com.klu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.exception.ProductNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo repo;

    // Add Product
    @PostMapping("/add")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return repo.saveAll(products);
    }

    // Display by category
    @GetMapping("/category/{category}")
    public List<Product> displayByCategory(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    // Display by category and price greater than
    @GetMapping("/category/{category}/price/{price}")
    public List<Product> displayByCategoryPrice(
            @PathVariable String category,
            @PathVariable double price) {

        return repo.findByCategoryAndPriceGreaterThan(category, price);
    }

    // Price between min and max
    @GetMapping("/filter")
    public List<Product> priceMinMax(
            @RequestParam double min,
            @RequestParam double max) {

        return repo.findByPriceBetween(min, max);
    }

    // Category OR Name
    @GetMapping("/or")
    public List<Product> byCategoryOrName(
            @RequestParam String category,
            @RequestParam String name) {

        return repo.findByCategoryOrName(category, name);
    }

    // Between
    @GetMapping("/between")
    public List<Product> between(
            @RequestParam double min,
            @RequestParam double max) {

        return repo.findByPriceBetween(min, max);
    }

    // Like search
    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword) {
        return repo.findByNameLike("%" + keyword + "%");
    }

    // Greater than price
    @GetMapping("/expensive/{price}")
    public List<Product> expensive(@PathVariable double price) {
        return repo.findByPriceGreaterThan(price);
    }

    // Count by category
    @GetMapping("/count/{category}")
    public long count(@PathVariable String category) {
        return repo.countByCategory(category);
    }

    // Exists by name
    @GetMapping("/exists/{name}")
    public boolean exists(@PathVariable String name) {
        return repo.existsByName(name);
    }

    // Delete by name
    @DeleteMapping("/delete/{name}")
    public String delete(@PathVariable String name) {
        repo.deleteByName(name);
        return "Product deleted successfully";
    }

    // JPQL Sorting
    @GetMapping("/sorted")
    public List<Product> sorted() {
        return repo.sortByPrice();
    }

    // Find by name with exception
    @GetMapping("/name/{name}")
    public Product byName(@PathVariable String name) {
        return repo.findByName(name)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with name '" + name + "' not found"));
    }
}