package kyy.springbootkumongcodingproject.service.movie;

import kyy.springbootkumongcodingproject.dto.common.PageRequestDTO;
import kyy.springbootkumongcodingproject.dto.common.PageResultDTO;
import kyy.springbootkumongcodingproject.dto.movie.MovieDTO;
import kyy.springbootkumongcodingproject.dto.movie.MovieImageDTO;
import kyy.springbootkumongcodingproject.entity.movie.Movie;
import kyy.springbootkumongcodingproject.entity.movie.MovieImage;
import kyy.springbootkumongcodingproject.repository.movie.MovieImageRepository;
import kyy.springbootkumongcodingproject.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
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

    // 중간에서 시작하니 PageResultDTO, PageRequestDTO가 없어서 난감했다. 앞장으로 돌아가서 DTO만들고 다시 돌아왔다. P158
    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

    MovieDTO getMovie(Long mno);

    @Service
    @Log4j2
    @RequiredArgsConstructor
    class MovieServiceImpl implements MovieService {

        private final ModelMapper modelMapper;

        public MovieDTO entityToDTO(Movie movie, List<MovieImage> movieImageList, Double avg, Long reviewCnt) {
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

            List<MovieImageDTO> movieImageDTOList = movieImageList.stream()
                    .map(movieImage -> {
                        return MovieImageDTO.builder().imgName(movieImage.getImgName())
                                .path(movieImage.getPath())
                                .uuid(movieImage.getUuid())
                                .build();
                    }).collect(Collectors.toList());

            movieDTO.setImageDTOList(movieImageDTOList);
            movieDTO.setAvg(avg);
            movieDTO.setReviewCnt(reviewCnt.intValue());

            return movieDTO;
        }



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

        @Override
        public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
            Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());
            Page<Object[]> result = repository.getListPage(pageable);

            // PageResultDTO 생성자 함수에서 map에서 사용할 함수를 미리 만든다.
            // map은 결과값을 매핑하는 작업을 한다. 즉, object에서 dto로 매핑을 한다.
            Function<Object[], MovieDTO> fn = (arr -> entityToDTO(
                    (Movie)arr[0],
                    (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                    (Double)arr[2],
                    (Long)arr[3]
            ));

            // Object to MovieDTO
            return new PageResultDTO<>(result, fn);
        }

        @Override
        public MovieDTO getMovie(Long mno) {
            List<Object[]> result = repository.getMovieWithAll(mno);

            Movie movie = (Movie) result.get(0)[0];

            List<MovieImage> movieImageList = new ArrayList<>();

            result.forEach(arr -> {
                MovieImage movieImage = (MovieImage) arr[1];
                movieImageList.add(movieImage);
            });

            Double avg = (Double) result.get(0)[2];
            Long reviewCnt = (Long) result.get(0)[3];

            return entityToDTO(movie, movieImageList, avg, reviewCnt);


        }


    }
}


