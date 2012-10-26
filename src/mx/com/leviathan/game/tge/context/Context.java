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
package mx.com.leviathan.game.tge.context;

import mx.com.leviathan.game.tge.io.receiver.Receiver;
import mx.com.leviathan.game.tge.io.sender.Sender;
import mx.com.leviathan.game.tge.player.Player;

/**
 *
 * @author Leviathan
 */
public class Context {

    private Sender sender;
    private Receiver receiver;
    private Player player;

    private Context() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public static Context getInstance() {
        return ContextHolder.INSTANCE;
    }

    private static class ContextHolder {

        private static final Context INSTANCE = new Context();
    }
}
