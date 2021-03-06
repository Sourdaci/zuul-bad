
/**
 * Write a description of class CollectableItem here.
 * 
 * @author Sourdaci
 * @version 2015-04-30 07
 */
public class CollectableItem extends GameObject
{
    // instance variables - replace the example below with your own
    private float peso;
    private boolean collectable;
    /**
     * Constructor for objects of class CollectableItem
     */
    public CollectableItem(String nombre, float peso, boolean collect, String desc)
    {
        super(nombre, desc);
        this.peso = peso;
        collectable = collect;
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
        return String.format(">> (id %3d) %s, %f %s", getID(), getNombre(), peso, GameText.WEIGHT_UNIT.getText());
    }
}
