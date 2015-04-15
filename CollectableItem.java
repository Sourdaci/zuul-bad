
/**
 * Write a description of class CollectableItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollectableItem
{
    // instance variables - replace the example below with your own
    private String descripcion;
    private float peso;

    /**
     * Constructor for objects of class CollectableItem
     * 
     * @param desc Descripcion del objeto
     * @param peso Peso del objeto
     */
    public CollectableItem(String desc, float peso)
    {
        descripcion = desc;
        this.peso = peso;
    }

    /**
     * Devuelve el peso del objeto creado
     * 
     * @return Peso del objeto
     */
    public float getPeso(){
        return peso;
    }
    
    /**
     * Representacion textual del objeto
     * 
     * @return Cadena descriptiva del objeto
     */
    @Override
    public String toString(){
        return ">> " + descripcion + ", " + peso + "Kg";
    }
}
