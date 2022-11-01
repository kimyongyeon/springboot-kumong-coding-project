package kyy.springbootkumongcodingproject.repository.movie;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyy.springbootkumongcodingproject.entity.auth.Member;
import kyy.springbootkumongcodingproject.entity.auth.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class MemberQueryTest {

    @Autowired
    JPAQueryFactory query;

    @Test
    @Transactional
    public void testMemberQuery() {

        List<Member> hello = query.selectFrom(QMember.member)
                .where(QMember.member.nickName.eq("hello"))
                .fetch();

    }
}