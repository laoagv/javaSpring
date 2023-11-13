package org.example.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidation implements ConstraintValidator<FileConstraint, MultipartFile> {
    @Override
    public void initialize(FileConstraint file){}

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
            if (multipartFile.getName().isEmpty() || multipartFile.getSize()==0){
                return false;
            }
            return true;

    }
}
