package jpa.finaljpa.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jpa.finaljpa.entities.Product;
import jpa.finaljpa.repos.ProductRepository;

@Component
public class ProductDao {
    
    @Autowired
    ProductRepository repo;

    public boolean save(Product product)
    {
        try {
            repo.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> getAll()
    {   
        try {
            return (List<Product>)repo.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Product getOne(int id)
    {   
        try {
            return repo.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteproduct(int id)
    {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;    
        }
    }
}
