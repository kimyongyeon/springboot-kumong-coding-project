package kyy.springbootkumongcodingproject.repository.search;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchBoardServiceTest {

    @Autowired
    SearchBoardService searchBoardService;

    @Test
    public void testSearch() {
        List read = searchBoardService.read();
        System.out.println(read);
    }
}