package com.birimarung.repository;

import com.birimarung.data.Waitlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaitListRepository extends JpaRepository<Waitlist, Integer> {
    Optional<Waitlist> findByEmail(String email);
}
