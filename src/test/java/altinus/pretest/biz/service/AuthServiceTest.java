package altinus.pretest.biz.service;

import altinus.pretest.TestRedisConfig;
import altinus.pretest.biz.domain.Member;
import altinus.pretest.biz.repository.MemberRepository;
import altinus.pretest.web.dto.MemberDto;
import altinus.pretest.web.dto.TokenDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = TestRedisConfig.class)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void login() {
        //given
        MemberDto.MemberRequest request = new MemberDto.MemberRequest("user@naver.com", "user1234");
        Member member = request.toEntity(encoder, encryptService.encryptEmail(request.getEmail()));
        memberRepository.save(member);

        //when
        TokenDto.TokenResponse tokenResponse = authService.login(request);
        String values = redisService.getValues(encryptService.encryptEmail(request.getEmail()));

        //then
        assertThat(tokenResponse.getAccessToken()).isNotEmpty();
        assertThat(tokenResponse.getRefreshToken()).isNotEmpty();
        assertThat(tokenResponse.getAccessToken()).isNotEmpty();
        assertThat(tokenResponse.getGrantType()).isNotEmpty();

        assertThat(tokenResponse.getRefreshToken()).isEqualTo(values);
    }

    @Test
    void logout() {
        //given
        MemberDto.MemberRequest request = new MemberDto.MemberRequest("user@naver.com", "user1234");
        Member member = request.toEntity(encoder, encryptService.encryptEmail(request.getEmail()));
        memberRepository.save(member);

        TokenDto.TokenResponse tokenResponse = authService.login(request);
        TokenDto.TokenRequest tokenRequest = TokenDto.TokenRequest.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
        //when
        authService.logout(tokenRequest);

        //then
        assertThat(redisService.getValues(member.getEmail())).isNull();
    }
}