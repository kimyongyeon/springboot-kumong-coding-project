package kyy.springbootkumongcodingproject.controller.guestbook;

import kyy.springbootkumongcodingproject.dto.guestbook.GuestBookDTO;
import kyy.springbootkumongcodingproject.service.guestbook.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guestbook/rest")
@CrossOrigin("*")
public class GuestBookRestController {

    final
    GuestBookService guestBookService;

    public GuestBookRestController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @GetMapping(value = {"/", "/list"})
    public List<GuestBookDTO> list() {
        return guestBookService.list();
    }

    @GetMapping("/read")
    public GuestBookDTO read(@RequestParam(value = "gno", defaultValue = "1") int gno) {
        return guestBookService.read(gno);
    }
    @GetMapping("/register")
    public GuestBookDTO register() {
        return new GuestBookDTO();
    }

    @GetMapping("/mod")
    public GuestBookDTO mod(GuestBookDTO dto) {
        guestBookService.edit(dto); // update
        return guestBookService.read(dto.getGno()); // 수정됐는지 확인
    }

    @PostMapping("/register")
    public GuestBookDTO create(GuestBookDTO guestDTO) {
        return guestDTO;
    }

    @PostMapping("/remove")
    public GuestBookDTO remove(long gno) {
        guestBookService.remove(gno);
        return guestBookService.read(gno); // 삭제했는지 확인
    }


}
