package kyy.springbootkumongcodingproject.service.auth;

import kyy.springbootkumongcodingproject.dto.auth.ClubAuthMemberDTO;
import kyy.springbootkumongcodingproject.entity.auth.ClubMember;
import kyy.springbootkumongcodingproject.repository.auth.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {
    private final ClubMemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailsService loadUserByUserName " + username);
        Optional<ClubMember> result = repository.findByEmail(username, false);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("check Email or Social");
        }

        ClubMember clubMember = result.get();

        log.info("===================================");
        log.info(clubMember);

        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );

        clubAuthMemberDTO.setName(clubMember.getName());
        clubAuthMemberDTO.setFromSocial(clubMember.isFromSocial());

        return clubAuthMemberDTO;
    }
}
