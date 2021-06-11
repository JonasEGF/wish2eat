package com.aramat.wish2eat.repositories;

import com.aramat.wish2eat.entities.FavStore;
import com.aramat.wish2eat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FavStoreRepository extends JpaRepository<FavStore, Long> {
    Set<FavStore> findByUser(User user);
}
