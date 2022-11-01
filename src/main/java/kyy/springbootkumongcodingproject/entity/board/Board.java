package kyy.springbootkumongcodingproject.entity.board;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import kyy.springbootkumongcodingproject.entity.auth.ClubMember;
import kyy.springbootkumongcodingproject.entity.auth.Custom;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;

    // 데이터를 어떻게 가져올지 정하는 것이 fetch
    @ManyToOne(fetch = FetchType.LAZY)
    private Custom writer; // 연관관계 지정

}
