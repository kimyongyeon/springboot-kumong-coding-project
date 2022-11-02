package kyy.springbootkumongcodingproject.repository.board;

import kyy.springbootkumongcodingproject.entity.auth.Custom;
import kyy.springbootkumongcodingproject.entity.board.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    /**
     * select
     *         board0_.bno as bno1_0_0_,
     *         board0_.mod_date as mod_date2_0_0_,
     *         board0_.reg_date as reg_date3_0_0_,
     *         board0_.content as content4_0_0_,
     *         board0_.title as title5_0_0_,
     *         board0_.writer_email as writer_e6_0_0_,
     *         custom1_.email as email1_3_1_,
     *         custom1_.mod_date as mod_date2_3_1_,
     *         custom1_.reg_date as reg_date3_3_1_,
     *         custom1_.name as name4_3_1_,
     *         custom1_.password as password5_3_1_
     *     from
     *         board board0_
     *     left outer join
     *         custom custom1_
     *             on board0_.writer_email=custom1_.email
     *     where
     *         board0_.bno=?
     */
//    @Transactional
    @Test // 즉시로딩
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Custom member = Custom.builder().email("admin" + i +"@aaa.com").build();

            Board board = Board.builder()
                    .title("Title... " + i)
                    .content("Content... " + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        });
    }

    /**
     *   select
     *         board0_.bno as bno1_0_0_,
     *         custom1_.email as email1_3_1_,
     *         board0_.mod_date as mod_date2_0_0_,
     *         board0_.reg_date as reg_date3_0_0_,
     *         board0_.content as content4_0_0_,
     *         board0_.title as title5_0_0_,
     *         board0_.writer_email as writer_e6_0_0_,
     *         custom1_.mod_date as mod_date2_3_1_,
     *         custom1_.reg_date as reg_date3_3_1_,
     *         custom1_.name as name4_3_1_,
     *         custom1_.password as password5_3_1_
     *     from
     *         board board0_
     *     left outer join
     *         custom custom1_
     *             on board0_.writer_email=custom1_.email
     *     where
     *         board0_.bno=?
     */
    @Test
    public void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[])result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1() {
        boardRepository.search1();
    }

    @Test
    public void testSearch2() {
        boardRepository.search2();
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending()
                .and(Sort.by("title").ascending()));
        Page<Object[]> result = boardRepository.searchPage("tc", "1", pageable);
    }

}