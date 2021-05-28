package com.aramat.wish2eat.resources;

import com.aramat.wish2eat.dto.FavoriteDTO;
import com.aramat.wish2eat.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteResource {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteDTO favoriteDTO){
        favoriteService.createFavorite(favoriteDTO);

        return ResponseEntity.ok("Favorite Created");
    }
}
