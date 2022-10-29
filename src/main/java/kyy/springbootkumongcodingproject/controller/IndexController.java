package kyy.springbootkumongcodingproject.controller;

import kyy.springbootkumongcodingproject.dto.auth.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Log4j2
@RequiredArgsConstructor
public class IndexController {


    @GetMapping("/")
    public void index(@AuthenticationPrincipal ClubAuthMemberDTO memberDTO, HttpServletResponse response) throws IOException {
        if (memberDTO != null) {
            response.sendRedirect("/index");
        }
       log.info("hello index");
    }

    @GetMapping("/index")
    public String helloIndex() {
        return "index";
    }
}
