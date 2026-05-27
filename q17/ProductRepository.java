package org.example.q17;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}
