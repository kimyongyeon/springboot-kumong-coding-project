package kyy.springbootkumongcodingproject.entity.auth;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "m_member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MID")
    private Long mid;

    private String email;

    private String pw;

    private String nickName;

}
