package kyy.springbootkumongcodingproject.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User {


    private String email;
    private String name;
    private boolean fromSocial;

    public ClubAuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
