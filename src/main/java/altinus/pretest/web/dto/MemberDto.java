package altinus.pretest.web.dto;

import altinus.pretest.biz.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberRequest {
        private String email;
        private String pw;

        public Member toEntity(PasswordEncoder encoder, String email) {
            return Member.builder()
                    .email(email)
                    .pw(encoder.encode(pw))
                    .build();
        }

        public UsernamePasswordAuthenticationToken toAuthentication(Long id) {
            return new UsernamePasswordAuthenticationToken(String.valueOf(id), pw);
        }
    }
}
