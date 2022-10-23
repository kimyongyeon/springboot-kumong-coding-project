package kyy.springbootkumongcodingproject.repository.auth;

import kyy.springbootkumongcodingproject.entity.auth.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityResult;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    // EntityGraph 로 left outer join
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.fromSocial = :social and m.email = :email")
    Optional<ClubMember> findByEmail(@Param("email") String email, @Param("social") boolean social);
}
