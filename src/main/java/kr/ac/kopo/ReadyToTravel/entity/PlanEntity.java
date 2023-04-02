package kr.ac.kopo.ReadyToTravel.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "plan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanEntity {

    @Id
    @Column(name = "plan_num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long num;

    @Column(name = "plan_type")
    private int planType;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_leader")
    private String planLeader;


}
