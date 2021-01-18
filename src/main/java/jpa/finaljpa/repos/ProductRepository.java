package jpa.finaljpa.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jpa.finaljpa.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    
}
