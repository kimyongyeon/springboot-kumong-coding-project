package kyy.springbootkumongcodingproject.controller.guestbook;

import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletResponse;

@Controller
@RequestMapping("/guestbook")
@CrossOrigin("*")
public class GuestBookController {
    @GetMapping("/")
    public String index() {

        return "index";
    }
}
