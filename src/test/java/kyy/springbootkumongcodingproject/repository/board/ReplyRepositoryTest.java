package kyy.springbootkumongcodingproject.repository.board;

import kyy.springbootkumongcodingproject.entity.board.Board;
import kyy.springbootkumongcodingproject.entity.board.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply....." + i)
                    .board(board)
                    .replyer("guest" + i)
                    .build();

            replyRepository.save(reply);
        });
    }

    /**
     *   select
     *         reply0_.rno as rno1_14_0_,
     *         reply0_.mod_date as mod_date2_14_0_,
     *         reply0_.reg_date as reg_date3_14_0_,
     *         reply0_.board_bno as board_bn6_14_0_,
     *         reply0_.replyer as replyer4_14_0_,
     *         reply0_.text as text5_14_0_,
     *         board1_.bno as bno1_0_1_,
     *         board1_.mod_date as mod_date2_0_1_,
     *         board1_.reg_date as reg_date3_0_1_,
     *         board1_.content as content4_0_1_,
     *         board1_.title as title5_0_1_,
     *         board1_.writer_email as writer_e6_0_1_,
     *         custom2_.email as email1_3_2_,
     *         custom2_.mod_date as mod_date2_3_2_,
     *         custom2_.reg_date as reg_date3_3_2_,
     *         custom2_.name as name4_3_2_,
     *         custom2_.password as password5_3_2_
     *     from
     *         reply reply0_
     *     left outer join
     *         board board1_
     *             on reply0_.board_bno=board1_.bno
     *     left outer join
     *         custom custom2_
     *             on board1_.writer_email=custom2_.email
     *     where
     *         reply0_.rno=?
     */
    @Test // 즉시로딩
    public void readReply1() {
        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());

    }

    /**
     * select
     *         board0_.bno as bno1_0_0_,
     *         reply1_.rno as rno1_14_1_,
     *         board0_.mod_date as mod_date2_0_0_,
     *         board0_.reg_date as reg_date3_0_0_,
     *         board0_.content as content4_0_0_,
     *         board0_.title as title5_0_0_,
     *         board0_.writer_email as writer_e6_0_0_,
     *         reply1_.mod_date as mod_date2_14_1_,
     *         reply1_.reg_date as reg_date3_14_1_,
     *         reply1_.board_bno as board_bn6_14_1_,
     *         reply1_.replyer as replyer4_14_1_,
     *         reply1_.text as text5_14_1_
     *     from
     *         board board0_
     *     left outer join
     *         reply reply1_
     *             on (
     *                 reply1_.board_bno=board0_.bno
     *             )
     *     where
     *         board0_.bno=?
     */
    @Test
    public void testGetBoardWithReply() {
        List<Object[]> result = replyRepository.getBoardWithReply(100L);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

}