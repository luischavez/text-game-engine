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

import mx.com.leviathan.game.tge.context.Context;
import mx.com.leviathan.game.tge.context.Registry;
import mx.com.leviathan.game.tge.item.Item;
import mx.com.leviathan.game.tge.world.World;
import mx.com.leviathan.game.tge.world.scene.connector.Connector;

/**
 *
 * @author Leviathan
 */
public class KeyItem extends Item {

    private String from, to;

    public KeyItem(String from, String to) {
        this.from = from;
        this.to = to;
        setName("llave " + to);
    }

    @Override
    public void on(World world, String action) {
        Registry registry = Registry.getInstance(this);
        if (action.equalsIgnoreCase("usar")) {
            if (registry.status("limpia")) {
                if (world.getCurrentScene().getName().equals(from)) {
                    if (world.hasConnector(from, to)) {
                        Connector connector = world.getConnector(from, to);
                        if (!connector.isConnected()) {
                            connector.setConnected(true);
                            Context.getInstance().getPlayer().getInventory().removeItem(this);
                        }
                    }
                }
            } else {
                Context.getInstance().getSender().send("la llave esta muy sucia, no se puede usar\n");
            }
        }

        if (action.equalsIgnoreCase("limpiar")) {
            if (registry.status("limpia")) {
                Context.getInstance().getSender().send("la llave ya estaba limpia\n");
            } else {
                Context.getInstance().getSender().send("se limpio la llave\n");
                registry.setFlag("limpia", true);
            }
        }
    }
}
