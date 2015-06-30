package com.epam.game.game.logic;

import com.epam.game.game.actors.Item;
import com.epam.game.game.utils.Constants;

import java.util.List;

public class ItemGenerator {

    private ItemGenerator(){}

    public static Item createItem(Map map, List<Item> items){
        boolean findPlace = false;
        int x = 3;
        int y = 1;
        int id = 0;
        while (!findPlace){
            x = Constants.RANDOM.nextInt(map.getWidth() / 2) * 2 + 1;
            y = Constants.RANDOM.nextInt(map.getHeight() / 2) * 2 + 1;
            if (((x == map.getStartX()) && (y == map.getStartY())) || ((x == map.getExitX()) && (y == map.getExitY()))){
                continue;
            }else
            {
                findPlace = true;
                for (Item item : items) {
                    if((x == item.getItemX()) && (y == item.getItemY())){
                        findPlace = false;
                    }
                }
            }
        }
        id = Constants.RANDOM.nextInt(Item.getNumberOfItems());
        return new Item(x,y,id);
    }

}
