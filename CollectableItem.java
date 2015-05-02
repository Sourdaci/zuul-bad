
/**
 * Write a description of class CollectableItem here.
 * 
 * @author Sourdaci
 * @version 2015-04-30 07
 */
public class CollectableItem
{
    // instance variables - replace the example below with your own
    private String descripcion;
    private float peso;
    private boolean collectable;
    private final int iD;
    private static int currentID = 1;
    private String detalle;

    /**
     * Constructor for objects of class CollectableItem
     * 
     * @param desc Descripcion del objeto
     * @param peso Peso del objeto
     * @param collect Si el objeto se puede coger
     * @param details Explicacion o detalles del objeto
     */
    public CollectableItem(String desc, float peso, boolean collect, String details)
    {
        descripcion = desc;
        this.peso = peso;
        collectable = collect;
        iD = currentID;
        currentID++;
        // Manias persecutorias
        if (currentID > 12 && currentID < 14){
            currentID++;
        }
        detalle = details;
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
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Devuelve los detalles del objeto
     * Utilizar cuando se examina un objeto
     * 
     * @return Detalles del objeto
     */
    public String getDetails(){
        String cadena = detalle;
        if (detalle == null){
            cadena = GameText.DESCRIPTION_UNAVAILABLE.getText();
        }
        return cadena;
    }
    
    /**
     * Indica si el objeto puede recogerse
     * 
     * @return true si se puede, false si NO se puede
     */
    public boolean isCollectable(){
        return collectable;
    }
    
    /**
     * Representacion textual del objeto
     * 
     * @return Cadena descriptiva del objeto
     */
    @Override
    public String toString(){
        return String.format(">> (id %3d) %s, %f %s", iD, descripcion, peso, GameText.WEIGHT_UNIT.getText());
    }
}
