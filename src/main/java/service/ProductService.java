package service;

import moduls.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IProductRepo;

import java.util.Optional;

public class ProductService implements IProductService{

    @Autowired
    IProductRepo iProductRepo;
    @Override
    public Iterable<Product> findAll() {
        return iProductRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return iProductRepo.save(product);
    }
}
