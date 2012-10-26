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

/**
 *
 * @author Leviathan
 */
public class Player {

    private Inventory inventory = new Inventory();
    private Registry registry = new Registry();

    private Player() {
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Registry getRegistry() {
        return registry;
    }

    public static Player getInstance() {
        return PlayerrHolder.INSTANCE;
    }

    private static class PlayerrHolder {

        private static final Player INSTANCE = new Player();
    }
}
