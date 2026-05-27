package org.example.q17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;

    public Product getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
