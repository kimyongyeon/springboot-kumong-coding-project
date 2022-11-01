package kyy.springbootkumongcodingproject.repository.search;

import kyy.springbootkumongcodingproject.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchBoardRepository {
    Board search1();

    Board search2();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
