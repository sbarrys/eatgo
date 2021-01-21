package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.MenuItemService;
import kr.co.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuItemController {
    private MenuItemService menuItemService;
    @Autowired
    public void MeuItemController(MenuItemService menuItemService){
        this.menuItemService=menuItemService;
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
