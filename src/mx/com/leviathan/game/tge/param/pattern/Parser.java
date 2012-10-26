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
package mx.com.leviathan.game.tge.param.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mx.com.leviathan.game.tge.annotation.PatternAction;
import mx.com.leviathan.game.tge.param.ParamHolder;

/**
 *
 * @author Leviathan
 */
public class Parser {

    public static String getVerb(String target) {
        return target.substring(0, target.indexOf(" ") != -1 ? target.indexOf(" ") : target.length());
    }

    public static String[] getValues(String target) {
        String[] values = target.replace(getVerb(target), "").split(" ");
        List<String> list = new ArrayList<String>(Arrays.asList(values));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            if (string.trim().matches("")) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll("_", " "));
        }
        return list.toArray(new String[list.size()]);
    }

    public static boolean isValid(PatternAction patternAction, String target) {
        String verb = getVerb(target);
        if (patternAction.verb().equals(verb)) {
            String[] values = getValues(target);
            if (values.length == patternAction.param().length) {
                String[] regex = patternAction.regex();
                for (int i = 0; i < values.length; i++) {
                    Matcher matcher = Pattern.compile(regex[i]).matcher(values[i]);
                    if (!matcher.matches()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static ParamHolder parse(PatternAction patternAction, String target) {
        ParamHolder paramHolder = new ParamHolder();
        if (isValid(patternAction, target)) {
            String[] params = patternAction.param();
            String[] values = getValues(target);
            for (int i = 0; i < params.length; i++) {
                paramHolder.add(params[i], values[i]);
            }
        }
        return paramHolder;
    }
}
