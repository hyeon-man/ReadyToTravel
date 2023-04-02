package kr.ac.kopo.ReadyToTravel.entity.attach;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity

public class BoardAttachEntity extends Attach {

    @Id
    private int boardNum;


}
