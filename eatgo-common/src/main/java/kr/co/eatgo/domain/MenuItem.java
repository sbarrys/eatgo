package kr.co.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Getter
    @Id
    @GeneratedValue
    private  Long id;
    @NotEmpty
    private  String name;
    @Setter
    private Long restaurantId;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)// boolean 의 default 값이 false 인데, 디폴트와 다르다면 넣어줘라.
    private boolean destroy;

    public Boolean isDestroy(){
        return destroy;
    }
    public String getName() {
        return name;
    }


    public Long getRestaurantId() {
        return restaurantId;
    }
}
