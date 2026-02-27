package com.klu;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
  
  List<Product> findByCategory(String category);
  List<Product> findByCategoryAndPriceGreaterThan(String category,double price);
  List<Product> findByPriceBetween(long min,long max);
  //or
  List<Product> findByCategoryOrName(String category, String name);
  //Between
  List<Product> findByPriceBetween(double min, double max);
  // ---------------- Like ----------------
  List<Product> findByNameLike(String pattern);

  //GreaterThan
  List<Product> findByPriceGreaterThan(double price);
  //countBy
  long countByCategory(String category);
  //existsBy
  boolean existsByName(String name);

  //deleteBy
  @Transactional
  void deleteByName(String name);

  // JPQL-
  @Query("SELECT p FROM Product p ORDER BY p.price ASC")
  List<Product> sortByPrice();
  //findByName
  Optional<Product> findByName(String name);

}