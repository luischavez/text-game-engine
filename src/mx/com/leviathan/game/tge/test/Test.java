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

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.event.WorldListener;
import mx.com.leviathan.game.tge.io.receiver.SwingReceiver;
import mx.com.leviathan.game.tge.io.sender.SwingSender;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.test.action.DropAction;
import mx.com.leviathan.game.tge.test.action.ExampleAction;
import mx.com.leviathan.game.tge.test.action.ExitAction;
import mx.com.leviathan.game.tge.test.action.GoBackAction;
import mx.com.leviathan.game.tge.test.action.GoToAction;
import mx.com.leviathan.game.tge.test.action.HelpAction;
import mx.com.leviathan.game.tge.test.action.ItemAction;
import mx.com.leviathan.game.tge.test.action.PickAction;
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
    
    public static void main(String... args) {
        JFrame frame = new JFrame("TEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));
        JTextField inputField = new JTextField();
        JTextArea outputArea = new JTextArea();
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.WHITE);
        outputArea.setEnabled(false);
        frame.add(inputField);
        frame.add(outputArea);
        SwingSender swingSender = new SwingSender(outputArea, true);
        SwingReceiver swingReceiver = new SwingReceiver(inputField);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setVisible(true);
        swingSender.send("Ingresa algo");
        swingSender.send(swingReceiver.read(String.class));
        Scanner scanner = new Scanner(System.in);
        
        World world = new World();
        
        world.addWorldListener(new WorldListener() {
            @Override
            public void onScene(World world, Scene scene) {
                System.out.println("Se cambio la escena a: " + scene.getName());
            }
            
            @Override
            public void onAction(World world, Action action, String verb, ParamHolder holder) {
                System.out.println("Se ejecuto la accion: " + verb);
            }
        });
        
        world.addAction(new ExampleAction());
        world.addAction(new PickAction());
        world.addAction(new HelpAction());
        world.addAction(new GoBackAction());
        world.addAction(new GoToAction());
        world.addAction(new DropAction());
        world.addAction(new ItemAction());
        world.addAction(new ExitAction());
        
        RoomScene roomScene = new RoomScene();
        CleanRoomScene cleanRoomScene = new CleanRoomScene();
        
        world.addScene(roomScene);
        world.addScene(cleanRoomScene);
        
        world.addConnector(new Connector(roomScene, cleanRoomScene));
        
        world.setCurrentScene("Cuarto");
        
        while (true) {
            System.out.print("Ingresa una accion >> ");
            String action = scanner.nextLine();
            
            world.execute(action);
        }
    }
}
