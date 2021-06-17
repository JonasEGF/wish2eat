package com.aramat.wish2eat.converter;

import com.aramat.wish2eat.dto.ProductDTO;
import com.aramat.wish2eat.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    @Autowired
    private StoreConverter storeConverter;

    public Product fromDtoToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setValue(dto.getValue());
        product.setStore(storeConverter.fromDtoToEntity(dto.getStoreID()));
        return product;
    }

    public ProductDTO fromEntityToDto(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        dto.setStoreID(entity.getStore().getId());
        return dto;
    }

    public Product fromDtoToEntity(Long id) {
        Product entity = new Product();
        entity.setId(id);
        return entity;
    }

    public ProductDTO fromEntityToDto(Long id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        return dto;
    }

    public Set<ProductDTO> fromEntityListToDtoList(List<Product> list) {
        return list.stream().map(this::fromEntityToDto).collect(Collectors.toSet());
    }

    public List<Product> fromDtoListToEntityList(List<ProductDTO> list) {
        return list.stream().map(this::fromDtoToEntity).collect(Collectors.toList());
    }

}
