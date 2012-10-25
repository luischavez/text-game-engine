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
package mx.com.leviathan.game.tge.test;

import java.util.Scanner;
import mx.com.leviathan.game.tge.action.ParamHolder;
import mx.com.leviathan.game.tge.player.Player;
import mx.com.leviathan.game.tge.test.action.ExampleAction;
import mx.com.leviathan.game.tge.test.action.HelpAction;
import mx.com.leviathan.game.tge.test.action.PickAction;
import mx.com.leviathan.game.tge.test.item.KeyItem;
import mx.com.leviathan.game.tge.test.world.scene.RoomScene;
import mx.com.leviathan.game.tge.world.World;
import mx.com.leviathan.game.tge.world.scene.Scene;

/**
 *
 * @author Leviathan
 */
public class Test {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa una accion >> ");
        String action = scanner.nextLine();
        World world = new World();
        world.addAction(new ExampleAction());
        world.addAction(new PickAction());
        world.addAction(new HelpAction());
        RoomScene exampleScene = new RoomScene();
        world.addScene(exampleScene);
        world.setCurrentScene("Cuarto");
        
        Player.getInstance().getInventory().addItem(new KeyItem());
        world.execute(action);
    }
}