package kr.ac.kopo.ReadyToTravel.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "place")
public class PlaceEntity {
    @Id
    @Column(name = "place_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long num;
    private String kName;
    private String eName;
    @Column(length = 500)
    private String comment;
    private String fileName;
}
