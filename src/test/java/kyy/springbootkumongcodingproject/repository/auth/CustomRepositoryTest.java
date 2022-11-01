package kyy.springbootkumongcodingproject.repository.auth;

import kyy.springbootkumongcodingproject.entity.auth.ClubMember;
import kyy.springbootkumongcodingproject.entity.auth.ClubMemberRole;
import kyy.springbootkumongcodingproject.entity.auth.Custom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class CustomRepositoryTest {
    @Autowired
    private CustomRepository repository;


    @Test
    public void insertDummies() {
        // 1 - 80 까지는 User만 지정
        // 81 - 90 까지는 USER, Manager
        // 91 - 100 까지는 User, Manager, Admin

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Custom clubMember = Custom.builder()
                    .email("admin" + i + "@aaa.com")
                    .name("사용자" + i)
                    .build();

            repository.save(clubMember);
        });
    }


}