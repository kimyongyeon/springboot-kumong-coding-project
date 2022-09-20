package kyy.springbootkumongcodingproject.entity.guestbook;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class}) // 엔티티 변경되는거 감지(생성, 변경)
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name="regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedBy
    @Column(name = "modDate")
    private LocalDateTime modDate;
}
