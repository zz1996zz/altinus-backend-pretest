package altinus.pretest.biz.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MemberErrorResult {

    MEMBER_EXIST(HttpStatus.BAD_GATEWAY, "회원이 이미 존재합니다."),
    MEMBER_NOT_EXIST(HttpStatus.BAD_GATEWAY, "회원이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
