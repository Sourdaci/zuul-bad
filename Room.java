import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> salidasDisponibles;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidasDisponibles = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room diagonalNE, Room east, Room diagonalSE, 
                        Room south, Room diagonalSW, Room west, Room diagonalNW) 
    {
        if(north != null)
            salidasDisponibles.put("north", north);
        if(diagonalNE != null)
            salidasDisponibles.put("northeast", diagonalNE);
        if(east != null)
            salidasDisponibles.put("east", east);
        if(diagonalSE != null)
            salidasDisponibles.put("southeast", diagonalSE);
        if(south != null)
            salidasDisponibles.put("south", south);
        if(diagonalSW != null)
            salidasDisponibles.put("southwest", diagonalSW);
        if(west != null)
            salidasDisponibles.put("west", west);
        if(diagonalNW != null)
            salidasDisponibles.put("northwest", diagonalNW);
    }
    
    /**
     * Devuelve la habitacion a la que se desea ir
     * 
     * @return Room a la que desplazarse, o null
     */
    public Room getExit(String direccion){
        return salidasDisponibles.get(direccion);
    }
    
    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String salidas = "Direcciones disponibles:";
        Set<String> disponibles = salidasDisponibles.keySet();
        for (String posible : disponibles){
            salidas += (" " + posible);
        }
        return salidas;
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
