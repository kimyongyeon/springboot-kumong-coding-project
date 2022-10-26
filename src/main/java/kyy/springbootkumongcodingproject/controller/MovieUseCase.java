package kyy.springbootkumongcodingproject.controller;

import kyy.springbootkumongcodingproject.dto.movie.MovieDTO;

import java.util.List;

public interface MovieUseCase {
    List list();
    MovieDTO view();

}
