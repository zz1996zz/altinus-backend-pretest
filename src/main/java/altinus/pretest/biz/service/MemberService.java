package altinus.pretest.biz.service;

import altinus.pretest.biz.exception.MemberErrorResult;
import altinus.pretest.biz.exception.MemberException;
import altinus.pretest.biz.repository.MemberRepository;
import altinus.pretest.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EncryptService encryptService;
    private final PasswordEncoder encoder;

    @Transactional
    public void signup(MemberDto.MemberRequest request){
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorResult.MEMBER_EXIST);
        }
        memberRepository.save(request.toEntity(encoder, encryptService.encryptEmail(request.getEmail())));
    }
}
