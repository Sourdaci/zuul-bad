
/**
 * Write a description of class PassiveNPC here.
 * 
 * @author Sourdaci
 * @version 2015-04-28 02
 */
public class PassiveNPC
{
    private static int currentID = 1;
    private int iD;
    private String nombre;
    private String frase, fraseAtaque;

    /**
     * Crea NPC pasivos para el juego. 
     * Son personajes que estan de relleno, pero pueden dar alguna pista
     * 
     * @param nombre Nombre del NPC
     * @param frase Texto del NPC cuando se habla con el
     * @param fraseAtaque Texto del NPC cuando se intenta combatir con el
     */
    public PassiveNPC(String nombre, String frase, String fraseAtaque)
    {
        this.nombre = nombre;
        this.frase = frase;
        this.fraseAtaque = fraseAtaque;
        iD = currentID;
        currentID += 2;
        if(currentID > 12 && currentID < 14){
            currentID += 2;
        }
    }

    /**
     * Indica que se quiere hablar con el NPC
     */
    public void hablar(){
        System.out.println(frase);
    }
    
    /**
     * Indica que se quiere pelear con el NPC. 
     * La operacion no tiene exito
     */
    public void pelea(){
        System.out.println(fraseAtaque);
    }
    
    /**
     * Devuelve el ID del NPC
     * 
     * @return ID del NPC
     */
    public int getID(){
        return iD;
    }
    
    /**
     * Representacion textual del NPC
     */
    @Override
    public String toString(){
        return String.format(">> (id %3d) %s", iD, nombre);
    }
}
