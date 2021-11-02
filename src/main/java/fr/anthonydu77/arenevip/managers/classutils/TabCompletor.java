package fr.anthonydu77.arenevip.managers.classutils;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.CommandSender;

/**
 * Created by Anthonydu77 03/08/2021 inside the package - fr.anthonydu77.redlandfarm.managers.apiclass
 */

public class TabCompletor {
    public TabCompletor() {
    }

    public static List<String> onTabComplete(List<String> suggestions, CommandSender sender, String[] args) {
        List<String> result = new ArrayList();
        String check = args[args.length - 1];
        if (check.length() > 0) {
            Iterator var6 = suggestions.iterator();

            while (var6.hasNext()) {
                String suggestion = (String) var6.next();
                if (suggestion.substring(0, check.length() < suggestion.length() ? check.length() : suggestion.length()).equalsIgnoreCase(check)) {
                    result.add(suggestion);
                }
            }
        } else {
            suggestions.forEach((suggestionx) -> {
                result.add(suggestionx);
            });
        }

        return result;
    }
}