package com.fehead.songs.validator;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object bean){
        final ValidationResult result=new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolations=validator.validate(bean);
        if(constraintViolations.size()>0){
            result.setHasErrors(true);
            constraintViolations.forEach(constraintViolation->{
                String errMsg=constraintViolation.getMessage();
                String propertyName=constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator= Validation.buildDefaultValidatorFactory().getValidator();
    }
}
