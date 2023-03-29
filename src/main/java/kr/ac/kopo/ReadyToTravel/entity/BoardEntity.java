package kr.ac.kopo.ReadyToTravel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "board")
public class BoardEntity {

    @Id
    @Column(name = "board_id")
    private Long id;

}
