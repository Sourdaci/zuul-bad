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
    private String descripcionObjeto;
    private float pesoObjeto;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String descObj, float pesoObj) 
    {
        this.description = description;
        salidasDisponibles = new HashMap<String, Room>();
        descripcionObjeto = descObj;
        pesoObjeto = pesoObj;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){
        salidasDisponibles.put(direction, neighbor);
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

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String descripcion = ("Estas en: " + description + "\n");
        if (descripcionObjeto != null){
            descripcion += String.format("Objeto disponible: %s, peso %f Kg\n", descripcionObjeto, pesoObjeto);
        }else{
            descripcion += "No ves objetos aprovechables para ti\n";
        }
        descripcion += getExitString();
        return descripcion;
    }
}
