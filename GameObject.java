
/**
 * Write a description of class GameObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameObject
{
    // instance variables - replace the example below with your own
    private String nombre;
    private String descripcion;
    private static int currentID = 1;
    private int iD;

    /**
     * Constructor for objects of class GameObject
     */
    public GameObject(String nom, String desc){
        // initialise instance variables
        nombre = nom;
        descripcion = desc;
        iD = currentID;
        currentID++;
        // Manias persecutorias
        if (currentID > 12 && currentID < 14){
            currentID++;
        }
    }

    /**
     * Devuelve el ID del objeto
     * 
     * @return ID
     */
    public int getID(){
        return iD;
    }
    
    /**
     * Devuelve la descripcion del objeto
     * 
     * @return Descripcion del objeto
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Devuelve los detalles del objeto
     * Utilizar cuando se examina un objeto
     * 
     * @return Detalles del objeto
     */
    public String getDetalles(){
        String cadena = descripcion;
        if (descripcion == null){
            cadena = GameText.DESCRIPTION_UNAVAILABLE.getText();
        }
        return cadena;
    }
}
