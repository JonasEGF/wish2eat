package com.aramat.wish2eat.service;

import com.aramat.wish2eat.converter.UserConverter;
import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.entities.User;
import com.aramat.wish2eat.repositories.UserRepository;
import com.aramat.wish2eat.service.exceptions.DatabaseException;
import com.aramat.wish2eat.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserConverter converter;

    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
        return converter.fromEntityListToDTOList(list);
    }

    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(()->new ResourceNotFoundException("Entity not Found"));
        return converter.fromEntityToDTO(entity);
    }

    public UserDTO insert(UserDTO dto) {
        User user = converter.fromDtoToEntity(dto);
        user = repository.save(user);
        return converter.fromEntityToDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = repository.getOne(id);
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

    private User copyDtoToEntity(UserDTO dto, User user){
        user.setNome(dto.getNome());
        return user;
    }
}
