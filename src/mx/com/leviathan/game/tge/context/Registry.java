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

import java.util.HashMap;
import java.util.Map;
import mx.com.leviathan.game.tge.param.ParamHolder;

/**
 *
 * @author Leviathan
 */
public class Registry {

    private ParamHolder holder = new ParamHolder();

    private Registry() {
    }

    public void setFlag(String key, boolean status) {
        holder.add(key, status);
    }

    public boolean status(String key) {
        return holder.exist(key) ? holder.get(key, Boolean.class) : false;
    }

    public ParamHolder getHolder() {
        return holder;
    }

    public static Registry getInstance(Object object) {
        if (RegistryHolder.contains(object)) {
            return RegistryHolder.get(object);
        }
        return RegistryHolder.newInstance(object);
    }

    private static class RegistryHolder {

        private static final Map<Object, Registry> registrys = new HashMap<Object, Registry>();

        private static Registry newInstance(Object object) {
            registrys.put(object, new Registry());
            return get(object);
        }

        private static Registry get(Object object) {
            return registrys.get(object);
        }

        private static boolean contains(Object object) {
            return registrys.containsKey(object);
        }
    }
}