package altinus.pretest.biz.repository;

import altinus.pretest.biz.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
