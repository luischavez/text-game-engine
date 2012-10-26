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
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.context.Context;
import mx.com.leviathan.game.tge.event.WorldListener;
import mx.com.leviathan.game.tge.io.receiver.SwingReceiver;
import mx.com.leviathan.game.tge.io.sender.SwingSender;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.player.Player;
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

    /*
     * Swing Components
     */
    private JFrame frame = new JFrame("TEST");
    private JTextField inputField = new JTextField();
    private JTextArea outputArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    /*
     * Engine
     */
    World world = new World();

    public Test() {
        initComponents();
        initEngine();
        while (true) {
            Context.getInstance().getSender().send("Ingresa una accion >> ");
            String action = Context.getInstance().getReceiver().read(String.class);
            Context.getInstance().getSender().clear();

            world.execute(action);
        }
    }

    private void initComponents() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.WHITE);
        outputArea.setEditable(false);

        frame.add(inputField);
        frame.add(scrollPane);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initEngine() {
        Context.getInstance().setPlayer(new Player());
        Context.getInstance().setReceiver(new SwingReceiver(inputField));
        Context.getInstance().setSender(new SwingSender(outputArea, true));

        world.addWorldListener(new WorldListener() {
            @Override
            public void onScene(World world, Scene scene) {
                Context.getInstance().getSender().send("Se cambio la escena a: " + scene.getName() + "\n");
            }

            @Override
            public void onAction(World world, Action action, String verb, ParamHolder holder) {
                Context.getInstance().getSender().send("Se ejecuto la accion: " + verb + "\n");
            }
        });

        world.addAction(new ExampleAction());
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

        world.addConnector(new Connector(roomScene, cleanRoomScene));

        world.setCurrentScene("Cuarto");
    }

    public static void main(String... args) {
        Test test = new Test();
    }
}
