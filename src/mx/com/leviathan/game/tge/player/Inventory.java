/*
 * Copyright 2012 Leviathan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.com.leviathan.game.tge.player;

import java.util.ArrayList;
import java.util.List;
import mx.com.leviathan.game.tge.item.Item;

/**
 *
 * @author Leviathan
 */
public class Inventory {

    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean contains(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
