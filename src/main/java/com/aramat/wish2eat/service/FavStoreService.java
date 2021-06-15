package com.aramat.wish2eat.service;

import com.aramat.wish2eat.dto.FavStoreDTO;
import com.aramat.wish2eat.entities.FavStore;
import com.aramat.wish2eat.repositories.*;
import com.aramat.wish2eat.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavStoreService {
    @Autowired
    private FavStoreRepository favStoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public void createFavorite(FavStoreDTO favStoreDTO){
        FavStore favStore = favStoreRepository.findByStoreIdAndUserId(favStoreDTO.getStore(),favStoreDTO.getUser());
        if (favStore!=null){
            favStoreRepository.delete(favStore);
        }
        else {
            FavStore entity = new FavStore();

            copyToEntity(entity, favStoreDTO);

            favStoreRepository.save(entity);
        }
    }

    private void copyToEntity(FavStore entity, FavStoreDTO favoriteDTO){
        entity.setUser(userRepository.findById(favoriteDTO.getUser()).orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
        entity.setStore(storeRepository.findById(favoriteDTO.getStore()).orElseThrow(()-> new ResourceNotFoundException("Entity not found")));
    }
}
