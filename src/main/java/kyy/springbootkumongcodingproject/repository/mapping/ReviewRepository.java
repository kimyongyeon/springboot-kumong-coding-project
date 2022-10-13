package kyy.springbootkumongcodingproject.repository.mapping;

import kyy.springbootkumongcodingproject.entity.mapping.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
