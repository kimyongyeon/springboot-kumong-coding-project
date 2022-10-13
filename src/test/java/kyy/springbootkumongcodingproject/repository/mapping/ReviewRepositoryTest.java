package kyy.springbootkumongcodingproject.repository.mapping;

import kyy.springbootkumongcodingproject.entity.auth.Member;
import kyy.springbootkumongcodingproject.entity.mapping.Review;
import kyy.springbootkumongcodingproject.entity.movie.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository repository;

    @Test
    public void insertMovieReviews() {
        // 200개의 리뷰를 등록
        IntStream.rangeClosed(1, 100).forEach(i -> {
            // 영화번호
            Long mno = (long)(Math.random() * 100) + 1;

            // 리뷰어 번호
            Long mid = ((long)(Math.random()* 100) + 1);

            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()* 5) + 1)
                    .text("이 영화에 대한 느낌... " + i)
                    .build();
            repository.save(review);
        });
    }

}