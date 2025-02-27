package com.birimarung.repository;

import com.birimarung.data.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkTypeInterface extends JpaRepository<DrinkType, Integer> {
}
