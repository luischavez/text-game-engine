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
package mx.com.leviathan.game.tge.io.sender;

import javax.swing.text.JTextComponent;

/**
 *
 * @author Leviathan
 */
public class SwingSender implements Sender {

    private JTextComponent component;
    private boolean append = false;

    public SwingSender(JTextComponent component, boolean append) {
        this.component = component;
        this.append = append;
    }

    @Override
    public void send(Object object) {
        if (append) {
            component.setText(component.getText() + "\n" + object);
        } else {
            component.setText(object.toString());
        }
    }
}
