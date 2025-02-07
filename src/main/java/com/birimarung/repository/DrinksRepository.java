package com.birimarung.repository;

import com.birimarung.data.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinksRepository extends JpaRepository<Drinks, Integer> {
    List<Drinks> findAllByDrinkPubliclyAvailable(boolean isDrinkPubliclyAvailable);
}
