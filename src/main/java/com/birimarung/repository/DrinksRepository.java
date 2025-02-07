package com.birimarung.repository;

import com.birimarung.data.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinksRepository extends JpaRepository<Drinks,Integer> {
}
