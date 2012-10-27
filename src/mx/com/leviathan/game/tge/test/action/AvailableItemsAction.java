/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.leviathan.game.tge.test.action;

import java.util.List;
import mx.com.leviathan.game.tge.action.Action;
import mx.com.leviathan.game.tge.annotation.PatternAction;
import mx.com.leviathan.game.tge.context.Context;
import mx.com.leviathan.game.tge.item.Item;
import mx.com.leviathan.game.tge.param.ParamHolder;
import mx.com.leviathan.game.tge.world.World;

/**
 *
 * @author Luis
 */
@PatternAction(verb = "ITEMS", param = {}, regex = {})
public class AvailableItemsAction implements Action {
    
    @Override
    public boolean doAction(World world, String verb, ParamHolder holder) {
        List<Item> items = world.getCurrentScene().getItems();
        for (Item item : items) {
            Context.getInstance().getSender().send("- " + item.getName() + "\n");
        }
        return true;
    }
}
