package kr.co.eatgo.application;

import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest {

    private MenuItemService menuItemService;
    @Mock
    MenuItemRepository menuItemRepository;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this) ;
//        given(menuItemRepository.save()).willReturn(restaurants);


        menuItemService= new MenuItemService(menuItemRepository);
    }
    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add( MenuItem.builder().name("Kimchi").build());
        menuItems.add( MenuItem.builder().name("GuckBob").build());
        menuItemService.bulkUpdate(1L,menuItems);

        verify(menuItemRepository,times(2)).save(any());
    }

}
