package com.aramat.wish2eat.service;

import com.aramat.wish2eat.converter.UserConverter;
import com.aramat.wish2eat.dto.LoginDTO;
import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.dto.UserInsertDTO;
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
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserConverter userConverter;

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
        Optional<User> obj = repository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        User entity = obj.orElseThrow(()->new ResourceNotFoundException("Email and/or password are invalid"));
        return userConverter.fromEntityToDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = repository.save(userConverter.fromDtoToEntity(dto));
        return userConverter.fromEntityToDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, @Valid UserInsertDTO dto) {
        try {
            User entity = repository.getById(id);
            entity.setPassword(dto.getPassword());
            entity.setEmail(dto.getEmail());
            entity.setNome(dto.getNome());
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
}
