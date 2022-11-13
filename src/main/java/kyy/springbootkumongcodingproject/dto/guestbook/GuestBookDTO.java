package kyy.springbootkumongcodingproject.dto.guestbook;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class GuestBookDTO {


    private Long gno;
    private LocalDateTime modDate;
    private LocalDateTime regDate;
    private String content;

    //https://gaemi606.tistory.com/entry/Spring-Boot-ResponseBody-%EA%B0%81-%ED%95%AD%EB%AA%A9%EC%97%90-%ED%81%AC%EA%B8%B0-%ED%95%84%EC%88%98-%EA%B0%92-%EC%84%A4%EC%A0%95-spring-boot-starter-validation

    @NotEmpty(message = "제목은 공백일 수 없습니다.")
    @NotNull(message = "제목은 null을 입력될 수 없습니다.")
    @Size(max = 10)
    private String title;
    private String writer;
}
