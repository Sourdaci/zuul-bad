
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author Sourdaci
 * @version 2015-04-30 06
 */
public enum Option
{
    GO ("ir", "Para moverse a otra zona. Hay que indicar la ruta"), 
    QUIT ("salir", "Para abandonar el juego"), 
    HELP ("ayuda", "Muestra la ayuda del juego"), 
    AUX ("auxilio", "Muestra esta pantalla"),
    LOOK ("ver", "Obtiene una descripcion de la zona"), 
    EAT ("comer", "El personaje come algo"), 
    BACK ("atras", "Vuelve a la zona anterior"), 
    ITEMS ("objetos", "Visualiza los objetos que llevas en el Inventario"), 
    TAKE ("coger", "Coge un objeto de la zona. Hay que indicarlo"), 
    DROP ("soltar", "Deja un objeto del inventario en la zona. Hay que indicarlo"), 
    LOOK_CLOSELY ("observar", "Examina un objeto de la zona. Hay que indicarlo"), 
    EXAMINE ("examinar", "Examina un objeto del Inventario. Hay que indicarlo"), 
    TALK ("hablar", "Habla con un personaje de la zona. Hay que indicarlo"),
    FIGHT ("atacar", "Pelea con un personaje de la zona. Hay que indicarlo"),
    EQUIP ("equipar", "Te intentas poner un equipo de la zona. Hay que indicarlo"),
    UNEQUIP ("quitar", "Intentas dejar una equipacion en la zona. Hay que indicarla"),
    STATUS ("estado", "Muestra vida, inventario y equipo del jugador"),
    UNKNOWN ("Desconocido", null);
    
    private final String orden;
    private final String detalle;
    
    /**
     * Crea los comandos del juego
     * 
     * @param cadena Comando del juego
     * @param descripcion Descripcion del comando
     */
    Option (String cadena, String descripcion){
        orden = cadena;
        detalle = descripcion;
    }
    
    /**
     * Devuelve el comando almacenado
     * 
     * @return Comando del juego
     */
    public String getCommandOrder(){
        return orden;
    }
    
    /**
     * Devuelve el comando detallado
     * 
     * @return Comando y su explicacion
     */
    public String getDetailedOrder(){
        return "> " + orden + ": " + detalle;
    }
}
