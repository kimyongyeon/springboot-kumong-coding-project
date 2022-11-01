package kyy.springbootkumongcodingproject.repository.auth;

import kyy.springbootkumongcodingproject.entity.auth.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .pw("1111")
                    .nickName("reviewr " + i).build();
            memberRepository.save(member);
        });
    }


}