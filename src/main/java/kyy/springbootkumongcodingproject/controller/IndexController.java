package kyy.springbootkumongcodingproject.controller;

import kyy.springbootkumongcodingproject.dto.auth.ClubAuthMemberDTO;
import kyy.springbootkumongcodingproject.dto.guestbook.GuestBookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.IOException;

@Controller
@Log4j2
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/hello")
    @ResponseBody
    String hello(@Valid GuestBookDTO guestBookDTO, BindingResult bindingResult) {
        if (guestBookDTO.getTitle() == null) {
            bindingResult.reject("401", "title null");
        }
        if (bindingResult.hasErrors()) {
            return "bindResult error";
        }
        return "ok";
    }


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
