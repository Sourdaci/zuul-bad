import java.util.HashMap;
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

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, Option>();
        validCommands.put("ir", Option.GO);
        validCommands.put("salir", Option.QUIT);
        validCommands.put("ayuda", Option.HELP);
        validCommands.put("ver", Option.LOOK);
        validCommands.put("comer", Option.EAT);
        validCommands.put("atras", Option.BACK);
        validCommands.put("objetos", Option.ITEMS);
        validCommands.put("coger", Option.TAKE);
        validCommands.put("soltar", Option.DROP);
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String cadena = "Comandos disponibles:";
        for (String valor : validCommands.keySet()){
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
        return validCommands.containsKey(aString);
    }
    
    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        if(validCommands.containsKey(commandWord)){
            return validCommands.get(commandWord);
        }else{
            return Option.UNKNOWN;
        }
    }
}
