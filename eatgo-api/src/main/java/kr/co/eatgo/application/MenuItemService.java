package kr.co.eatgo.application;

import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long Id, List<MenuItem> menuItems) {
//        MenuItem menuItem=MenuItem.builder().build();
        for( MenuItem menuItem : menuItems ){
            menuItemRepository.save(menuItem);
        }
    }
}
