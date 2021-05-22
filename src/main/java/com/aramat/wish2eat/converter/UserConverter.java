package com.aramat.wish2eat.converter;

import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public User fromDtoToEntity(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setAddedProducts(dto.getAddedProducts());
        return user;
    }

    public UserDTO fromEntityToDTO(User entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setAddedProducts(entity.getAddedProducts());
        return dto;
    }

    public List<UserDTO> fromEntityListToDTOList(List<User> list){
        return list.stream().map(this::fromEntityToDTO).collect(Collectors.toList());
    }
}
