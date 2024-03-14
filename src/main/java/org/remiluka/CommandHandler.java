package org.remiluka;

import org.remiluka.features.drinking.DrinkMaster;
import org.remiluka.features.drinking.commands.Blargk;
import org.remiluka.features.drinking.commands.GiveDrink;
import org.remiluka.features.drinking.drinks.Drink;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import revxrsal.commands.exception.CommandErrorException;

public class CommandHandler {
    public static void registerCommands() {
        BukkitCommandHandler handler = BukkitCommandHandler.create(Main.getInstance());

        // Resolvers
        handler.registerValueResolver(Drink.class, context -> {
            String value = context.pop();

            if (DrinkMaster.isDrink(value))
                return DrinkMaster.getDrinkById(value);
            throw new CommandErrorException("Boisson inconnue, voici la liste de celles qui existent:\n" + String.join(", ", DrinkMaster.getDrinkIds()));
        });

        // Auto-Complete
        handler.getAutoCompleter().registerParameterSuggestions(Drink.class, (args, sender, command) -> DrinkMaster.getDrinkIds());

        // Commands
        handler.register(GiveDrink.class);
        handler.register(Blargk.class);

        // The rest
        handler.registerBrigadier();
    }
}
