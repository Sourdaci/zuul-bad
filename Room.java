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
    private Room northExit;
    private Room neExit;
    private Room eastExit;
    private Room seExit;
    private Room southExit;
    private Room swExit;
    private Room westExit;
    private Room nwExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
            northExit = north;
        if(diagonalNE != null)
            neExit = diagonalNE;
        if(east != null)
            eastExit = east;
        if(diagonalSE != null)
            seExit = diagonalSE;
        if(south != null)
            southExit = south;
        if(diagonalSW != null)
            swExit = diagonalSW;
        if(west != null)
            westExit = west;
        if(diagonalNW != null)
            nwExit = diagonalNW;
    }
    
    /**
     * Devuelve la habitacion a la que se desea ir
     * 
     * @return Room a la que desplazarse, o null
     */
    public Room getExit(String direccion){
        Room siguiente = null;
        switch (direccion){
            case "north":
                siguiente = northExit;
                break;
            case "northeast":
                siguiente = neExit;
                break;
            case "east":
                siguiente = eastExit;
                break;
            case "southeast":
                siguiente = seExit;
                break;
            case "south":
                siguiente = southExit;
                break;
            case "southwest":
                siguiente = swExit;
                break;
            case "west":
                siguiente = westExit;
                break;
            case "northwest":
                siguiente = nwExit;
                break;
        }
        return siguiente;
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
