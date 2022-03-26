package com.plo.ploboardproject.validator;

import com.plo.ploboardproject.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckPasswordValidator extends AbstractValidator<SignUpRequestDto> {

    @Override
    protected void doValidate(SignUpRequestDto dto, Errors errors) {
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            errors.rejectValue("password", "password 확인 오류", "패스워드가 일치하지 않습니다");
        }

    }
}
