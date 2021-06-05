package com.aramat.wish2eat.service;

import com.aramat.wish2eat.converter.UserConverter;
import com.aramat.wish2eat.dto.LoginDTO;
import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.dto.UserInsertDTO;
import com.aramat.wish2eat.entities.User;
import com.aramat.wish2eat.repositories.UserRepository;
import com.aramat.wish2eat.service.exceptions.DatabaseException;
import com.aramat.wish2eat.service.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userConverter.fromEntityListToDTOList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return userConverter.fromEntityToDTO(entity);
    }

    @Transactional(readOnly = true)
    public UserDTO findByEmailAndPassword(LoginDTO loginDTO){
        Optional<User> obj = repository.findByEmailAndPassword(loginDTO.getUsername(),loginDTO.getPassword());
        User entity = obj.orElseThrow(()->new ResourceNotFoundException("Email and/or password are invalid"));
        return userConverter.fromEntityToDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = repository.save(userConverter.fromDtoToEntity(dto));
        return userConverter.fromEntityToDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, @Valid UserDTO dto) {
        try {
            User entity = repository.getOne(id);
            entity = repository.save(entity);
            return userConverter.fromEntityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }
}
