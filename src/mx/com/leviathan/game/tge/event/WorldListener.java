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
package mx.com.leviathan.game.tge.event;

import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.world.World;
import mx.com.leviathan.game.tge.world.scene.Scene;

/**
 *
 * @author Leviathan
 */
public interface WorldListener {

    public void onScene(World world, Scene scene);

    public void onAction(World world, Action action, String verb, ParamHolder holder);

    public void onBeforeAction(World world, Action action, String verb, ParamHolder holder);

    public void onAfterAction(World world, Action action, String verb, ParamHolder holder, boolean executed);
}
