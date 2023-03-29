package kr.ac.kopo.ReadyToTravel.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "plan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanEntity {
    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_type")
    private int planType;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_leader")
    private String planLeader;


}
