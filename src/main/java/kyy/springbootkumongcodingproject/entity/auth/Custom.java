package kyy.springbootkumongcodingproject.entity.auth;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Custom extends BaseEntity {
    @Id
    private String email;

    private String password;

    private String name;
}
