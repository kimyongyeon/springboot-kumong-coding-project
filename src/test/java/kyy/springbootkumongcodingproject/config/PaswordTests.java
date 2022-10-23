package kyy.springbootkumongcodingproject.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PaswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String password = "1234";

        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw : " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println("matchResult : " + matchResult);
    }
}