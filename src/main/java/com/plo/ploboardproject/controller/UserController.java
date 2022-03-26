package com.plo.ploboardproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.plo.ploboardproject.dto.SignUpRequestDto;
import com.plo.ploboardproject.service.KakaoUserService;
import com.plo.ploboardproject.validator.CheckEmailValidator;
import com.plo.ploboardproject.validator.CheckPasswordUsernameValidator;
import com.plo.ploboardproject.validator.CheckUsernameValidator;
import com.plo.ploboardproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final CheckUsernameValidator checkUsernameValidator;
    private final CheckEmailValidator checkEmailValidator;
    private final CheckPasswordUsernameValidator checkPasswordValidator;
    private final CheckPasswordUsernameValidator checkPasswordUsernameValidator;
    private final KakaoUserService kakaoUserService;

    //WebDataBinder binder : HTTP 요청 정보를 컨트롤러 메소드의 파라미터나 모델에 바인딩할 때 사용되는 바인딩 객체
    @InitBinder //@InitBinder : 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용한다
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkPasswordValidator);
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkPasswordUsernameValidator);
    }


    @Autowired  //생성자
    public UserController(UserService userService, CheckUsernameValidator checkUsernameValidator, CheckEmailValidator checkEmailValidator, CheckPasswordUsernameValidator checkPasswordValidator, CheckPasswordUsernameValidator checkPasswordUsernameValidator, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.checkUsernameValidator = checkUsernameValidator;
        this.checkEmailValidator = checkEmailValidator;
        this.checkPasswordValidator = checkPasswordValidator;
        this.checkPasswordUsernameValidator = checkPasswordUsernameValidator;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지 리턴
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지 리턴
    @GetMapping("/user/signup")
    public String signup() {
        return "sign_up";
    }

    //회원가입 요청 처리와 검토
    @Validated
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignUpRequestDto requestDto, Errors errors, Model model) {
        //회원가입중 error발생시
        if (errors.hasErrors()){
            model.addAttribute("dto", requestDto);
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return "/sign_up";
        }
        //회원가입 유효성검사 통과시
        System.out.println(requestDto);
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    //로그인 실패시
    @GetMapping ("/user/login/fail")
    public String loginFail(Model model){
        model.addAttribute("error" , "iD 또는 비밀번호가 유효하지 않습니다");
        return "login";
    }


    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/board/main";
    }

}
