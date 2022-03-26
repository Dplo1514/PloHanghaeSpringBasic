package com.plo.ploboardproject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class KakakoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;
}
