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
package mx.com.leviathan.game.tge.test.action;

import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.action.ParamHolder;
import mx.com.leviathan.game.tge.pattern.PatternAction;
import mx.com.leviathan.game.tge.world.World;

/**
 *
 * @author Leviathan
 */
@PatternAction(verb = "Atras", param = {}, regex = {})
public class GoBackAction implements Action {

    @Override
    public boolean doAction(World world, String verb, ParamHolder holder) {
        if (world.previous()) {
            System.out.println("actualmente estas en: " + world.getCurrentScene().getName());
            return true;
        }
        return false;
    }
}