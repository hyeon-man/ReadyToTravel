package kr.ac.kopo.ReadyToTravel.entity.attach;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
//@MappedSuperclass = 상위 클래스에 필드를 하위 클래스에게도 매핑
@MappedSuperclass
public class Attach {


    @Column(name = "file_name")
    String fileName;
}
