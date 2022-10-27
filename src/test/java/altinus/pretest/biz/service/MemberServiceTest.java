package altinus.pretest.biz.service;

import altinus.pretest.TestConfig;
import altinus.pretest.biz.domain.Member;
import altinus.pretest.biz.repository.MemberRepository;
import altinus.pretest.web.dto.MemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(TestConfig.class)
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Value("${aes.secret}")
    String secret;

    public MemberService memberService;

    public EncryptService encryptService;

    @Mock
    public MemberRepository memberRepository;

    @Autowired
    public AesBytesEncryptor aesBytesEncryptor;

    @Autowired
    public PasswordEncoder encoder;

    @BeforeEach
    public void beforeEach() {
        encryptService = new EncryptService(aesBytesEncryptor);
        memberService = new MemberService(memberRepository, encryptService, encoder);
    }

    @Test
    void 회원가입() {
        //given
        MemberDto.SignupRequest request = new MemberDto.SignupRequest("user@naver.com", "user1234");
        Member member = request.toEntity(encoder, encryptService.encryptEmail(request.getEmail()));

        //mocking
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        memberService.signup(request);

        //then
        Member findMember = memberRepository.findById(1L).get();

        assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(member.getPw()).isEqualTo(findMember.getPw());
        assertThat(request.getEmail()).isEqualTo(encryptService.decryptEmail(findMember.getEmail()));
    }
}