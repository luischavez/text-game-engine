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
package mx.com.leviathan.game.tge.test.action;

import java.util.List;
import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.action.ParamHolder;
import mx.com.leviathan.game.tge.item.Item;
import mx.com.leviathan.game.tge.pattern.PatternAction;
import mx.com.leviathan.game.tge.world.World;

/**
 *
 * @author Leviathan
 */
@PatternAction(verb = "Tomar", param = {"item_name"}, regex = {"^[\\w]+$"})
public class PickAction implements Action {
    
    @Override
    public boolean doAction(World world, String verb, ParamHolder holder) {
        List<Item> items = world.getCurrentScene().getItems();
        for (Item item : items) {
            if (item.getName().equals(holder.get("item_name"))) {
                System.out.println("Tomaste el item " + holder.get("item_name"));
                return true;
            }
        }
        return false;
    }
}
