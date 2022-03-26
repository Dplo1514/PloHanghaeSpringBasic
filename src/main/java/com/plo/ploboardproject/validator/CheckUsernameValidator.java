package com.plo.ploboardproject.validator;

import com.plo.ploboardproject.dto.SignUpRequestDto;
import com.plo.ploboardproject.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<SignUpRequestDto> {
    private final UserRepository userRepository;

    @Override
    protected void doValidate(SignUpRequestDto dto, Errors errors) {
        if (userRepository.existsByUsername(dto.getUsername())){
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }


}
