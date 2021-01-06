package kr.co.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository {
    List<MenuItem> menuItems = new ArrayList<>();
    MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("Kimchi"));
    }
    @Override
    public List<MenuItem> findAllByRestaurantId(Long id) {
        System.out.println(menuItems+"아ㅏ아아아아앙");
        return menuItems;
    }
}
