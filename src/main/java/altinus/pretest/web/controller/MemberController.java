package altinus.pretest.web.controller;

import altinus.pretest.biz.service.MemberService;
import altinus.pretest.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody MemberDto.SignupRequest request) {
        memberService.signup(request);
        return ResponseEntity.ok("회원가입이 완료됐습니다.");
    }
}
