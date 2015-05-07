import java.util.Stack;
import java.util.HashMap;
/**
 * Write a description of class Player here.
 * 
 * @author Sourdaci
 * @version 2015-04-30 10
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> lastRoom;
    private HashMap<Integer, CollectableItem> objetos;
    private float cargaMax;
    private int vida, vidaRestante;
    private int ataque, defensa;
    private Equipment arma;
    private Equipment armadura;
    private boolean roomEndedGame;
    
    /**
     * Crea un nuevo jugador para el juego
     * 
     * @param startRoom Zona del juego donde comienza el jugador
     * @param carga Peso maximo que puede llevar el jugador
     * @param pv Vida maxima del jugador
     * @param atk Ataque base del jugador
     * @param def Defensa base del jugador
     */
    public Player(Room startRoom, float carga, int pv, int atk, int def){
        currentRoom = startRoom;
        lastRoom = new Stack<Room>();
        objetos = new HashMap<Integer, CollectableItem>();
        cargaMax = carga;
        vida = pv;
        vidaRestante = pv;
        ataque = atk;
        defensa = def;
        arma = null;
        armadura = null;
        roomEndedGame = false;
    }
    
    /**
     * Mueve al jugador a una nueva zona
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
                    roomEndedGame = nextRoom.endGame();
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
     * Indica al jugador que mire en la zona actual
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
            for (CollectableItem item : objetos.values()){
                System.out.println(item);
            }
            System.out.println("-> " + GameText.INVENTORY_LOAD.getText() + ": " + calcularCarga() + " / " + cargaMax + " " + GameText.WEIGHT_UNIT.getText());
        }else{
            System.out.println(GameText.INVENTORY_IS_EMPTY.getText());
        }
    }
    
    /**
     * El jugador intenta coger un objeto de la zona
     * 
     * @param item ID del objeto que quieres coger de la zona
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
                            objetos.put(obj.getID(), obj);
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
     * El jugador intenta dejar un objeto en la zona
     * 
     * @param item ID del objeto que intenta dejar en la zona
     */
    public void dropItem(String item){
        if (item != null){
            CollectableItem obj = null;
            try{
                int id = Integer.parseInt(item);
                obj = objetos.get(id);
                if(obj == null){
                    System.out.println(GameText.DROPPING_INEXISTENT_OBJECT.getText());
                }else{
                    System.out.println(GameText.DROPPING_OBJECT_SUCCESSFULLY.getText() + " " + obj.toString());
                    currentRoom.addItemToRoom(obj);
                    objetos.remove(obj.getID());
                }
            }catch (Exception ex){
                System.out.println(GameText.OBJECT_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.DROPPING_OBJECT_WITHOUT_OBJECT.getText());
        }
    }
    
    /**
     * El jugador intenta observar un objeto de la zona
     * 
     * @param item ID del objeto que quieres ver de la zona
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
                    CollectableItem obj = objetos.get(indice);
                    if (obj == null){
                        System.out.println(GameText.PICKING_INEXISTENT_OBJECT.getText());
                    }else{
                        System.out.println(obj.getDetalles());
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
     * El jugador intenta hablar con un NPC de la zona
     * 
     * @param idNPC El ID del NPC con el que intenta hablar el jugador
     */
    public void talkWith(String idNPC){
        if(currentRoom.availableNPC()){
            try{
                int id = Integer.parseInt(idNPC);
                ActiveNPC activo = currentRoom.getActiveNPC(id);
                if(activo == null){
                    System.out.println(GameText.NPC_ID_INVALID.getText());
                }else{
                    activo.hablar(this);
                }
            }catch (Exception ex){
                System.out.println(GameText.NPC_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.ROOM_WITHOUT_NPC.getText());
        }
    }
    
    /**
     * El jugador intenta entablar combate con un NPC activo de la zona
     * Si el ID es de un NPC pasivo, no hay combate
     * 
     * @param idNPC El ID del NPC con el que se intenta combatir
     * @return El ActiveNPC con el que combatir, null en cualquier otro caso
     */
    public ActiveNPC battle(String idNPC){
        ActiveNPC activo = null;
        if(currentRoom.availableNPC()){
            try{
                int id = Integer.parseInt(idNPC);
                activo = currentRoom.getActiveNPC(id);
                if(activo == null){
                    System.out.println(GameText.NPC_ID_ATTACK_INVALID.getText());
                }else if(!activo.pelea()){
                    activo = null;
                }
            }catch (Exception ex){
                System.out.println(GameText.NPC_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.ROOM_WITHOUT_NPC.getText());
        }
        return activo;
    }
    
    /**
     * Cambia la salud restante del jugador
     * 
     * @param num La salud restante
     */
    public void setVidaRestante(int num){
        vidaRestante = num;
    }
    
    /**
     * Devuelve la salud restante del jugador
     * 
     * @return Salud restante
     */
    public int getVitalidad(){
        return vidaRestante;
    }
    
    /**
     * Devuelve la vida total del jugador
     * 
     * @return Vida total
     */
    public int getVitalidadTotal(){
        return vida;
    }
    
    /**
     * Calcula el poder de ataque del jugador
     * 
     * @return Ataque del jugador
     */
    public int getAtaque(){
        int cantidad = ataque;
        if(arma != null){
            cantidad += arma.getBonoAtaque();
        }
        if(armadura != null){
            cantidad += armadura.getBonoAtaque();
        }
        return cantidad;
    }
    
    /**
     * Calcula el poder defensivo del jugador
     * 
     * @return Defensa del jugador
     */
    public int getDefensa(){
        int cantidad = defensa;
        if(arma != null){
            cantidad += arma.getBonoDefensa();
        }
        if(armadura != null){
            cantidad += armadura.getBonoDefensa();
        }
        return cantidad;
    }
    
    /**
     * El jugador intenta coger un equipo de la zona
     * 
     * @param item ID del equipo que quieres coger de la zona
     */
    public void takeEquipment(String item){
        if (item != null){
            try{
                Equipment obj = currentRoom.takeEquipment(Integer.parseInt(item));
                if (obj == null){
                    System.out.println(GameText.PICKING_INEXISTENT_EQUIPMENT.getText());
                }else{
                    if(obj.esArma()){
                        if(arma != null){
                            System.out.println(GameText.WEAPON_ALREADY_EQUIPPED.getText());
                        }else{
                            arma = obj;
                            System.out.println(GameText.EQUIPMENT_SUCCESSFUL.getText() + ": " + obj);
                            currentRoom.deleteEquipment(obj);
                        }
                    }else{
                        if(armadura != null){
                            System.out.println(GameText.ARMOR_ALREADY_EQUIPPED.getText());
                        }else{
                            armadura = obj;
                            System.out.println(GameText.EQUIPMENT_SUCCESSFUL.getText() + ": " + obj);
                            currentRoom.deleteEquipment(obj);
                        }
                    }
                }
            }catch (Exception ex){
                System.out.println(GameText.EQUIPMENT_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.PICKING_EQUIPMENT_WITHOUT_EQUIPMENT.getText());
        }
    }
    
    /**
     * El jugador intenta dejar un objeto en la zona
     * 
     * @param item ID del equipo que quieres dejar en la zona
     */
    public void dropEquipment(String item){
        if (item != null){
            try{
                int id = Integer.parseInt(item);
                boolean borrado = false;
                if(arma != null){
                    if (arma.getID() == id){
                        System.out.println(GameText.DROP_EQUIPMENT_SUCCESSFUL.getText() + ": " + arma);
                        currentRoom.addEquipment(arma);
                        arma = null;
                        borrado = true;
                    }
                }
                if(!borrado && armadura != null){
                    if(armadura.getID() == id){
                        System.out.println(GameText.DROP_EQUIPMENT_SUCCESSFUL.getText() + ": " + armadura);
                        currentRoom.addEquipment(armadura);
                        armadura = null;
                        borrado = true;
                    }
                }
                if(!borrado){
                    System.out.println(GameText.EQUIPMENT_ID_NOT_FOUND.getText());
                }
            }catch (Exception ex){
                System.out.println(GameText.EQUIPMENT_ID_NOT_NUMBER.getText());
            }
        }else{
            System.out.println(GameText.DROP_EQUIPMENT_WITHOUT_EQUIPMENT.getText());
        }
    }
    
    /**
     * Muestra por pantalla el estado del jugador (atributos, objetos y equipo)
     */
    public void getStatus(){
        System.out.println(GameText.STATUS_VIT.getText() + ": " + vidaRestante + "/" + vida);
        System.out.println(GameText.STATUS_ATTACK.getText() + ": " + getAtaque() + ", " + GameText.STATUS_DEFFENSE.getText() + ": " + getDefensa());
        if(arma != null){
            System.out.println(arma);
            System.out.println("\t" + arma.getDetalles());
        }else{
            System.out.println(GameText.STATUS_NOT_WEAPON.getText());
        }
        if(armadura != null){
            System.out.println(armadura);
            System.out.println("\t" + armadura.getDetalles());
        }else{
            System.out.println(GameText.STATUS_NOT_ARMOR.getText());
        }
        listItems();
    }
    
    /**
     * Le dice a la zona en la que se encuentra el jugador que elimine al enemigo que ha derrotado
     */
    public void enemigoDerrotado(ActiveNPC enemigo){
        currentRoom.removeActiveNPC(enemigo);
    }
    
    /**
     * Se le pregunta al jugador si tiene un objeto concreto en el inventario
     */
    public boolean enInventario(CollectableItem item){
        return objetos.containsValue(item);
    }
    
    /**
     * Se le entrega a un NPC el objeto indicado
     */
    public void entregarObjetoNPC(CollectableItem item){
        objetos.remove(item.getID());
    }
    
    /**
     * Indica al juego si la habitacion actual finaliza la partida
     */
    public boolean roomEndsGame(){
        return roomEndedGame;
    }
    
    /**
     * Calcula el peso de los objetos que lleva el jugador en el inventario
     */
    private float calcularCarga(){
        float carga = 0;
        for (CollectableItem item : objetos.values()){
            carga += item.getPeso();
        }
        return carga;
    }
}
