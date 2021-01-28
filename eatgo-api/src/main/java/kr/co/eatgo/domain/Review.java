package kr.co.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String name;
    @NonNull
    private Integer score;

    @NotEmpty
    private  String description;

    private Long restaurantId;
}
