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

import java.util.List;
import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.annotation.PatternAction;
import mx.com.leviathan.game.tge.context.Context;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.world.World;

/**
 *
 * @author Leviathan
 */
@PatternAction(verb = "AYUDA", param = {}, regex = {})
public class HelpAction implements Action {

    @Override
    public boolean doAction(World world, String verb, ParamHolder holder) {
        List<Action> actions = world.getActions();
        for (Action action : actions) {
            if (action.getClass().isAnnotationPresent(PatternAction.class)) {
                PatternAction patternAction = action.getClass().getAnnotation(PatternAction.class);
                Context.getInstance().getSender().send(patternAction.verb() + "\n");
                for (String param : patternAction.param()) {
                    Context.getInstance().getSender().send("\t-" + param + "\n");
                }
            }
        }
        return true;
    }
}
