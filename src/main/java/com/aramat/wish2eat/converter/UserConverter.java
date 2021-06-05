package com.aramat.wish2eat.converter;

import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.dto.UserInsertDTO;
import com.aramat.wish2eat.entities.Product;
import com.aramat.wish2eat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private StoreConverter storeConverter;

    public User fromDtoToEntity(UserInsertDTO dto){
        User user = new User();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserDTO fromEntityToDTO(User entity){
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());

        if(entity.getFavoriteList() != null)
            dto.setAddedProducts(entity.getFavoriteList().stream().map(favorite -> productConverter.fromEntityToDto(favorite.getProduct())).collect(Collectors.toSet()));

        if(entity.getFavStoreList() != null)
            dto.setAddStores(entity.getFavStoreList().stream().map(favStore -> storeConverter.fromEntityToDTO(favStore.getStore())).collect(Collectors.toSet()));

        return dto;
    }

    private Set<Product> fromAddProdDtoToEntity(UserDTO dto) {
        return dto.getAddedProducts().stream().map(x -> productConverter.fromDtoToEntity(x)).collect(Collectors.toSet());
    }

    public List<UserDTO> fromEntityListToDTOList(List<User> list){
        return list.stream().map(this::fromEntityToDTO).collect(Collectors.toList());
    }

}
