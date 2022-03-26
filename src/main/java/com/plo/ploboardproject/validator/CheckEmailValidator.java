package com.plo.ploboardproject.validator;

import com.plo.ploboardproject.dto.SignUpRequestDto;
import com.plo.ploboardproject.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<SignUpRequestDto> {
    private final UserRepository userRepository;

    @Override
    protected void doValidate(SignUpRequestDto dto, Errors errors) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
