
/**
 * Write a description of class Equipment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipment
{
    // instance variables - replace the example below with your own
    private String nombre;
    private String descripcion;
    private int bonoAtaque;
    private int bonoDefensa;
    private boolean tipoEquipo;
    private static int currentID = 1;
    private int iD;
    

    /**
     * Constructor for objects of class Equipment
     */
    public Equipment(String nombre, String desc, int at, int def, boolean tipo)
    {
        this.nombre = nombre;
        descripcion = desc;
        bonoAtaque = at;
        bonoDefensa = def;
        tipoEquipo = tipo;
        iD = currentID;
        currentID++;
        // Manias persecutorias
        if (currentID > 12 && currentID < 14){
            currentID++;
        }
    }

    public int getID(){
        return iD;
    }
    
    public int getBonoAtaque(){
        return bonoAtaque;
    }
    
    public int getBonoDefensa(){
        return bonoDefensa;
    }
    
    public boolean esArma(){
        return tipoEquipo;
    }
    
    public String toString(){
        return String.format(">> (id %3d) %s", iD, descripcion);
    }
}
