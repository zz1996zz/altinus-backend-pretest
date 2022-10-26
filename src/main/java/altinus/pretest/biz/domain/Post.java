package altinus.pretest.biz.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 10000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(Long id, String title, String description, Member member) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.member = member;
    }
}
