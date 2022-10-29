package kyy.springbootkumongcodingproject.controller.movie;

import kyy.springbootkumongcodingproject.dto.common.PageRequestDTO;
import kyy.springbootkumongcodingproject.dto.common.PageResultDTO;
import kyy.springbootkumongcodingproject.dto.movie.MovieDTO;
import kyy.springbootkumongcodingproject.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {
        log.info("register called");
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO: ", movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(){
        log.info("list called");
    }

    @GetMapping({"read", "modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("mno" + mno);

        MovieDTO movieDTO = movieService.getMovie(mno);

        model.addAttribute("dto", movieDTO);
    }


    @GetMapping("/rest/list")
    @ResponseBody
    public PageResultDTO<MovieDTO, Object[]> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("pageRequestDTO: " + pageRequestDTO);
        // model.addAttribute("result", movieService.getList(pageRequestDTO));
        return movieService.getList(pageRequestDTO);
    }

}
