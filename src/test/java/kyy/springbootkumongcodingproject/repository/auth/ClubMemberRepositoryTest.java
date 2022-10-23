package kyy.springbootkumongcodingproject.repository.auth;

import kyy.springbootkumongcodingproject.entity.auth.ClubMember;
import kyy.springbootkumongcodingproject.entity.auth.ClubMemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ClubMemberRepositoryTest {
    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        // 1 - 80 까지는 User만 지정
        // 81 - 90 까지는 USER, Manager
        // 91 - 100 까지는 User, Manager, Admin

        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@zerock.org")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            // default role
            clubMember.addMemberRole(ClubMemberRole.USER);

            if (i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }

            if (i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }

            repository.save(clubMember);
        });
    }

    @Test
    public void read() {
        Optional<ClubMember> result = repository.findByEmail("user95@zerock.org", false);

        ClubMember clubMember = result.get();

        System.out.println("=====================================");
        System.out.println(">>>>>>>>>>>>>>>>>>>> " + clubMember);
        System.out.println("=====================================");
    }

}