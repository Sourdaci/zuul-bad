
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO ("ir", "Para moverse a otra habitacion. Hay que indicar la puerta"), 
    QUIT ("salir", "Para abandonar el juego"), 
    HELP ("ayuda", "Muestra la ayuda del juego"), 
    AUX ("auxilio", "Muestra esta pantalla"),
    LOOK ("ver", "Obtiene una descripcion de la habitacion"), 
    EAT ("comer", "El personaje come algo"), 
    BACK ("atras", "Vuelve a la habitacion anterior"), 
    ITEMS ("objetos", "Visualiza los objetos que llevas en el Inventario"), 
    TAKE ("coger", "Coge un objeto de la habitacion. Hay que indicarlol"), 
    DROP ("soltar", "Deja un objeto del inventario en la habitacion. Hay que indicarlo"), 
    LOOK_CLOSELY ("observar", "Examina un objeto de la habitacion. Hay que indicarlo"), 
    EXAMINE ("examinar", "Examina un objeto del Inventario. Hay que indicarlo"), 
    UNKNOWN ("Desconocido", null);
    
    private final String orden;
    private final String detalle;
    
    Option (String cadena, String descripcion){
        orden = cadena;
        detalle = descripcion;
    }
    
    public String getCommandOrder(){
        return orden;
    }
    
    public String getDetailedOrder(){
        return "> " + orden + ": " + detalle;
    }
}
