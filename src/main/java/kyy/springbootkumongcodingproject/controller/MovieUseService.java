package kyy.springbootkumongcodingproject.controller;

import kyy.springbootkumongcodingproject.dto.movie.MovieDTO;
import kyy.springbootkumongcodingproject.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieUseService implements MovieUseCase{

    private final MovieService movieService;

    @Override
    public List list() {
        movieService.hashCode();
        return null;
    }

    @Override
    public MovieDTO view() {
        return null;
    }
}
