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
package mx.com.leviathan.game.tge.test.item;

import mx.com.leviathan.game.tge.item.Item;
import mx.com.leviathan.game.tge.world.World;

/**
 *
 * @author Leviathan
 */
public class KeyItem extends Item {

    public KeyItem() {
        setName("llave");
    }

    @Override
    public void onUse(World world, String... actions) {
        for (String action : actions) {
            if (action.equalsIgnoreCase("limpiar")) {
                System.out.println("se limpio la llave");
            }
        }
    }
}
