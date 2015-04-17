import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> lastRoom;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(){
        lastRoom = new Stack<Room>();
    }

    /**
     * Indica al jugador la habitacion en la que se encuentra
     * 
     * @param newRoom Habitacion a la que se mueve el jugador
     */
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }
    
    /**
     * Indica de en que habitacion se encuentra el jugador
     * 
     * @return Habitacion en la que esta el jugador
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
}
