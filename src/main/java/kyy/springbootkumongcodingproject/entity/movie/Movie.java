package kyy.springbootkumongcodingproject.entity.movie;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MNO")
    private Long mno;

    private String title;


}
