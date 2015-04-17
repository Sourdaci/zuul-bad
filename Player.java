import java.util.Stack;
import java.util.ArrayList;
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
    private ArrayList<CollectableItem> objetos;
    private float cargaMax;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(Room startRoom, float carga){
        currentRoom = startRoom;
        lastRoom = new Stack<Room>();
        objetos = new ArrayList<CollectableItem>();
        cargaMax = carga;
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
    
    /**
     * Muestra por pantalla todos los objetos recogidos por el jugador
     */
    public void listItems(){
        if(objetos.size() > 0){
            System.out.println("Objetos en el inventario:");
            for (CollectableItem item : objetos){
                System.out.println(item);
            }
        }else{
            System.out.println("Inventario vacio");
        }
    }
    
    /**
     * El jugador intenta coger un objeto de la habitacion
     * 
     * @param item Nombre del objeto que quieres coger de la habitacion
     */
    public void takeItem(String item){
        if (item != null){
            CollectableItem obj = currentRoom.takeItem(item);
            if (obj == null){
                System.out.println("Lo intentas coger y no puedes porque es un espejismo");
            }else{
                if(obj.isCollectable()){
                    if(calcularCarga() + obj.getPeso() > cargaMax){
                        System.out.println("Si coges eso tambien te partes la espalda");
                    }else{
                        objetos.add(obj);
                        System.out.println("Has cogido " + obj.toString());
                        currentRoom.deleteItem(obj);
                    }
                }else{
                    System.out.println("Aunque eso fuera util... ¿Para que lo quieres?");
                }
            }
        }else{
            System.out.println("Coger... ¿que? ¿Que coges?");
        }
    }
    
    /**
     * Calcula el peso de los objetos que lleva el jugador en el inventario
     */
    private float calcularCarga(){
        float carga = 0;
        for (CollectableItem item : objetos){
            carga += item.getPeso();
        }
        return carga;
    }
}
