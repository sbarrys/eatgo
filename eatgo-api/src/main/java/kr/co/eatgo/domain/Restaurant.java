package kr.co.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Restaurant {
    @Id
    @GeneratedValue
    private  Long id;
    @NotEmpty
    private  String name;
    @NotEmpty
    private  String address;

    @Transient// 임시로 사용하는 변수에요~
    @JsonInclude(JsonInclude.Include.NON_NULL)// Null 이 아닐때만 응답JSON에 넣어줘라.
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public String getInformation() {
        return name + " in " + address+menuItems;
    }
    public List<MenuItem> getMenuItems(){
        return menuItems;
    }
    public void addMenuItem(MenuItem name) {
        menuItems.add(name);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>();
        for( MenuItem menuItem: menuItems){
            addMenuItem(menuItem);
        }
    }


    public void updateInformation(String name, String address) {
        this.name= name;
        this.address =address;

    }
}
