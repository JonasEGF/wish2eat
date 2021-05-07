package com.aramat.wish2eat.service;

import com.aramat.wish2eat.converter.ProductConverter;
import com.aramat.wish2eat.dto.ProductDTO;
import com.aramat.wish2eat.dto.StoreDTO;
import com.aramat.wish2eat.entities.Product;

import com.aramat.wish2eat.entities.Store;
import com.aramat.wish2eat.repositories.ProductRepository;
import com.aramat.wish2eat.service.exceptions.DatabaseException;
import com.aramat.wish2eat.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductConverter converter;

    public Set<ProductDTO> findAll() {
        List<Product> list = repository.findAll();
        return converter.fromEntityListToDtoList(list);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = converter.fromDtoToEntity(dto);
        entity = repository.save(entity);
        return converter.fromEntityToDto(entity);
    }

    @org.springframework.transaction.annotation.Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getOne(id);
            entity = repository.save(copyDtoToEntity(dto, entity));
            return converter.fromEntityToDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    public Product copyDtoToEntity(ProductDTO dto, Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setValue(dto.getValue());
        entity.setName(dto.getName());
        return entity;
    }
}
