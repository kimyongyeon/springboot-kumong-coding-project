package kyy.springbootkumongcodingproject.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static kyy.springbootkumongcodingproject.entity.auth.QMember.member;

@Service
public class SearchBoardService {
    @Autowired
    JPAQueryFactory queryFactory;

    public List read() {
        List<?> fetch = queryFactory.from(member)
                .fetch();

        return fetch;
    }
}
