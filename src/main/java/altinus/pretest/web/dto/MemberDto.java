package altinus.pretest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequest {
        private String email;
        private String pw;
    }
}
