package com.birimarung.repository;

import com.birimarung.data.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByIsActiveTrue();
}
