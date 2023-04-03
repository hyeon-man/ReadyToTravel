package kr.ac.kopo.ReadyToTravel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "plan_group")
public class PlanGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long groupNum;

}
