package com.plo.ploboardproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@Validated
public class SignUpRequestDto {

    @NotBlank(message = "유저네임을 입력해주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{2,9}", message = "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 가능합니다.")
    private String username;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}", message = "비밀번호는 영문과 숫자 조합으로 4 ~ 16자리까지 가능합니다.")
    private String password;

    @NotBlank(message = "패스워드를 확인해주세요" )
    private String passwordCheck;

    @NotBlank(message = "이메일을 입력해주세요" )
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private boolean admin = false;
    private String adminToken = "";
}
