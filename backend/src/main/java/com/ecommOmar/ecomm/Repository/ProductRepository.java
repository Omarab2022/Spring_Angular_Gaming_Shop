package com.ecommOmar.ecomm.Repository;


import com.ecommOmar.ecomm.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Long> {


	List<Product> findAllByNameContaining(String title);

}
