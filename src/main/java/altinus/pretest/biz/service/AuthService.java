package altinus.pretest.biz.service;

import altinus.pretest.biz.domain.Member;
import altinus.pretest.biz.exception.MemberErrorResult;
import altinus.pretest.biz.exception.MemberException;
import altinus.pretest.biz.repository.MemberRepository;
import altinus.pretest.config.SecurityUtil;
import altinus.pretest.config.jwt.TokenProvider;
import altinus.pretest.web.dto.MemberDto;
import altinus.pretest.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final EncryptService encryptService;
    private final TokenProvider tokenProvider;
    private final RedisService redisService;

    @Transactional
    public TokenDto.TokenResponse login(MemberDto.MemberRequest request) {
        Member member = memberRepository.findByEmail(encryptService.encryptEmail(request.getEmail())).orElseThrow(
                () -> new MemberException(MemberErrorResult.MEMBER_NOT_EXIST));

        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication(member.getId());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto.TokenResponse tokenResponse = tokenProvider.createTokenDto(authenticate);

        redisService.setValues(encryptService.encryptEmail(request.getEmail()),
                tokenResponse.getRefreshToken(),
                Duration.ofMillis(1000 * 60 * 60 * 24 * 7));

        return tokenResponse;
    }

    public void logout(TokenDto.TokenRequest request) {
        if(!tokenProvider.validateToken(request.getAccessToken())){
            throw new RuntimeException("Access Token 유효하지 않음");
        }

        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());
        Member member = memberRepository.findById(Long.parseLong(authentication.getName())).orElseThrow(
                () -> new MemberException(MemberErrorResult.MEMBER_EXIST));

        redisService.deleteValues(member.getEmail());
    }
}
