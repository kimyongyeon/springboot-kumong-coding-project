package kyy.springbootkumongcodingproject.repository.movie;

import kyy.springbootkumongcodingproject.entity.auth.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQuery {
    private final EntityManager em;

    List<Member> findAllList() {
        return em.createQuery("select m from Member m")
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();
    }

//    Page<Member> findPagingMemberList() {
//        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("name"));
//        em.createQuery("select m from Member m")
//                .setFirstResult(0)
//                .setMaxResults(10)
//                .getResultList();
//    }
}
