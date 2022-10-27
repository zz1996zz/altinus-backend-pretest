package altinus.pretest.config.auth;

import altinus.pretest.biz.domain.Member;
import altinus.pretest.biz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findById(Long.parseLong(username))
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException(username + " 을 DB에서 찾지 못함"));
    }

    private UserDetails createUserDetails(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

        return new User(
                String.valueOf(member.getId()),
                member.getPw(),
                Collections.singleton(grantedAuthority));
    }
}
