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
package mx.com.leviathan.game.tge.world.scene.connector;

import mx.com.leviathan.game.tge.world.scene.Scene;

/**
 *
 * @author Leviathan
 */
public class Connector {

    private Scene source, target;
    private boolean connected = true;

    public Connector(Scene source, Scene target) {
        this.source = source;
        this.target = target;
    }

    public Connector(Scene source, Scene target, boolean connected) {
        this.source = source;
        this.target = target;
        this.connected = connected;
    }

    public Scene getSource() {
        return source;
    }

    public Scene getTarget() {
        return target;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
