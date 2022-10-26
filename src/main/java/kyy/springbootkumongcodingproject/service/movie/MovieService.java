package kyy.springbootkumongcodingproject.service.movie;

import kyy.springbootkumongcodingproject.dto.movie.MovieDTO;
import kyy.springbootkumongcodingproject.dto.movie.MovieImageDTO;
import kyy.springbootkumongcodingproject.entity.movie.Movie;
import kyy.springbootkumongcodingproject.entity.movie.MovieImage;
import kyy.springbootkumongcodingproject.repository.movie.MovieImageRepository;
import kyy.springbootkumongcodingproject.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    Long register(MovieDTO movieDTO);

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {
        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        // MovieImageDTO 처리
        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {
                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", movieImageList);
        }

        return entityMap;
    }


    @Service
    @Log4j2
    @RequiredArgsConstructor
    class MovieServiceImpl implements MovieService {

        private final MovieRepository repository;

        private final MovieImageRepository movieImageRepository;

        @Transactional
        @Override
        public Long register(MovieDTO movieDTO) {
            Map<String, Object> entityMap = dtoToEntity(movieDTO);
            Movie movie = (Movie) entityMap.get("movie");
            List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

            repository.save(movie);

            movieImageList.forEach(movieImage -> {
                movieImageRepository.save(movieImage);
            });

            return movie.getMno();
        }
    }
}


