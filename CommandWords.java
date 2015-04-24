import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static HashMap<String, Option> validCommands;
    private static Option comandos;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, Option>();
        validCommands.put("go", comandos.GO);
        validCommands.put("quit", comandos.QUIT);
        validCommands.put("help", comandos.HELP);
        validCommands.put("look", comandos.LOOK);
        validCommands.put("eat", comandos.EAT);
        validCommands.put("back", comandos.BACK);
        validCommands.put("items", comandos.ITEMS);
        validCommands.put("take", comandos.TAKE);
        validCommands.put("drop", comandos.DROP);
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String cadena = "Comandos disponibles:";
        Set<String> opciones = validCommands.keySet();
        for (String valor : opciones){
            cadena += " " + valor;
        }
        System.out.println(cadena);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        if(validCommands.get(aString) != null){
            return true;
        }else{
            return false;
        }
    }
}
