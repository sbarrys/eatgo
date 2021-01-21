package kr.co.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue
    private  Long id;
    @NotEmpty
    private Long restaurantId;
    @NotEmpty
    private  String name;

    public String getName() {
        return name;
    }
}
