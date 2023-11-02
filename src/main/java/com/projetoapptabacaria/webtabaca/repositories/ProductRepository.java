package com.projetoapptabacaria.webtabaca.repositories;

import com.projetoapptabacaria.webtabaca.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
