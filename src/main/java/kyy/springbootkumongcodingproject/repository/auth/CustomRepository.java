package kyy.springbootkumongcodingproject.repository.auth;

import kyy.springbootkumongcodingproject.entity.auth.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRepository extends JpaRepository<Custom, String> {
}
