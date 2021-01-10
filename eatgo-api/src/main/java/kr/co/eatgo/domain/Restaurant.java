package kr.co.eatgo.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
public class Restaurant {
    private  String name;
    private  String address;
    private  Long id;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();
    @Autowired
    public Restaurant(Long id,String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Restaurant() {
    }

    public Restaurant(String name, String address) {
        this.name= name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id =id;
    }
    public String getName() {
        return name;
    }

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
        for( MenuItem menuItem: menuItems){
            addMenuItem(menuItem);
        }
    }


}
