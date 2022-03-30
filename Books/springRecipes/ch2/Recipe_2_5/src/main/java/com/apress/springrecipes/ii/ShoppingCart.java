package com.apress.springrecipes.ii;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
// Scope(prototype) 어노테이션을 붙이면 getBean() 메소드가 실행될때마다 인스턴스가 생성됨
@Scope("prototype")
public class ShoppingCart {

    private List<Product> items = new ArrayList<>();

    public void addItem(Product item) {
        items.add(item);
    }

    public List<Product> getItems() {
        return items;
    }
}
