package kyy.springbootkumongcodingproject.repository.movie;

import kyy.springbootkumongcodingproject.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
