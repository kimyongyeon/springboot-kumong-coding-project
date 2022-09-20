package kyy.springbootkumongcodingproject.service.guestbook;

import kyy.springbootkumongcodingproject.dto.guestbook.GuestBookDTO;
import kyy.springbootkumongcodingproject.entity.guestbook.GuestBookEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface GuestBookService {
    List<GuestBookDTO> list();
    GuestBookDTO read(long gno);

    void edit(GuestBookDTO guestBookDTO);

    void remove(long gno);

    default GuestBookEntity dtoToEntity(GuestBookDTO dto) {
        GuestBookEntity entity = GuestBookEntity.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
}

@Service
class GuestBookServiceImpl implements GuestBookService {
    private static List<GuestBookDTO> guestBookList;

    GuestBookServiceImpl() {
        guestBookList = new ArrayList<>();
        createGuestBookList();
    }

    public static void createGuestBook(GuestBookDTO guestBookDTO) {
        guestBookList.add(guestBookDTO);
    }

    public List<GuestBookDTO> list() {
        return guestBookList;
    }

    public GuestBookDTO read(long gno) {
        // 목록 기본데이터를 만들지 않고 호출하다가 에러가 호출되 에러조건문 추가.
        if (guestBookList.size() > 0) {
            return guestBookList.stream()
                    .filter(gDTO -> gDTO.getGno() == gno)
                    .findFirst()
                    .orElse(new GuestBookDTO());
        } else {
            return new GuestBookDTO();
        }
    }

    @Override
    public void edit(GuestBookDTO guestBookDTO) {
        // 수정하고 나서 read 반드시 실행
    }

    @Override
    public void remove(long gno) {
        // 삭제하고 remove 반드시 실행
    }

    private void createGuestBookList() {
        for(int i=0; i<10; i++) {
            GuestBookDTO guestBookDTO = new GuestBookDTO();
            guestBookDTO.setGno(Long.parseLong(i+""));
            guestBookDTO.setContent("방명록 작성은 반드시 해야 합니다. :: " + i);
            guestBookDTO.setTitle("방명록을 만드는 방법은? :: " + i);
            guestBookDTO.setWriter("관리자 :: " + i);
            guestBookDTO.setRegDate(LocalDateTime.now());
            guestBookList.add(guestBookDTO);
        }
    }
}
