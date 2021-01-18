package jpa.finaljpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jpa.finaljpa.Dao.ProductDao;
import jpa.finaljpa.entities.Product;
import jpa.finaljpa.repos.ProductRepository;
import net.minidev.json.JSONObject;

@RestController
public class IndexController {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductDao productDao;

    @RequestMapping(method = RequestMethod.POST, path = "/addproduct/newProduct", consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Boolean> addProduct(@RequestBody Product product) {
        System.out.println(product.toString());
        if (productDao.save(product))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.ok(false);
    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getAllproducts() {
        JSONObject obj = new JSONObject();
        List<Product> li = productDao.getAll();
        if (li != null) {
            obj.appendField("done", true);
            obj.appendField("products", li);
        } else {
            obj.appendField("done", false);
            obj.appendField("products", li);
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(path = "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> updateProduct(@RequestBody Product product)
    {
        JSONObject obj = new JSONObject();
        Product productFinal = productDao.getOne(product.getId());
        if (productFinal != null) {
            boolean done = productDao.save(product);
            if (done)
                obj.appendField("message", "product updated");
            else
                obj.appendField("message", "error occured !");
        } else
            obj.appendField("message", "no such product exist");
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(path = "/getProduct/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getProduct(@PathVariable("productId") int id) {
        JSONObject obj = new JSONObject();
        Product pro = productDao.getOne(id);
        if (pro != null) {
            obj.appendField("done", true);
            obj.appendField("product", pro);
        } else {
            obj.appendField("done", false);
            obj.appendField("product", pro);
        }

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(path = "/deleteProduct/{id}")
    public ResponseEntity<JSONObject> deleteProduct(@PathVariable("id") int id) {
        JSONObject obj = new JSONObject();
        if (productDao.deleteproduct(id))
            obj.appendField("message", true);
        else
            obj.appendField("message", false);

        return ResponseEntity.ok().body(obj);
    }

}