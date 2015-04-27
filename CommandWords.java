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
        for (Option disponible : Option.values()){
            if (!disponible.equals(Option.UNKNOWN)){
                validCommands.put(disponible.getCommandOrder(), disponible);
            }
        }
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String cadena = GameText.AVAILABLE_COMMANDS.getText() + ":";
        int contador = 50;
        for (String valor : validCommands.keySet()){
            cadena += " " + valor;
            if (cadena.length() > contador){
                cadena += "\n";
                contador += 50;
            }
        }
        System.out.println(cadena);
    }
    
    /**
     * Muestra por pantalla todos los comandos disponibles en el juego. 
     * Cada comando lleva una descripcion.
     */
    public void showAllDetailed(){
        String cadena = GameText.AVAILABLE_COMMANDS.getText() + ":\n";
        for (Option valor : validCommands.values()){
            cadena += valor.getDetailedOrder() + "\n";
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
