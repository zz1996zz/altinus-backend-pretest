package altinus.pretest.web.controller;

import altinus.pretest.web.dto.MemberDto;
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

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody MemberDto.SignupRequest request) {
        return ResponseEntity.ok();
    }
}
