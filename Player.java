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
    public Player(Room startRoom){
        currentRoom = startRoom;
        lastRoom = new Stack<Room>();
    }

    /**
     * Mueve al jugador a una nueva habitacion
     * 
     * @param newRoom Habitacion a la que se mueve el jugador
     */
    public void goRoom(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Si no indicas donde, no te vas a mover");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("No atraviesas paredes ni abres ventanas, listo...");
        }else{
            lastRoom.push(currentRoom);
            currentRoom = nextRoom;
        }
    }
    
    public void goBack(){
        if(!lastRoom.empty()){
            currentRoom = lastRoom.pop();
        }else{
            System.out.println("No puedes volver atras...");
        }
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
