package com.aramat.wish2eat.service;

import com.aramat.wish2eat.dto.FavoriteDTO;
import com.aramat.wish2eat.entities.Favorite;
import com.aramat.wish2eat.repositories.FavoriteRepository;
import com.aramat.wish2eat.repositories.ProductRepository;
import com.aramat.wish2eat.repositories.UserRepository;
import com.aramat.wish2eat.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createFavorite(FavoriteDTO favoriteDTO) {
        Favorite favorite = favoriteRepository.findByUserIdAndProductId(favoriteDTO.getUser(), favoriteDTO.getProduct());
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        } else {
            Favorite entity = new Favorite();

            copyToEntity(entity, favoriteDTO);

            favoriteRepository.save(entity);
        }
    }

    private void copyToEntity(Favorite entity, FavoriteDTO favoriteDTO) {
        entity.setUser(userRepository.findById(favoriteDTO.getUser()).orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
        entity.setProduct(productRepository.findById(favoriteDTO.getProduct()).orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
    }
}
