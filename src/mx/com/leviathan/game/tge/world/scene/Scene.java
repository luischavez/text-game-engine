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
package mx.com.leviathan.game.tge.world.scene;

import java.util.ArrayList;
import java.util.List;
import mx.com.leviathan.game.tge.item.Item;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.world.World;

/**
 *
 * @author Leviathan
 */
public abstract class Scene {
    
    private String name;
    private List<Item> items = new ArrayList<Item>();
    
    public String getName() {
        return name;
    }
    
    protected void setName(String name) {
        this.name = name;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public abstract void doAction(World world, String verb, ParamHolder holder);
}
