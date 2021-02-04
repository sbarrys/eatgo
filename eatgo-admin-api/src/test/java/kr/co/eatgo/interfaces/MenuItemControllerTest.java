package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.MenuItemService;
import kr.co.eatgo.domain.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private MenuItemService menuItemService;
    @Test
    public void list() throws Exception{
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("Kimchi").build());
        given(menuItemService.getMenuItems(1004L)).willReturn(menuItems);
        mvc.perform(get("/restaurants/1004/menuitems"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Kimchi")));
    }
    @Test
    public void bulkUpdateWithJson() throws Exception {
        mvc.perform(patch("/restaurants/1/menuitems")
        .contentType(MediaType.APPLICATION_JSON)
        .content("[]"))
        .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(1L),any());
    }
}
