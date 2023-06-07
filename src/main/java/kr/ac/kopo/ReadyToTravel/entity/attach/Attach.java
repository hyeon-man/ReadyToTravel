package kr.ac.kopo.ReadyToTravel.entity.attach;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.List;

@Getter
@Setter
@MappedSuperclass // 상속받은 하위 클래스에 상위 클래스 필드 매핑
public class Attach {

    @Column(name = "file_name")
    private String fileName;
}
