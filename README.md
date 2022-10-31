# altinus-backend-pretest

## 기능
### Entity
- Member
  - email과 pw로 구성
- Post
  - title과 description으로 구성
  - Member와 일대다로 구성
- Comment
  - content로 구성
  - Post와 일대다로 구성
  - 대댓글의 기능이 필요하므로 self join을 함
### 암호화
- 이메일 암호화
  - 이메일은 암호화를하고, 복호화도 해야하므로 양방향 암호화인 AES256을 사용
  - Spring Security의 AesBytesEncryptor을 사용함
- 비밀번호 암호화
  - 비밀번호는 복호화를 할 필요가 없으므로 단방향 암호화인 SHA를 사용
  - Spring Security의 BCryptPasswordEncoder를 사용함
### JWT
- 다중화 서버
  - 다중화된 서버에서 사용하기 위해서는 Spring Security의 Session을 사용하면 안됨.
  - JWT를 사용하여 액세스 토큰과 리프레쉬 토큰을 구현하여 여러 서버에서 사용자를 인증, 인가 할 수 있음.
  - 리프레시 토큰은 레디스를 사용하여 저장.
- 인가
  - Spring Security의 필터체인을 활용하여 인가되지 않은 사용자의 게시판 등록 및 조회를 막음(401에러)
## 앱 구성
### ERD
![altinus_pretest](https://user-images.githubusercontent.com/53508659/198937350-2978334d-83dc-4e2a-975f-b1331caeb0f5.png)

### API 명세서
 - [API 명세서](https://altinus-pretest.gitbook.io/altinus_pretest/reference/pretest)

## 트러블 슈팅
- 임베디드 레디스를 사용하여 테스트 코드를 작성하려고 할 때, 에러 발생

로그인 테스트 코드를 작성할 때, 임베디드 레디스를 사용하여 작성을 했는데 Slf4J Logger 중복으로 컴파일 에러가 발생하였다. 
`it.ozimov:embedded-redis`를 의존받아서 사용하였는데, 이 안에 Slf4J Logger가 있어 중복되었다.
그래서 gradle의 exclude를 사용하여 it.ozimov:embedded-redis안에 있는 slf4j를 예외시키고, 
`implementation 'org.projectlombok:lombok'`을 추가해주었다.
