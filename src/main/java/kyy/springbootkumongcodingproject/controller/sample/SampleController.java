package kyy.springbootkumongcodingproject.controller.sample;

import kyy.springbootkumongcodingproject.dto.auth.ClubAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

    // 로그인 안해도 모두 접근
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll.....");
    }

    // 로그인한 사용자만
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {

        log.info("exMember....");

        log.info(clubAuthMemberDTO);
    }

    // 관리자 권한이 있는 사용자만
    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin.....");
    }
}
