package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.MenuItemService;
import kr.co.eatgo.domain.MenuItem;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuItemController {

    private final MenuItemService menuItemService;

    public  MenuItemController(MenuItemService menuItemService){
        this.menuItemService=menuItemService;
    }

    @GetMapping("restaurants/{restaurantId}/menuitems")
    public List<MenuItem> list(@PathVariable Long restaurantId){
        return menuItemService.getMenuItems(restaurantId);
    }
    @PatchMapping("restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(
                @PathVariable("restaurantId") Long restaurantId ,
                @Valid @RequestBody List<MenuItem> menuItems
    ){
        menuItemService.bulkUpdate(restaurantId,menuItems);
        return "";
    }
}
