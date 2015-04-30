import java.util.ArrayList;
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
    private ArrayList<CollectableItem> objetos;
    private Room teleportRoom;
    private String teleportString;
    private ArrayList<PassiveNPC> pasivos;
    private ArrayList<ActiveNPC> activos;
    private boolean endGame;
    private ArrayList<Equipment> equipoTirado;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, Room teleport, String message, boolean end) 
    {
        this.description = description;
        salidasDisponibles = new HashMap<String, Room>();
        objetos = new ArrayList<CollectableItem>();
        teleportRoom = teleport;
        teleportString = message;
        pasivos = new ArrayList<PassiveNPC>();
        activos = new ArrayList<ActiveNPC>();
        endGame = end;
        equipoTirado = new ArrayList<Equipment>();
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
     * Coloca objetos en la habitacion
     * 
     * @param descripcion Texto del objeto
     * @param peso Peso del objeto
     * @param collect Si el objeto puede cogerse o no
     */
    public void addItem(String descripcion, float peso, boolean collect, String detalle){
        objetos.add(new CollectableItem(descripcion, peso, collect, detalle));
    }
    
    
    public void addEquipment(Equipment cosa){
        equipoTirado.add(cosa);
    }
    
    public void addEquipment(String nom, String desc, int at, int def, boolean tipo){
        equipoTirado.add(new Equipment(nom, desc, at, def, tipo));
    }
    
    public void deleteEquipment(Equipment gear){
        equipoTirado.remove(gear);
    }
    
    public Equipment takeEquipment(int equip){
        Equipment gear = null;
        for (int i=0; i<equipoTirado.size(); i++){
            if(equipoTirado.get(i).getID() == equip){
                gear = equipoTirado.get(i);
            }
        }
        return gear;
    }
    
    /**
     * Busca en la habitacion un objeto y lo entrega al jugador
     * 
     * @param item Nombre del objeto que quiere coger el jugador
     * @return El objeto si existe, null si NO existe
     */
    public CollectableItem takeItem(int item){
        CollectableItem obj = null;
        for (int i=0; i < objetos.size() && obj == null; i++){
            if(item == objetos.get(i).getID()){
                obj = objetos.get(i);
            }
        }
        return obj;
    }
    
    /**
     * Elimina de la habitacion el objeto que le indique el jugador
     * 
     * @param item El objeto CollectableItem que debe borrar de la habitacion
     */
    public void deleteItem(CollectableItem item){
        objetos.remove(item);
    }
    
    /**
     * Deja en la habitacion el objeto que le indique el jugador
     * 
     * @param item El objeto CollectableItem que se deja en la habitacion
     */
    public void addItemToRoom(CollectableItem item){
        objetos.add(item);
    }
    
    /**
     * El jugador intenta observar un objeto de la habitacion
     * 
     * @param item ID del objeto que quieres ver de la habitacion
     */
    public void lookItemOnRoom(String item){
        if (objetos.size() > 0){
            if (item != null){
                try{
                    CollectableItem obj = takeItem(Integer.parseInt(item));
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
            System.out.println(GameText.ROOM_WITHOUT_OBJECTS.getText());
        }
    }
    
    public void addPassiveNPC(PassiveNPC pasivo){
        pasivos.add(pasivo);
    }
    
    public void addActiveNPC(ActiveNPC activo){
        activos.add(activo);
    }
    
    public void removeActiveNPC(ActiveNPC activo){
        activos.remove(activo);
    }
    
    public boolean availableNPC(){
        return(!activos.isEmpty() || !pasivos.isEmpty());
    }
    
    public ActiveNPC getActiveNPC(int id){
        ActiveNPC personaje = null;
        for(int i=0; i<activos.size() && personaje == null; i++){
            if(activos.get(i).getID() == id){
                personaje = activos.get(i);
            }
        }
        return personaje;
    }
    
    public PassiveNPC getPassiveNPC(int id){
        PassiveNPC personaje = null;
        for(int i=0; i< pasivos.size() && personaje == null; i++){
            if(pasivos.get(i).getID() == id){
                personaje = pasivos.get(i);
            }
        }
        return personaje;
    }
    
    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String salidas = GameText.AVAILABLE_DIRECTIONS.getText() + ":";
        Set<String> disponibles = salidasDisponibles.keySet();
        for (String posible : disponibles){
            salidas += (" " + posible);
        }
        return salidas;
    }
    
    /**
     * Indica al jugador si la habitacion lleva a otra sin cruzar puertas (Teletransporte)
     * 
     * @return Room de teletransporte, null si NO hay teletransporte
     */
    public Room getTeleportRoom(){
        if(teleportRoom != null){
            System.out.println(teleportString);
        }
        return teleportRoom;
    }
    
    public boolean endGame(){
        return endGame;
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
        String descripcion = (GameText.YOU_ARE_IN_PLACE.getText() + ": " + description + "\n");
        if (objetos.size() != 0){
            for(CollectableItem item : objetos){
                descripcion += item.toString() + "\n";
            }
        }else{
            descripcion += GameText.NOT_INTERESTING_OBJECTS.getText() + "\n";
        }
        if(activos.size() != 0 || pasivos.size() != 0){
            for(PassiveNPC tio : pasivos){
                descripcion += tio.toString() + "\n";
            }
            for(ActiveNPC tio : activos){
                descripcion += tio.toString() + "\n";
            }
        }else{
            descripcion += GameText.NOT_AVAILABLE_NPC.getText() + "\n";
        }
        descripcion += getExitString();
        return descripcion;
    }
}
