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
package mx.com.leviathan.game.tge.world;

import java.util.ArrayList;
import java.util.List;
import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.action.ParamHolder;
import mx.com.leviathan.game.tge.pattern.Parser;
import mx.com.leviathan.game.tge.pattern.PatternAction;
import mx.com.leviathan.game.tge.player.Player;
import mx.com.leviathan.game.tge.world.scene.Scene;
import mx.com.leviathan.game.tge.world.scene.connector.Connector;

/**
 *
 * @author Leviathan
 */
public class World {

    private List<Action> actions = new ArrayList<Action>();
    private List<Scene> scenes = new ArrayList<Scene>();
    private List<Connector> connectors = new ArrayList<Connector>();
    private List<Scene> routes = new ArrayList<Scene>();
    private Scene currentScene;

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String name) {
        Scene sceneFor = sceneFor(name);
        if (sceneFor != null) {
            currentScene = sceneFor;
        }
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(Action action) {
        actions.remove(action);
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void removeScene(Scene scene) {
        scenes.remove(scene);
    }

    public List<Connector> getConnectors() {
        return connectors;
    }

    public void addConnector(Connector connector) {
        connectors.add(connector);
    }

    public void removeConnector(Connector connector) {
        connectors.remove(connector);
    }

    public boolean previous() {
        if (!routes.isEmpty()) {
            currentScene = routes.remove(routes.size() - 1);
            return true;
        }
        return false;
    }

    public boolean goTo(String scene) {
        for (Connector connector : connectors) {
            if (connector.getSource().getName().equalsIgnoreCase(currentScene.getName())) {
                if (connector.getTarget().getName().equalsIgnoreCase(scene)) {
                    routes.add(connector.getSource());
                    currentScene = connector.getTarget();
                    return true;
                }
            }
        }
        return false;
    }

    public Scene sceneFor(String scene) {
        for (Scene s : scenes) {
            if (s.getName().equalsIgnoreCase(scene)) {
                return s;
            }
        }
        return null;
    }

    public Action actionFor(String action) {
        for (Action a : actions) {
            if (a.getClass().isAnnotationPresent(PatternAction.class)) {
                PatternAction patternAction = a.getClass().getAnnotation(PatternAction.class);
                if (Parser.isValid(patternAction, action)) {
                    return a;
                }
            }
        }
        return null;
    }

    public boolean execute(String action) {
        Action actionFor = actionFor(action);
        if (actionFor != null) {
            String verb = Parser.getVerb(action);
            PatternAction patternAction = actionFor.getClass().getAnnotation(PatternAction.class);
            ParamHolder holder = Parser.parse(patternAction, action);
            if (actionFor.doAction(this, verb, holder)) {
                currentScene.doAction(this, verb, holder);
                return true;
            }
        }
        Player player = Player.getInstance();
        String verb = Parser.getVerb(action);
        if (player.getInventory().contains(verb)) {
            player.getInventory().getItem(verb).onUse(this, Parser.getValues(action));
            return true;
        }
        return false;
    }
}
