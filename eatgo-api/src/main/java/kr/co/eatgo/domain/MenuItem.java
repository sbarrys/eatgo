package kr.co.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private  String name;
    @Setter
    private Long restaurantId;

    public String getName() {
        return name;
    }


    public Long getRestaurantId() {
        return restaurantId;
    }
}
