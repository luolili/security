package com.luo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    public void initialize(MyConstraint constraint) {
        System.out.println("iint");
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        System.out.println(obj);


        return false;
    }
}
