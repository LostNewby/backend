package com.naturalgoods.backend.util;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationUtils {

    public static <T> List<String> validate(T data, Class<?>... var2) {
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate(data, var2);
        for (ConstraintViolation<T> registrationRequestConstraintViolation : validate) {
            errors.add(registrationRequestConstraintViolation.getMessage());
        }
        return errors;
    }
}
