package kyy.springbootkumongcodingproject.dto.guestbook;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GuestBookDTO {
    private Long gno;
    private LocalDateTime modDate;
    private LocalDateTime regDate;
    private String content;
    private String title;
    private String writer;
}
