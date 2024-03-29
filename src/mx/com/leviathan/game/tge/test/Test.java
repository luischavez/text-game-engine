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

import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.context.Context;
import mx.com.leviathan.game.tge.event.WorldListener;
import mx.com.leviathan.game.tge.io.receiver.SwingReceiver;
import mx.com.leviathan.game.tge.io.sender.SwingSender;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.player.Player;
import mx.com.leviathan.game.tge.test.action.AvailableItemsAction;
import mx.com.leviathan.game.tge.test.action.DropAction;
import mx.com.leviathan.game.tge.test.action.ExampleAction;
import mx.com.leviathan.game.tge.test.action.ExitAction;
import mx.com.leviathan.game.tge.test.action.GoBackAction;
import mx.com.leviathan.game.tge.test.action.GoToAction;
import mx.com.leviathan.game.tge.test.action.HelpAction;
import mx.com.leviathan.game.tge.test.action.ItemAction;
import mx.com.leviathan.game.tge.test.action.PickAction;
import mx.com.leviathan.game.tge.test.action.RoutesAction;
import mx.com.leviathan.game.tge.test.world.scene.CleanRoomScene;
import mx.com.leviathan.game.tge.test.world.scene.RoomScene;
import mx.com.leviathan.game.tge.world.World;
import mx.com.leviathan.game.tge.world.scene.Scene;
import mx.com.leviathan.game.tge.world.scene.connector.Connector;

/**
 *
 * @author Leviathan
 */
public class Test {
    
    private TestFrame frame = new TestFrame();
    /*
     * Engine
     */
    private World world = new World();
    
    public Test() {
        frame.setVisible(true);
        initEngine();
        while (true) {
            String action = Context.getInstance().getReceiver().read(String.class);
            
            if (!world.execute(action)) {
                Context.getInstance().getSender().clear();
                Context.getInstance().getSender().send("Accion invalida!!\n");
            }
        }
    }
    
    private void initEngine() {
        Context.getInstance().setPlayer(new Player());
        Context.getInstance().setReceiver(new SwingReceiver(frame.inputField));
        Context.getInstance().setSender(new SwingSender(frame.outputArea, true));
        
        world.addWorldListener(new WorldListener() {
            @Override
            public void onScene(World world, Scene scene) {
                Context.getInstance().getSender().send("Se cambio la escena a: " + scene.getName() + "\n");
            }
            
            @Override
            public void onAction(World world, Action action, String verb, ParamHolder holder) {
                Context.getInstance().getSender().send("Se ejecuto la accion: " + verb + "\n");
            }
            
            @Override
            public void onBeforeAction(World world, Action action, String verb, ParamHolder holder) {
                Context.getInstance().getSender().clear();
            }
            
            @Override
            public void onAfterAction(World world, Action action, String verb, ParamHolder holder, boolean executed) {
                if (!executed) {
                    Context.getInstance().getSender().send("Ocurrio un error al ejecutar la accion: " + verb + "\n");
                }
            }
        });
        
        world.addAction(new ExampleAction());
        world.addAction(new AvailableItemsAction());
        world.addAction(new PickAction());
        world.addAction(new HelpAction());
        world.addAction(new GoBackAction());
        world.addAction(new GoToAction());
        world.addAction(new RoutesAction());
        world.addAction(new DropAction());
        world.addAction(new ItemAction());
        world.addAction(new ExitAction());
        
        RoomScene roomScene = new RoomScene();
        CleanRoomScene cleanRoomScene = new CleanRoomScene();
        
        world.addScene(roomScene);
        world.addScene(cleanRoomScene);
        
        world.addConnector(new Connector(roomScene, cleanRoomScene, false));
        
        world.setCurrentScene("Cuarto");
    }
    
    public static void main(String... args) {
        Test test = new Test();
    }
}
