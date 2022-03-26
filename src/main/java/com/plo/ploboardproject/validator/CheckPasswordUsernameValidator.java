package com.plo.ploboardproject.validator;

import com.plo.ploboardproject.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckPasswordUsernameValidator extends AbstractValidator<SignUpRequestDto> {

    @Override
    protected void doValidate(SignUpRequestDto dto, Errors errors) {
        if (dto.getUsername().equals(dto.getPassword())) {
            errors.rejectValue("password", "password 닉네임 일치오류", "닉네임을 패스워드에 포함할 수 없습니다.");
        }
    }
}
