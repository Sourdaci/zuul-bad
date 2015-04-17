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
     * @param direction Hacia que puerta/direccion se mueve el jugador
     */
    public void goRoom(String direction){
        if(direction == null){
            // if there is no second word, we don't know where to go...
            System.out.println("Si no indicas donde, no te vas a mover");
        }else{
            // Try to leave current room.
            Room nextRoom = currentRoom.getExit(direction);
            
            if (nextRoom == null) {
                System.out.println("No atraviesas paredes ni abres ventanas, listo...");
            }else{
                lastRoom.push(currentRoom);
                currentRoom = nextRoom;
            }
        }
    }
    
    /**
     * Indica al jugador que mire en la habitacion actual
     */
    public void lookRoom(){
        System.out.println(currentRoom.getLongDescription() + "\n");
    }
    
    /**
     * Indica al jugador que coma algo
     */
    public void eat(){
        if(currentRoom.getDescription().startsWith("Cocina.")){
            System.out.print("ENSERIO: ");
        }
        System.out.println("Con las necesidades que tienes, comer puede esperar");
    }
    
    /**
     * Indica al jugador que vuelva a la habitacion anterior si puede
     */
    public void goBack(){
        if(!lastRoom.empty()){
            currentRoom = lastRoom.pop();
        }else{
            System.out.println("No puedes volver atras...");
        }
    }
}
