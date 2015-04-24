
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO ("ir"), QUIT ("salir"), HELP ("ayuda"), LOOK ("ver"), EAT ("comer"), 
    BACK ("atras"), ITEMS ("objetos"), TAKE ("coger"), DROP ("soltar"), UNKNOWN ("Comando desconocido");
    
    private final String orden;
    
    Option (String cadena){
        orden = cadena;
    }
    
    public String getCommandOrder(){
        return orden;
    }
}
