package kr.co.eatgo.application;

import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        update(restaurantId, menuItems);
    }

    private void update(Long restaurantId, List<MenuItem> menuItems) {
        for( MenuItem menuItem : menuItems ){
            if (menuItem.isDestroy()){
                menuItemRepository.deleteById(menuItem.getId());
                continue;
            }
            menuItem.setRestaurantId(restaurantId);
            menuItemRepository.save(menuItem);
        }
    }
    public List<MenuItem> getMenuItems(long restaurantId){

        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }
}
