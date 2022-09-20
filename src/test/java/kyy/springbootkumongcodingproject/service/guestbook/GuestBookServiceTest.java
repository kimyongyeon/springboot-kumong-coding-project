package kyy.springbootkumongcodingproject.service.guestbook;

import kyy.springbootkumongcodingproject.dto.guestbook.GuestBookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestBookServiceTest {

    @Autowired
    GuestBookService guestBookService;

    @Test
    void list() {
        List<GuestBookDTO> list = guestBookService.list();
        System.out.println(list);

    }

    @Test
    void read() {
    }

    @Test
    void createGuestBookList() {
    }
}