package com.aramat.wish2eat.resources;

import com.aramat.wish2eat.dto.FavStoreDTO;
import com.aramat.wish2eat.service.FavStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/favStore")
public class FavStoreResource {

    @Autowired
    private FavStoreService favStoreService;

    @PostMapping
    public ResponseEntity<String> addFavorite(@RequestBody @Valid FavStoreDTO favStoreDTO) {
        favStoreService.createFavorite(favStoreDTO);

        return ResponseEntity.ok("Favorite Created");
    }
}
