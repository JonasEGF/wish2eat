package com.aramat.wish2eat.service;

import com.aramat.wish2eat.converter.StoreConverter;
import com.aramat.wish2eat.dto.StoreDTO;
import com.aramat.wish2eat.entities.Store;
import com.aramat.wish2eat.repositories.StoreRepository;
import com.aramat.wish2eat.service.exceptions.DatabaseException;
import com.aramat.wish2eat.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository repository;

    @Autowired
    private StoreConverter converter;

    @Transactional(readOnly = true)
    public List<StoreDTO> findAll() {
        return converter.fromEntityListToDtoList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public StoreDTO findById(Long id) {
        Optional<Store> obj = repository.findById(id);
        Store entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return converter.fromEntityToDTO(entity);
    }

    @Transactional
    public StoreDTO insert(StoreDTO dto) {
        Store store = converter.fromDtoToEntity(dto);
        store = repository.save(store);
        return converter.fromEntityToDTO(store);
    }

    @Transactional
    public StoreDTO update(Long id, StoreDTO dto) {
        try {
            Store entity = repository.getOne(id);
            entity = repository.save(copyDtoToEntity(dto, entity));
            return converter.fromEntityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private Store copyDtoToEntity(StoreDTO dto, Store store) {
        store.setName(dto.getName());
        store.setFacebook(dto.getFacebook());
        store.setNumber(dto.getNumber());
        store.setCep(dto.getCep());
        store.setInstagram(dto.getInstagram());
        return store;
    }
}
