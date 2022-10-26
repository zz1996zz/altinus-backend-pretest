package altinus.pretest.web.dto;

import altinus.pretest.biz.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequest {
        private String email;
        private String pw;

        public Member toEntity(PasswordEncoder encoder, String email) {
            return Member.builder()
                    .email(email)
                    .pw(encoder.encode(pw))
                    .build();
        }
    }
}
