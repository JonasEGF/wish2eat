package com.aramat.wish2eat.repositories;

import com.aramat.wish2eat.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByEmailAndPassword(String email, String password);

    Store findByEmail(String email);
}
