package altinus.pretest.web.controller;

import altinus.pretest.biz.service.AuthService;
import altinus.pretest.web.dto.MemberDto;
import altinus.pretest.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto.TokenResponse> login(@RequestBody MemberDto.MemberRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public String logout(@RequestBody TokenDto.TokenRequest request){
        authService.logout(request);
        return "로그아웃되셨습니다.";
    }
}
