package kyy.springbootkumongcodingproject.repository.movie;

import kyy.springbootkumongcodingproject.entity.movie.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
