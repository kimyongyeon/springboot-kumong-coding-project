package kyy.springbootkumongcodingproject.entity.mapping;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import kyy.springbootkumongcodingproject.entity.auth.Member;
import kyy.springbootkumongcodingproject.entity.movie.Movie;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
public class Review  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MNO")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MID")
    private Member member;

    private int grade;

    private String text;

}
