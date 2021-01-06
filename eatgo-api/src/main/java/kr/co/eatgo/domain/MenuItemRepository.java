package kr.co.eatgo.domain;

import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.Restaurant;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByRestaurantId(Long id);

}
