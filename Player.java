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
            System.out.println(GameText.DIRECTION_NOT_INDICATED.getText());
        }else{
            // Try to leave current room.
            Room nextRoom = currentRoom.getExit(direction);
            
            if (nextRoom == null) {
                System.out.println(GameText.DIRECTION_INVALID.getText());
            }else{
                Room teleport = nextRoom.getTeleportRoom();
                if(teleport != null){
                    lastRoom.clear();
                    currentRoom = teleport;
                }else{
                    lastRoom.push(currentRoom);
                    currentRoom = nextRoom;
                }
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
        System.out.println(GameText.WHEN_PLAYER_EATS.getText());
    }
    
    /**
     * Indica al jugador que vuelva a la habitacion anterior si puede
     */
    public void goBack(){
        if(!lastRoom.empty()){
            currentRoom = lastRoom.pop();
        }else{
            System.out.println(GameText.WHEN_CANT_GO_BACK.getText());
        }
    }
    
    /**
     * Muestra por pantalla todos los objetos recogidos por el jugador
     */
    public void listItems(){
        if(objetos.size() > 0){
            System.out.println(GameText.INVENTORY_HEADER.getText() + ":");
            for (CollectableItem item : objetos){
                System.out.println(item);
            }
            System.out.println("-> " + GameText.INVENTORY_LOAD.getText() + ": " + calcularCarga() + " / " + cargaMax + " " + GameText.WEIGHT_UNIT.getText());
        }else{
            System.out.println(GameText.INVENTORY_IS_EMPTY.getText());
        }
    }
    
    /**
     * El jugador intenta coger un objeto de la habitacion
     * 
     * @param item ID del objeto que quieres coger de la habitacion
     */
    public void takeItem(String item){
        if (item != null){
            try{
                CollectableItem obj = currentRoom.takeItem(Integer.parseInt(item));
                if (obj == null){
                    System.out.println(GameText.PICKING_INEXISTENT_OBJECT.getText());
                }else{
                    if(obj.isCollectable()){
                        if(calcularCarga() + obj.getPeso() > cargaMax){
                            System.out.println(GameText.PICKING_OBJECT_AND_OVERWEIGHT.getText());
                        }else{
                            objetos.add(obj);
                            System.out.println(GameText.PICKING_OBJECT_SUCCESSFULLY.getText() + " " + obj.toString());
                            currentRoom.deleteItem(obj);
                        }
                    }else{
                        System.out.println(GameText.PICKING_UNPICKABLE_OBJECT.getText());
                    }
                }
            }catch (Exception ex){
                System.out.println(GameText.OBJECT_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.PICKING_OBJECT_WITHOUT_OBJECT.getText());
        }
    }
    
    /**
     * El jugador intenta dejar un objeto en la habitacion
     * 
     * @param item ID del objeto que intenta dejar en la habitacion
     */
    public void dropItem(String item){
        if (item != null){
            CollectableItem obj = null;
            try{
                int id = Integer.parseInt(item);
                for (int i=0; i < objetos.size() && obj == null; i++){
                    if(id == objetos.get(i).getID()){
                        obj = objetos.get(i);
                    }
                }
                if(obj == null){
                    System.out.println(GameText.DROPPING_INEXISTENT_OBJECT.getText());
                }else{
                    System.out.println(GameText.DROPPING_OBJECT_SUCCESSFULLY.getText() + " " + obj.toString());
                    currentRoom.addItemToRoom(obj);
                    objetos.remove(obj);
                }
            }catch (Exception ex){
                System.out.println(GameText.OBJECT_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.DROPPING_OBJECT_WITHOUT_OBJECT.getText());
        }
    }
    
    /**
     * El jugador intenta observar un objeto de la habitacion
     * 
     * @param item ID del objeto que quieres ver de la habitacion
     */
    public void lookItemOnRoom(String item){
        currentRoom.lookItemOnRoom(item);
    }
    
    /**
     * El jugador intenta observar un objeto de su inventario
     * 
     * @param item ID del objeto que quieres ver del inventario
     */
    public void lookItemOnInventory(String item){
        if (objetos.size() > 0){
            if (item != null){
                try{
                    int indice = Integer.parseInt(item);
                    CollectableItem obj = null;
                    for (int i=0; i < objetos.size() && obj == null; i++){
                        obj = objetos.get(i);
                        if(obj.getID() != indice){
                            obj = null;
                        }
                    }
                    if (obj == null){
                        System.out.println(GameText.PICKING_INEXISTENT_OBJECT.getText());
                    }else{
                        System.out.println(obj.getDetails());
                    }
                }catch (Exception ex){
                    System.out.println(GameText.OBJECT_ID_NOT_NUMBER.getText());
                }
            }else{
                System.out.println(GameText.PICKING_OBJECT_WITHOUT_OBJECT.getText());
            }
        }else{
            System.out.println(GameText.INVENTORY_IS_EMPTY.getText());
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
