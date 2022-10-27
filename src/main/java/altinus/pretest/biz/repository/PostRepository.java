package altinus.pretest.biz.repository;

import altinus.pretest.biz.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
