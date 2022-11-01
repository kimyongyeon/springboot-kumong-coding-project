package kyy.springbootkumongcodingproject.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kyy.springbootkumongcodingproject.entity.auth.QCustom;
import kyy.springbootkumongcodingproject.entity.auth.QMember;
import kyy.springbootkumongcodingproject.entity.board.Board;
import kyy.springbootkumongcodingproject.entity.board.QBoard;
import kyy.springbootkumongcodingproject.entity.board.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static kyy.springbootkumongcodingproject.entity.board.QReply.*;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        QBoard board = QBoard.board;

        JPAQuery<Board> where = jpaQueryFactory.from(board)
                .leftJoin(reply).on(reply.board.eq(board))
                .select(board)
                .where(board.bno.eq(1L));
        log.info(where);
        List<Board> fetch = where.fetch();
        System.out.println(fetch);

        return null;
    }

    @Override
    public Board search2() {
        QBoard board = QBoard.board;
        QReply reply1 = reply;
        QCustom member = QCustom.custom;

        JPQLQuery<Tuple> tupleJPQLQuery = from(board)
                .leftJoin(member).on(board.writer.eq(member))
                .leftJoin(reply1).on(reply1.board.eq(board))
                .select(board, member.email, reply1.count()).groupBy(board);

        System.out.println(tupleJPQLQuery);
        List<Tuple> fetch = tupleJPQLQuery.fetch();
        System.out.println(fetch);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QCustom member = QCustom.custom;



        JPQLQuery<Tuple> tuple = from(board)
                .leftJoin(member).on(board.writer.eq(member))
                .leftJoin(reply).on(reply.board.eq(board))
                .select(board, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression booleanExpression = board.bno.gt(0L);

        booleanBuilder.and(booleanExpression);

        if (type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);
        tuple.groupBy(board);


        Sort sort = pageable.getSort();
//        tuple.orderBy(board.bno.desc()); // 직접 코드로 처리 하면.
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });


        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());


        List<Tuple> fetch = tuple.fetch();
//        tuple.fetchCount();
        System.out.println(fetch);

        return null;
    }


}
