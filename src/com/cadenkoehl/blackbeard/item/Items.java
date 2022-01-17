package com.cadenkoehl.blackbeard.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Items {
    private static final List<Item> ITEMS = new ArrayList<>();

    public static final Item HEART = register(new HeartItem());

    private static Item register(Item item) {
        ITEMS.add(item);
        return item;
    }

    public static List<Item> getItems() {
        return ITEMS;
    }

    public static Item randomItem() {
        return ITEMS.get(new Random().nextInt(ITEMS.size()));
    }
}
