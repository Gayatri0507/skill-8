package com.klu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.exception.ProductNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
  @Autowired
  ProductRepo repo;
  //displaybycategory
  @GetMapping("/category/{category}")
  public List<Product> displaybycategory(@PathVariable String category){
    return repo.findByCategory(category);
  }
  //displaybycategory and price
	  @GetMapping("category/{category}/price/{price}")
	  public List<Product> displaybycategoryprice(
	      @PathVariable String category,
	      @PathVariable double price){
	    
	    return repo.findByCategoryAndPriceGreaterThan(category, price);
	    
	    
	  }
	  //displaybymin/max
	  @GetMapping("/filter")
	  public List<Product> priceminmax(
			  @RequestParam long min,
			  @RequestParam long max){
		  return repo.findByPriceBetween(min, max);
	  }
	  //or
	  @GetMapping("/or")
	  public List<Product> byCategoryOrName(@RequestParam String category,
              @RequestParam String name) {
return repo.findByCategoryOrName(category, name);
}
	  //Between
	    public List<Product> between(@RequestParam double min,
                @RequestParam double max) {
return repo.findByPriceBetween(min, max);
}
	 // Like
	    @GetMapping("/search")
	    public List<Product> like(@RequestParam String keyword) {
	        return repo.findByNameLike("%" + keyword + "%");
	    }
	    // GreaterThan
	    @GetMapping("/expensive/{price}")
	    public List<Product> greaterThan(@PathVariable double price) {
	        return repo.findByPriceGreaterThan(price);
	    }
	 // countBy
	    @GetMapping("/count/{category}")
	    public long count(@PathVariable String category) {
	        return repo.countByCategory(category);
	    }
	    // existsBy
	    @GetMapping("/exists/{name}")
	    public boolean exists(@PathVariable String name) {
	        return repo.existsByName(name);
	    }
	    // deleteBy
	    @DeleteMapping("/delete/{name}")
	    public String delete(@PathVariable String name) {
	        repo.deleteByName(name);
	        return "Product deleted successfully";
	    }
	    // JPQL sorting
	    @GetMapping("/sorted")
	    public List<Product> sorted() {
	        return repo.sortByPrice();
	    }
	  //Add the following code to the existing program.

	    @GetMapping("/name/{name}")
	    public Product byName(@PathVariable String name) {
	        return repo.findByName(name)
	                .orElseThrow(() ->
	                        new ProductNotFoundException("Product with name '" + name + "' not found"));
	    }
  
}