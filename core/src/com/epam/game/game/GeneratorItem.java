package com.epam.game.game;

import com.epam.game.game.logic.Constants;
import com.epam.game.game.logic.Item;
import com.epam.game.game.logic.Map;

import java.util.List;

/**
 * Created by Alenka on 30.06.2015.
 */
public class GeneratorItem {

    private GeneratorItem(){}

    public static Item CreateItem(Map map, List<Item> items){
        boolean findPlace = false;
        int x = 3;
        int y = 1;
        int id = 0;
//        while (!findPlace){
//            x = Constants.RANDOM.nextInt(map.getWidth() / 2) * 2 + 1;
//            y = Constants.RANDOM.nextInt(map.getHeigth() / 2) * 2 + 1;
//            if (((x == map.getStartX()) && (y == map.getStartY())) || ((x == map.get_exitX()) && (y == map.get_exitY()))){
//                continue;
//            }else
//            {
//                findPlace = true;
//                for (Item item : items) {
//                    if((x == item.get_x()) && (y == item.get_y())){
//                        findPlace = false;
//                    }
//                }
//            }
//        }
        id = Constants.RANDOM.nextInt(Item.getNumberOfItems());
        return new Item(x,y,id);
    }

}
