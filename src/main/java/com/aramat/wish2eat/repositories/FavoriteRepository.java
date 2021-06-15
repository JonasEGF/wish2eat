package com.aramat.wish2eat.repositories;

import com.aramat.wish2eat.entities.Favorite;
import com.aramat.wish2eat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Set<Favorite> findByUser(User user);

    Favorite findByUserIdAndProductId(Long user, Long product);
}
