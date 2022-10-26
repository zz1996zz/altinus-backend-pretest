package altinus.pretest.biz.repository;

import altinus.pretest.biz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
