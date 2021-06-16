package com.aramat.wish2eat.service.validation;

import com.aramat.wish2eat.dto.StoreInsertDTO;
import com.aramat.wish2eat.entities.Store;
import com.aramat.wish2eat.entities.User;
import com.aramat.wish2eat.repositories.StoreRepository;
import com.aramat.wish2eat.repositories.UserRepository;
import com.aramat.wish2eat.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class StoreInsertValidator implements ConstraintValidator<StoreInsertValid, StoreInsertDTO> {

    @Autowired
    private StoreRepository repository;

    @Override
    public void initialize(StoreInsertValid ann) {
    }

    @Override
    public boolean isValid(StoreInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Store store = repository.findByEmail(dto.getEmail());

        if (store != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
