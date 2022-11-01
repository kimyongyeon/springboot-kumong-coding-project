package kyy.springbootkumongcodingproject.repository.movie;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kyy.springbootkumongcodingproject.entity.auth.Member;
import kyy.springbootkumongcodingproject.entity.auth.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static kyy.springbootkumongcodingproject.entity.mapping.QReview.review;
import static kyy.springbootkumongcodingproject.entity.movie.QMovie.movie;
import static kyy.springbootkumongcodingproject.entity.movie.QMovieImage.movieImage;

public class MovieRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory query;
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     * @param query
     */
    public MovieRepositorySupport(Class<?> domainClass, JPAQueryFactory query) {
        super(domainClass);
        this.query = query;
    }


    public List<Member> findByName(String nickName) {
        return query.selectFrom(QMember.member)
                .where(QMember.member.nickName.eq(nickName))
                .fetch();
    }


    /**
     * @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) " +
     * "from Movie m left outer join MovieImage mi on mi.movie = m " +
     * "left outer join Review r on r.movie = m " +
     * " where m.mno = :mno group by mi "
     * )
     * List<Object[]> getMovieWithAll(@Param("mno") Long mno);
     */

    public List<?> getMovieWithAll(@Param("mno") Long mno) {
        return query.selectFrom(movie)
                .leftJoin(movieImage.movie, movie)
                .leftJoin(review.movie, movie)
                .where(movie.mno.eq(mno))
                .fetch();
    }

}
