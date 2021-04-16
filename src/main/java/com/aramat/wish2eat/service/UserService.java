package com.aramat.wish2eat.service;

import com.aramat.wish2eat.converter.UserConverter;
import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.entities.Store;
import com.aramat.wish2eat.entities.User;
import com.aramat.wish2eat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public UserDTO insert(UserDTO dto) {
        User user = converter.fromDtoToEntity(dto);
        user = repository.save(user);
        return converter.fromEntityToDTO(user);
    }
}
