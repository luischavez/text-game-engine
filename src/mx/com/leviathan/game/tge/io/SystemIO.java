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
package mx.com.leviathan.game.tge.io;

import java.util.Scanner;
import mx.com.leviathan.game.tge.io.receiver.Receiver;
import mx.com.leviathan.game.tge.io.sender.Sender;

/**
 *
 * @author Leviathan
 */
public class SystemIO implements Sender, Receiver {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void send(Object object) {
        System.out.print(object);
    }

    @Override
    public Object read() {
        return scanner.nextLine();
    }

    @Override
    public <T> T read(Class<T> type) {
        return (T) read();
    }
}