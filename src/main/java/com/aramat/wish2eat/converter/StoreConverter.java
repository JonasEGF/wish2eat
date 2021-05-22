package com.aramat.wish2eat.converter;

import com.aramat.wish2eat.dto.StoreDTO;
import com.aramat.wish2eat.entities.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreConverter {

    @Autowired
    private ProductConverter productConverter;

    public Store fromDtoToEntity(StoreDTO dto){
        Store store = new Store();
        store.setName(dto.getName());
        store.setFacebook(dto.getFacebook());
        store.setNumber(dto.getNumber());
        store.setCep(dto.getCep());
        store.setInstagram(dto.getInstagram());
        store.setProducts(dto.getProducts().stream().map(x->productConverter.fromDtoToEntity(x)).collect(Collectors.toSet()));
        return store;
    }

    public StoreDTO fromEntityToDTO(Store entity){
        StoreDTO dto = new StoreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFacebook(entity.getFacebook());
        dto.setNumber(entity.getNumber());
        dto.setCep(entity.getCep());
        dto.setInstagram(entity.getInstagram());
        dto.setProducts(entity.getProducts().stream().map(x->productConverter.fromEntityToDto(x)).collect(Collectors.toSet()));
        return dto;
    }

    public Store fromDtoToEntity(Long id) {
        Store entity = new Store();
        entity.setId(id);
        return entity;
    }

    public StoreDTO fromEntityToDTO(Long id) {
        StoreDTO dto = new StoreDTO();
        dto.setId(id);
        return dto;
    }

    public List<StoreDTO> fromEntityListToDtoList(List<Store> list){
        return list.stream().map(this::fromEntityToDTO).collect(Collectors.toList());
    }
}
