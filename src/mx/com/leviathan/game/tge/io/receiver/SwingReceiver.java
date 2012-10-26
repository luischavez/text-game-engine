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
package mx.com.leviathan.game.tge.io.receiver;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Leviathan
 */
public class SwingReceiver implements Receiver {

    private JTextComponent component;

    public SwingReceiver(JTextComponent component) {
        this.component = component;
        this.component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    synchronized (SwingReceiver.this) {
                        SwingReceiver.this.notify();
                    }
                }
            }
        });
    }

    @Override
    public Object read() {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SwingReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return component.getText();
    }

    @Override
    public <T> T read(Class<T> type) {
        return (T) read();
    }
}
