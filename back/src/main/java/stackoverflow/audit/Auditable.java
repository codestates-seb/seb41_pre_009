package stackoverflow.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;


    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_TIME")
    private LocalDateTime modifiedTime;


    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
}
