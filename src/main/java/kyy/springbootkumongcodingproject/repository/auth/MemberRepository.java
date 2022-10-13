package kyy.springbootkumongcodingproject.repository.auth;

import kyy.springbootkumongcodingproject.entity.auth.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
