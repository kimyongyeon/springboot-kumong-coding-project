package kyy.springbootkumongcodingproject.controller.guestbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@CrossOrigin("*")
public class GuestBookController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
