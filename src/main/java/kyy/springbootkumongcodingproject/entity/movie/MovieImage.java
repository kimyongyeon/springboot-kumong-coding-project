package kyy.springbootkumongcodingproject.entity.movie;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    // MovieImage 테이블은 FK를 가지므로 
    @ManyToOne(fetch = FetchType.LAZY) // 단방향
    @JoinColumn(name = "MNO")
    private Movie movie;
}
